package subjectService;

import entities.Enrolled;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author irati
 */
public class EJBEnrolledManager implements EnrolledInterface {
    @PersistenceContext(unitName = "Server-ByteBuddiesPU")
    private EntityManager em;
    
    private Set<Enrolled> subjectMatriculated;
    
    @Override
    public void createEnrolled(Enrolled enrolled) throws CreateErrorException {
        try{
            em.persist(enrolled);
        }catch(Exception ex){
            throw new CreateErrorException(ex.getMessage());
        }
       
    }

    @Override
    public void updateEnrolled(Enrolled enrolled) throws UpdateErrorException {
         try{
             if (!em.contains(enrolled)) {
                em.merge(enrolled);
            }
            em.flush();
       }catch(Exception ex){
           throw new UpdateErrorException(ex.getMessage());
       }
    }

    @Override
    public void deleteEnrolled(Enrolled enrolled) throws DeleteErrorException {
       //Mirar
        try{
            em.remove(em.merge(enrolled));
        }catch(Exception e){
            throw new DeleteErrorException(e.getMessage());
        }
    }

    @Override
    public Set<Enrolled> findEnrolled(Boolean isMatriculated) throws FindErrorException {
        try{
            subjectMatriculated = (Set<Enrolled>) em.createNamedQuery("findAllSubjects").getResultList();
            return  subjectMatriculated;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }
    
}
