package service;

import entities.Student;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.EncryptException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import exceptions.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import studentService.StudentInterface;

/**
 * RESTful web service for managing student entities.
 *
 *
 * @author irati
 */
@Path("entities.student")
public class StudentFacadeREST {

    @EJB
    private StudentInterface ejb;
    private Logger LOGGER = Logger.getLogger(StudentFacadeREST.class.getName());

    /**
     * Creates a new student entity.
     *
     * @param student The student entity to be created.
     * @return 
     * @throws InternalServerErrorException If an error occurs during creation.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student createStudent(Student student) {
        try {
            LOGGER.log(Level.INFO, "Creating student {0}", student.getId());
            ejb.createStudent(student);

        } catch (EncryptException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (EmailAlreadyExistsException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotAuthorizedException(ex.getMessage());
        } catch (BadRequestException e) {
            LOGGER.severe(e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());

        }
        //return the student without password
        student.setPassword(null);
        LOGGER.log(Level.INFO, "Created student {0} successfully", student.getId());
        return student;
    }

    /**
     * Updates an existing student entity.
     *
     * @param student The updated student entity.
     * @throws InternalServerErrorException If an error occurs during update.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateStudent(Student student) {
        try {
            LOGGER.log(Level.INFO, "Updating student {0}", student.getId());
            ejb.updateStudent(student);
            LOGGER.log(Level.INFO, "Updated student {0} successfully", student.getId());
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Deletes a student entity by its identifier.
     *
     * @param id The identifier.
     * @throws InternalServerErrorException If an error occurs during deletion.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {

        try {
            LOGGER.log(Level.INFO, "Deleting student {0}", id);
            ejb.deleteStudent(ejb.findStudentById(id));
            LOGGER.log(Level.INFO, "Student deleted successfully {0}", id);
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Retrieves a student entity by its identifier.
     *
     * @param id The identifier.
     * @return The retrieved student entity.
     * @throws NotFoundException If the student is not found.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student find(@PathParam("id") Integer id) {
        Student student;
        try {
            LOGGER.log(Level.INFO, "Reading data of student {0}", id);
            student = ejb.findStudentById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return student;
    }

    /**
     * Retrieves all student entities.
     *
     * @return List of all student entities.
     * @throws NotFoundException If no students are found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findAll() {
        List<Student> students;
        try {
            LOGGER.log(Level.INFO, "Reading data for all students");
            students = ejb.findAllStudents();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return students;
    }
}
