/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Student;
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
import javax.persistence.EntityManager;
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
import studentService.StudentInterface;

/**
 *
 * @author irati
 */
@Path("entities.student")
public class StudentFacadeREST {

    @EJB
    private StudentInterface ejb;
    private Logger LOGGER = Logger.getLogger(StudentFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createStudent(Student student) {
        try {
            LOGGER.log(Level.INFO, "Creating user {0}", student.getId());
            ejb.createStudent(student);
        } catch (EmailAlreadyExistsException | CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateStudent(Student student) {
        LOGGER.log(Level.INFO, "Updating student {0}", student.getId());
        try {
            ejb.updateStudent(student);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        LOGGER.log(Level.INFO, "Deleting student {0}", id);
        try {
            ejb.deleteStudent(ejb.findStudentById(id));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

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

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Student> findAll() {
        List<Student> students;
        try {
            LOGGER.log(Level.INFO, "Reading data for all users");
            students = ejb.findAllStudents();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return students;
    }

}
