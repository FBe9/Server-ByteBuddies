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

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    private Set<Enrolled> subjectMatriculated;

    /**
     * Creates a new enrollment with the provided data.
     *
     * @param enrolled the entity object representing the new enrollment.
     * @throws CreateErrorException if there is an error during the creation
     * process.
     */
    @Override
    public void createEnrolled(Enrolled enrolled) throws CreateErrorException {
        try {
            em.persist(enrolled);
        } catch (Exception ex) {
            throw new CreateErrorException(ex.getMessage());
        }

    }

    /**
     * Updates an existing enrollment with the provided data.
     *
     * @param enrolled the entity object representing the updated enrollment.
     * @throws UpdateErrorException if there is an error during the update
     * process.
     */
    @Override
    public void updateEnrolled(Enrolled enrolled) throws UpdateErrorException {
        try {
            if (!em.contains(enrolled)) {
                em.merge(enrolled);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Deletes the specified enrollment.
     *
     * @param enrolled the entity object representing the enrollment to be
     * deleted.
     * @throws DeleteErrorException if there is an error during the deletion
     * process.
     */
    @Override
    public void deleteEnrolled(Enrolled enrolled) throws DeleteErrorException {
        //Mirar
        try {
            em.remove(em.merge(enrolled));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    /**
     * Finds enrollments based on the matriculation status.
     *
     * @param isMatriculated the matriculation status to filter enrollments.
     * @return a set of enrollments matching the specified matriculation status.
     * @throws FindErrorException if there is an error during the retrieval
     * process.
     */
    @Override
    public Set<Enrolled> findEnrolled(Boolean isMatriculated) throws FindErrorException {
        try {
            subjectMatriculated = (Set<Enrolled>) em.createNamedQuery("findAllSubjects").getResultList();
            return subjectMatriculated;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

}
