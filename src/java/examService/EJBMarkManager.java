package examService;

import entities.Exam;
import entities.Mark;
import entities.MarkId;
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
public class EJBMarkManager implements MarkInterface{
    
    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @Override
    public void createMark(Mark mark) throws CreateErrorException {
        try{
            if (!em.contains(mark)) {
                mark = em.merge(mark);
            }
            em.persist(mark);
        }catch (Exception ex){
            throw new CreateErrorException(ex.getMessage());
        }
    }

    @Override
    public void updateMark(Mark mark) throws UpdateErrorException {
        try{
            if(!em.contains(mark)){
                em.merge(mark);
            }
            em.flush();
        }catch(Exception ex){
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    @Override
    public void deleteMark(Mark mark) throws DeleteErrorException {
        try{
            mark = em.merge(mark);
            em.remove(mark);
        }catch(Exception ex){
            throw new DeleteErrorException(ex.getMessage());
        }
    }
    
    @Override
    public List<Mark> findAllMarks() throws FindErrorException {
        List<Mark> marks;
        try{
            marks = em.createNamedQuery("findAllMarks").getResultList();
            return marks;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }
    
    @Override
    public Mark findMarkById(Integer examId, Integer studentId) throws FindErrorException {
        Mark mark = null;
        MarkId markId = new MarkId(examId, studentId);
        try{
            mark = em.find(Mark.class, markId);
        } catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
        return mark;
    }
    
    

    @Override
    public List<Exam> findExamsByStudent(String userName) throws FindErrorException {
        List<Exam> exams;
        
        try{
            exams = em.createNamedQuery("findExamsByStudent").setParameter("userName", "%" + userName + "%").getResultList();
            return exams;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }
    
    @Override
    public List<Mark> findMarkByExam(Integer examId) throws FindErrorException {
        List<Mark> marks;
        
        try{
            marks = em.createNamedQuery("findMarkByExam").setParameter("examid", examId).getResultList();
            return marks;
        } catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }
    
}
