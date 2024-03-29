package service;

import entities.Enrolled;
import entities.EnrolledId;
import entities.Student;
import entities.Subject;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
import studentService.StudentInterface;
import subjectService.EnrolledInterface;
import subjectService.SubjectInterface;

/**
 * RESTful web service for managing enrollments.
 *
 * @author irati
 */
@Path("entities.enrolled")
public class EnrolledFacadeREST {

    /**
     * EJB object implementing enrolled business logic.
     */
    @EJB
    private EnrolledInterface ejb;
    /**
     * EJB object implementing student business logic.
     */
    @EJB
    private StudentInterface ejbS;
    /**
     * EJB object implementing subject business logic.
     */
    @EJB
    private SubjectInterface ejbSu;
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
     * Creates a new enrollment for a student in a subject.
     *
     *
     * @param enrolled The enrollment details to be created.
     * @throws InternalServerErrorException If an error occurs during enrollment
     * creation.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createEnrolled(Enrolled enrolled) {
        Subject subject = null;
        Student student = null;
        try {
            try {
                // Retrieve subject and student details by their identifiers
                subject = ejbSu.findSubjectById(enrolled.getId().getSubjectId());
                student = ejbS.findStudentById(enrolled.getId().getStudentId());
            } catch (FindErrorException ex) {
                Logger.getLogger(EnrolledFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Set the retrieved subject and student in the enrollment
            enrolled.setStudent(student);
            enrolled.setSubject(subject);

            // Log and create the enrollment
            LOGGER.log(Level.INFO, "Creating enrolled {0}", enrolled.getId());
            ejb.createEnrolled(enrolled);

        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update enrollment: uses updateEnrolled business logic
     * method.
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
     * DELETE method to delete enrollment: uses deleteEnrolled business logic
     * method.
     *
     * @param studentId the id of the student.
     * @param subjectId the id of the subject.
     */
    @DELETE
    @Path("{studentId}/{subjectId}")
    public void removeEnrolled(@PathParam("studentId") Integer studentId, @PathParam("subjectId") Integer subjectId) {
        EnrolledId id = new EnrolledId(studentId, subjectId);
        try {
            LOGGER.log(Level.INFO, "Deleting enrolled {0}", id);
            ejb.deleteEnrolled(ejb.findEnrolledById(studentId, subjectId));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    /**
     * GET method to find an enrollment by id: uses findEnrolledById business
     * logic method.
     *
     * @param studentId the id of the student.
     * @param subjectId the id of the subject.
     * @return a enrolled object.
     */
    @GET
    @Path("{studentId}/{subjectId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Enrolled find(@PathParam("studentId") Integer studentId, @PathParam("subjectId") Integer subjectId) {
        Enrolled enrolled;
        EnrolledId id = new EnrolledId(studentId, subjectId);
        try {
            LOGGER.log(Level.INFO, "Reading data for enrolled {0}", id);
            enrolled = ejb.findEnrolledById(studentId, subjectId);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return enrolled;
    }

    /**
     * GET method to find all enrollments: uses findAllEnrolled business logic
     * method.
     *
     * @return a collection of enrollments.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Enrolled> findAll() {
        List<Enrolled> enrollments;
        try {
            LOGGER.log(Level.INFO, "Reading data for all enrollments");
            enrollments = ejb.findAllEnrolled();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return enrollments;
    }

}
