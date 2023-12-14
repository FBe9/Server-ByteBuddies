package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
public class EJBExamManager implements ExamInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;
    
    

    @Override
    public void createExam(Exam exam) throws CreateErrorException{
        try{
            em.persist(exam);
        }catch(Exception ex){
            throw new CreateErrorException(ex.getMessage());
        }
    }

    @Override
    public void updateExam(Exam exam) throws UpdateErrorException{
        try{
            if(!em.contains(exam)){
                em.merge(exam);
            }
            em.flush();
        }catch (Exception ex){
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    @Override
    public void deleteExam(Exam exam) throws DeleteErrorException{
        try{
            em.remove(em.merge(exam));
        } catch(Exception ex){
            throw new DeleteErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findAllExams() throws FindErrorException{
        List<Exam> exams;
        try{
            exams = em.createNamedQuery("findAllExams").getResultList();
            return exams;
        }catch (Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findByDescription(String description) throws FindErrorException{
        List<Exam> exams;
        
        try{
            exams = em.createNamedQuery("findByDescription").setParameter("examDescription", "%" + description + "%").getResultList();
            return exams;
        }catch (Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findAndOrderByDuration() throws FindErrorException{
        List<Exam> exams;
        
        try{
            exams = em.createNamedQuery("findAndOrderByDuration").getResultList();
            return exams;
        } catch (Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public List<Exam> findByNullSolution() throws FindErrorException {
        List<Exam> exams;
        
        try{
            exams = em.createNamedQuery("findByNullSolution").getResultList();
            return exams;
        } catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

}
