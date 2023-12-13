/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Subject;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import subjectService.SubjectInterface;

/**
 * RESTful web service for managing subjects.
 *
 * @author irati
 */
@Stateless
@Path("entities.subject")
public class SubjectFacadeREST {
    /**
     * EJB object implementing business logic.
     */
    @EJB
    private SubjectInterface ejb;
    /**
     * Logger for this class.
     */
    private Logger LOGGER = Logger.getLogger(SubjectFacadeREST.class.getName());

    /**
     * POST method to create subjects: uses createSubject business logic method.
     *
     * @param subject The subject object containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createSubject(Subject subject) {
        try {
            LOGGER.log(Level.INFO, "Creating subject {0}", subject.getId());
            ejb.createSubject(subject);
        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update subject: uses updateSubject business logic method.
     *
     * @param subject The subject object containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateSubject(Subject subject) {
        LOGGER.log(Level.INFO, "Updating account {0}", subject.getId());
        try {
            ejb.updateSubject(subject);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }

    }

    /**
     * DELETE method to delete subject: uses deleteSubject business logic
     * method.
     *
     * @param id the id of the subject.
     */
    @DELETE
    @Path("{id}")
    public void removeSubject(@PathParam("id") Integer id) {
        LOGGER.log(Level.INFO, "Deleting account {0}", id);
        try {
            ejb.deleteSubject(ejb.findSubjectById(id));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    /**
     * GET method to find a subject by id: uses findSubjectById business logic
     * method.
     *
     * @param id the id of the subject.
     * @return the subject containing data.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Subject find(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Reading data for account {0}", id);
            return ejb.findSubjectById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find all subjects: uses findAllSubjects business logic
     * method.
     *
     * @return the subjects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Subject> findAll() {
        try {
            LOGGER.log(Level.INFO, "Reading data for all accounts");
            return (List<Subject>) ejb.findAllSubjects();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find subject by name: uses findSubjectsByName business
     * logic method.
     *
     * @param name the name of the subject for the search.
     * @return the subjects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsByName/{name}")
    public List<Subject> findSubjectsByName(@PathParam("name") String name) {
        try {
            return ejb.findSubjectsByName(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find subject by teacher's name: uses findSubjectsByTeacher
     * business logic method.
     *
     * @param name the name of the teacher for the search.
     * @return the subjects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectByTeacher/{name}")
    public List<Subject> findSubjectsByTeacher(@PathParam("name") String name) {
        try {
            return ejb.findSubjectsByTeacher(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find subject by language: uses findSubjectsByLanguage
     * business logic method.
     *
     * @param language the language of the subject.
     * @return the subjects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsByLanguage/{language}")
    public List<Subject> findSubjectsByLanguage(@PathParam("language") String language) {
        try {
            return ejb.findSubjectsByLanguage(language);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find subject by init date: uses findSubjectsByInitDate
     * business logic method.
     *
     * @param dateGet the init date of the subject.
     * @return the subjects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsByInitDate/{date}")
    public List<Subject> findSubjectsByInitDate(@PathParam("date") String dateGet) {
        List<Subject> subjects;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(dateGet);
            subjects = ejb.findSubjectsByInitDate(date);
        } catch (FindErrorException | ParseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * GET method to find subject by end date: uses findSubjectsByEndDate
     * business logic method.
     *
     * @param dateGet the end date of the subject.
     * @return the subjects.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsByEndDate/{date}")
    public List<Subject> findSubjectsByEndDate(@PathParam("date") String dateGet) {
        List<Subject> subjects;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = format.parse(dateGet);
            subjects = ejb.findSubjectsByEndDate(date);
        } catch (FindErrorException | ParseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * GET method to find subjects based on the count of associated units: uses
     * findSubjectsWithXUnits business logic method.
     *
     * @param number The number for comparison.
     * @param comparisonOperator The operator for comparison
     * @return List of subjects meeting the specified unit count condition.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsWithXUnits/{number}/{comparisonOperator}")
    public List<Subject> findSubjectsWithXUnits(@PathParam("number") Integer number, @PathParam("comparisonOperator") String comparisonOperator) {
        List<Subject> subjects;
        try {
            subjects = ejb.findSubjectsWithXUnits(number, comparisonOperator);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * GET method to find subjects based on the count of enrolled students: uses findSubjectsWithEnrolledStudentsCount business logic method.
     *
     * @param number The number for comparison.
     * @param comparisonOperator The operator for comparison.
     * @return List of subjects meeting the specified enrollment count
     * condition.
     * 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsWithEnrolledStudentsCount/{number}/{comparisonOperator}")
    public List<Subject> findSubjectsWithEnrolledStudentsCount(@PathParam("number") Integer number, @PathParam("comparisonOperator") String comparisonOperator) {
        List<Subject> subjects;
        try {
            subjects = ejb.findSubjectsWithEnrolledStudentsCount(number, comparisonOperator);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

}