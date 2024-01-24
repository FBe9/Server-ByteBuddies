package service;

import entities.Teacher;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import teacherService.TeacherInterface;

/**
 * RESTful web service for managing Teacher entities. Handles CRUD operations
 * for Teacher resources.
 *
 * @author irati
 */
@Path("entities.teacher")
public class TeacherFacadeREST {

    @EJB
    private TeacherInterface ejb;
    private Logger LOGGER = Logger.getLogger(TeacherFacadeREST.class.getName());

    /**
     * Creates a new Teacher.
     *
     * @param teacher The Teacher entity to be created.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createTeacher(Teacher teacher) {
        try {
            LOGGER.log(Level.INFO, "Creating teacher {0}", teacher.getId());
            ejb.createTeacher(teacher);
            LOGGER.log(Level.INFO, "Teacher {0} created successfully", teacher.getId());
        } catch (EmailAlreadyExistsException | CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Updates an existing Teacher.
     *
     * @param teacher The updated Teacher entity.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateTeacher(Teacher teacher) {

        try {
            LOGGER.log(Level.INFO, "Updating teacher {0}", teacher.getId());
            ejb.updateTeacher(teacher);
            LOGGER.log(Level.INFO, "Teacher {0} updated successfully", teacher.getId());
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }
    }

    /**
     * Deletes a Teacher by ID.
     *
     * @param id The ID of the Teacher to be deleted.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {

        try {
            LOGGER.log(Level.INFO, "Deleting teacher {0}", id);
            ejb.deleteTeacher(ejb.findTeacherById(id));
            LOGGER.log(Level.INFO, "Teacher {0} deleted successfully", id);
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Retrieves a Teacher by ID.
     *
     * @param id The ID of the Teacher to be retrieved.
     * @return The retrieved Teacher entity.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Teacher find(@PathParam("id") Integer id) {
        Teacher teacher;
        try {
            LOGGER.log(Level.INFO, "Reading data of teacher {0}", id);
            teacher = ejb.findTeacherById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return teacher;
    }

    /**
     * Retrieves all Teacher entities.
     *
     * @return List of all Teacher entities.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Teacher> findAll() {
        List<Teacher> teachers;
        try {
            LOGGER.log(Level.INFO, "Reading data for all teachers");
            teachers = ejb.findAllTeachers();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        } catch (BadRequestException e) {
            LOGGER.severe(e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
        return teachers;
    }
}
