package service;

import entities.Exam;
import examService.ExamInterface;
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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * RESTful web service for managing exams.
 *
 * @author Alex
 */
@Path("entities.exam")
public class ExamFacadeREST {

    /**
     * EJB object for business logic.
     */
    @EJB
    private ExamInterface ejb;

    /**
     * Logger for ExamFacadeREST class
     */
    private Logger LOGGER = Logger.getLogger(ExamFacadeREST.class.getName());

    /**
     * POST method to create exams.
     *
     * @param exam The exam to post.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createExam(Exam exam) {
        try {
            LOGGER.log(Level.INFO, "Creating exam {0}", exam.getId());
            ejb.createExam(exam);
        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update an exam.
     *
     * @param exam The exam to update.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateExam(Exam exam) {
        try {
            LOGGER.log(Level.INFO, "Updating exam {0}", exam.getId());
            ejb.updateExam(exam);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method by exam ID to set subject value to null.
     *
     * @param id The exam ID.
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateNullSubject(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Setting subject_id to null");
            ejb.updateNullSubject(id);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * DELETE method to remove an exam.
     *
     * @param id The id of the exam to remove.
     */
    @DELETE
    @Path("{id}")
    public void deleteExam(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Deleting exam {0}", id);
            //updateNullSubject(id);
            ejb.deleteExam(ejb.findExamById(id));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method by ID to find an exam with a given ID.
     *
     * @param id The exam ID to search for.
     * @return The exam found with the given ID.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Exam find(@PathParam("id") Integer id) {
        Exam exam;
        try {
            LOGGER.log(Level.INFO, "Searching for exam {0}", id);
            exam = ejb.findExamById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return exam;
    }

    /**
     * GET method to find all exams.
     *
     * @return A List collection of all exams found.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exam> findAll() {
        List<Exam> exams;
        try {
            LOGGER.log(Level.INFO, "Searching for all exams");
            exams = ejb.findAllExams();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return exams;
    }

    /**
     * GET method to find an exam by its description.
     *
     * @param description The String to search for.
     * @return A List collection of all found exams containing the given
     * description string.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findByDescription/{description}")
    public List<Exam> findByDescription(@PathParam("description") String description) {
        List<Exam> exams;
        try {
            LOGGER.log(Level.INFO, "Searching for exam with desc {0}", description);
            exams = ejb.findByDescription(description);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return exams;
    }

    /**
     * GET method to find exams without a solution, exams with a null solution
     * file path.
     *
     * @param solutionFilePath The parameter to search for.
     * @return A List collection containing all exams without a solution.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findByNullSolution/{solutionFilePath}")
    public List<Exam> findBySolution(@PathParam("solutionFilePath") String solutionFilePath) {
        List<Exam> exams;
        try {
            LOGGER.log(Level.INFO, "Searching for all exam with {0} file path", solutionFilePath);
            exams = ejb.findBySolution(solutionFilePath);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return exams;
    }

    /**
     * GET method to find all exams of a given subject.
     *
     * @param subjectId The ID of the subject to search for.
     * @return A List collection of all the found exams.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findBySubject/{subject}")
    public List<Exam> findBySubject(@PathParam("subject") Integer subjectId) {
        List<Exam> exams;
        try {
            LOGGER.log(Level.INFO, "Searching for exam with desc {0}", subjectId);
            exams = ejb.findBySubject(subjectId);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return exams;
    }
}
