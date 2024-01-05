package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
@Stateless
public class EJBExamManager implements ExamInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @Override
    public void createExam(Exam exam) throws CreateErrorException {
        try {
            em.persist(exam);
        } catch (Exception ex) {
            throw new CreateErrorException(ex.getMessage());
        }
    }

    @Override
    public void updateExam(Exam exam) throws UpdateErrorException {
        try {
            if (!em.contains(exam)) {
                em.merge(exam);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }
    
    @Override
    public void updateNullSubject(Integer id) throws UpdateErrorException {
        Exam exam;
        try{
            exam = findExamById(id);
            if(!em.contains(exam)){
                em.createNamedQuery("setNullSubject").setParameter("examId", "%" + id + "%");
            }
            em.flush();
        } catch(Exception ex){
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    @Override
    public void deleteExam(Exam exam) throws DeleteErrorException {
        try {
            exam = em.merge(exam);
            em.remove(exam);
        } catch (Exception ex) {
            throw new DeleteErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findAllExams() throws FindErrorException {
        List<Exam> exams;
        try {
            exams = em.createNamedQuery("findAllExams").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Exam findExamById(Integer id) throws FindErrorException {
        Exam exam = null;
        try {
            exam = em.find(Exam.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return exam;
    }

    @Override
    public List<Exam> findByDescription(String description) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findByDescription").setParameter("examDescription", "%" + description + "%").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findBySolution(String solutionFilePath) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findBySolution").setParameter("solutionFilePath", "%" + solutionFilePath + "%").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findBySubject(String subject) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findBySubject").setParameter("subjectName", "%" + subject + "%").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

}
