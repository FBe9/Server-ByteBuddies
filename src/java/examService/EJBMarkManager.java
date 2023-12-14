package examService;

import entities.Exam;
import entities.Mark;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alex
 */
public class EJBMarkManager implements MarkInterface{
    
    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @Override
    public void createMark(Mark mark) throws CreateErrorException {
        try{
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
            em.remove(em.merge(mark));
        }catch(Exception ex){
            throw new DeleteErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Exam> findExamsByStudent(String userName) throws FindErrorException {
        Set<Exam> exams;
        
        try{
            exams = new HashSet<>(em.createNamedQuery("findExamsByStudent").setParameter("userName", "%" + userName + "%").getResultList());
            return exams;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }
    
}
