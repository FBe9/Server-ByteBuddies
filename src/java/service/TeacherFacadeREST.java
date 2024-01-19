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
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
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
 *
 * @author irati
 */
@Stateless
@Path("entities.teacher")
public class TeacherFacadeREST{

    
    @EJB
    private TeacherInterface ejb;
    private Logger LOGGER = Logger.getLogger(TeacherFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createTeacher(Teacher teacher) {
        try {
            LOGGER.log(Level.INFO, "Creating user {0}", teacher.getId());
            ejb.createTeacher(teacher);
        } catch (EmailAlreadyExistsException | CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateTeacher(Teacher teacher) {
        LOGGER.log(Level.INFO, "Updating teacher {0}", teacher.getId());
        try {
            ejb.updateTeacher(teacher);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        LOGGER.log(Level.INFO, "Deleting teacher {0}", id);
        try {
            ejb.deleteTeacher(ejb.findTeacherById(id));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

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

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Teacher> findAll() {
        List<Teacher> teachers;
        try {
            LOGGER.log(Level.INFO, "Reading data for all users");
            teachers = ejb.findAllTeachers();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return teachers;
    }

    
}
