package service;

import entities.Exam;
import entities.Mark;
import entities.MarkId;
import examService.MarkInterface;
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

/**
 *
 * @author Alex
 */
@Path("entities.mark")
public class MarkFacadeREST {

    @EJB
    private MarkInterface ejb;

    private Logger LOGGER = Logger.getLogger(ExamFacadeREST.class.getName());

    private MarkId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;examId=examIdValue;studentId=studentIdValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        entities.MarkId key = new entities.MarkId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> examId = map.get("examId");
        if (examId != null && !examId.isEmpty()) {
            key.setExamId(new java.lang.Integer(examId.get(0)));
        }
        java.util.List<String> studentId = map.get("studentId");
        if (studentId != null && !studentId.isEmpty()) {
            key.setStudentId(new java.lang.Integer(studentId.get(0)));
        }
        return key;
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createMark(Mark mark) {
        try {
            LOGGER.log(Level.INFO, "Creating mark {0}", mark.getId());
            ejb.createMark(mark);
        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateMark(Mark mark) {
        try {
            LOGGER.log(Level.INFO, "Updating info in mark {0}", mark.getId());
            ejb.updateMark(mark);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{examId}/{studentId}")
    public void deleteMark(@PathParam("examId") Integer examId, @PathParam("studentId") Integer studentId) {
        try {
            LOGGER.log(Level.INFO, "Deleting mark with exam {0} and student {1}", examId + studentId);
            ejb.deleteMark(ejb.findMarkById(examId, studentId));
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{examId}/{studentId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Mark find(@PathParam("examId") Integer examId, @PathParam("studentId") Integer studentId) {
        Mark mark = null;
        try {
            LOGGER.log(Level.INFO, "Finding mark with exam {0} and student {1}", examId + studentId);
            mark = ejb.findMarkById(examId, studentId);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return mark;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mark> findAll() {
        List<Mark> marks;
        try{
            LOGGER.log(Level.INFO, "Finding all marks");
            marks = ejb.findAllMarks();
        }catch(FindErrorException ex){
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return marks;
    }

    @GET
    @Path("findExamsByStudent/{userName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exam> findExamsByStudent(@PathParam("userName") String userName) {
        List<Exam> exams;
        try{
            LOGGER.log(Level.INFO, "Finding the exams of {0}", userName);
            exams = ejb.findExamsByStudent(userName);
        }catch(FindErrorException ex){
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exams;
    }
    
    @GET
    @Path("findMarkByExam/{examId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mark> findMarkByExam(@PathParam("examId") Integer examId){
        List<Mark> marks;
        try{
            LOGGER.log(Level.INFO, "Finding all marks of exam with id {0}", examId);
            marks = ejb.findMarkByExam(examId);
        } catch(FindErrorException ex){
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return marks;
    }
}
