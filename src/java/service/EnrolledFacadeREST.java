/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Enrolled;
import entities.EnrolledId;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import subjectService.EnrolledInterface;

/**
 *
 * @author irati
 */
@Stateless
@Path("entities.enrolled")
public class EnrolledFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private EnrolledInterface ejb;
    /**
     * Logger for this class.
     */
    private Logger LOGGER = Logger.getLogger(SubjectFacadeREST.class.getName());

    /**
     * Extracts and constructs the primary key (EnrolledId) from a URI path
     * segment.
     *
     *
     * @param pathSegment The URI path segment containing matrix parameters.
     * @return EnrolledId representing the primary key constructed from matrix
     * parameters.
     */
    private EnrolledId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;studentId=studentIdValue;subjectId=subjectIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.EnrolledId key = new entities.EnrolledId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> studentId = map.get("studentId");
        if (studentId != null && !studentId.isEmpty()) {
            key.setStudentId(new java.lang.Integer(studentId.get(0)));
        }
        java.util.List<String> subjectId = map.get("subjectId");
        if (subjectId != null && !subjectId.isEmpty()) {
            key.setSubjectId(new java.lang.Integer(subjectId.get(0)));
        }
        return key;
    }
    /**
     * POST method to create enrollment: uses createEnrolled business logic method.
     *
     * @param enrolled the enrollment containg the data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createEnrolled(Enrolled enrolled) {
        try {
            LOGGER.log(Level.INFO, "Creating enrolled {0}", enrolled.getId());
            ejb.createEnrolled(enrolled);
        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    /**
     * PUT method to update enrollment: uses updateEnrolled business logic method.
     *
     * @param enrolled the enrollment containg the data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateEnrolled(Enrolled enrolled) {
        LOGGER.log(Level.INFO, "Updating enrolled {0}", enrolled.getId());
        try {
            ejb.updateEnrolled(enrolled);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }

    }
    /**
     * DELETE method to delete enrollment: uses deleteEnrolled business logic method.
     *
     * @param id the id of the subject.
     */
    @DELETE
    @Path("{id}")
    public void removeEnrolled(@PathParam("id") Integer id) {
        LOGGER.log(Level.INFO, "Deleting enrolled {0}", id);
        try {
            ejb.deleteEnrolled(ejb.findEnrolledById(id));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }
    /**
     * GET method to find an enrollment by id: uses findEnrolledById business logic method.
     *
     * @param id the id of the subject.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Enrolled find(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Reading data for account {0}", id);
            return ejb.findEnrolledById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
     /**
     * GET method to find all enrollments: uses findAllEnrolled business logic method.
     *
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Enrolled> findAll() {
        try {
            LOGGER.log(Level.INFO, "Reading data for all accounts");
            return (List<Enrolled>) ejb.findAllEnrolled();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

}
