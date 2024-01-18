package service;

import entities.Subject;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.SubjectNameAlreadyExistsException;
import exceptions.UpdateErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import subjectService.SubjectInterface;

/**
 * RESTful web service for managing subjects.
 *
 * @author irati
 */
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
        } catch (CreateErrorException | SubjectNameAlreadyExistsException ex) {
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
        LOGGER.log(Level.INFO, "Updating subject {0}", subject.getId());
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
        LOGGER.log(Level.INFO, "Deleting subject {0}", id);
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
        Subject subject;
        try {
            LOGGER.log(Level.INFO, "Reading data for subject {0}", id);
            subject = ejb.findSubjectById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subject;
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
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading data for all subjects");
            subjects = ejb.findAllSubjects();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
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
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading subjects by name");
            subjects = ejb.findSubjectsByName(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
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
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading subjects by teacher's name");
            subjects = ejb.findSubjectsByTeacher(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
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
            LOGGER.log(Level.INFO, "Reading subjects by initDate");
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
            LOGGER.log(Level.INFO, "Reading subjects by endDate");
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
     * @return List of subjects meeting the specified unit count condition.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsWithXUnits/{number}")
    public List<Subject> findSubjectsWithXUnits(@PathParam("number") Long number) {
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading subjects that have " + number + " number of units");
            subjects = ejb.findSubjectsWithXUnits(number);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * GET method to find subjects based on the count of enrolled students: uses
     * findSubjectsWithEnrolledStudentsCount business logic method.
     *
     * @param number The number for comparison.
     * @return List of subjects meeting the specified enrollment count
     * condition.
     *
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsWithEnrolledStudentsCount/{number}")
    public List<Subject> findSubjectsWithEnrolledStudentsCount(@PathParam("number") Long number) {
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading subjects that have " + number + " of enrolledStudents");
            subjects = ejb.findSubjectsWithEnrolledStudentsCount(number);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * GET method that retrieves a list of subjects based on the enrollments of
     * a student identified by the provided student ID.
     *
     * @param studentId The unique identifier of the student for whom subjects
     * are to be retrieved.
     * @return A List of Subject objects representing the subjects associated
     * with the specified student.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findByEnrollments/{studentId}")
    public List<Subject> findByEnrollments(@PathParam("studentId") Integer studentId) {
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading subjects in which a student is enrolled");
            subjects = ejb.findByEnrollments(studentId);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * GET method that retrieves a list of subjects taught by a teacher
     * identified by the provided teacher ID.
     *
     * @param teacherId The unique identifier of the teacher for whom subjects
     * are to be retrieved.
     * @return A List of Subject objects representing the subjects taught by the
     * specified teacher.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findSubjectsByTeacherId/{teacherId}")
    public List<Subject> findSubjectsByTeacherId(@PathParam("teacherId") Integer teacherId) {
        List<Subject> subjects;
        try {
            LOGGER.log(Level.INFO, "Reading subjects for a teacher by id ", teacherId);
            subjects = ejb.findSubjectsByTeacherId(teacherId);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return subjects;
    }

}
