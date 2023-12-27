package subjectService;

import entities.Enrolled;
import entities.EnrolledId;
import entities.Student;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the EnrolledInterface.
 *
 * @author Irati
 */
@Stateless
public class EJBEnrolledManager implements EnrolledInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

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
            if (!em.contains(enrolled)) {
                enrolled = em.merge(enrolled);
            }
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
     * @return a set of enrollments matching the specified matriculation status.
     * @throws FindErrorException if there is an error during the retrieval
     * process.
     */
    @Override
    public List<Enrolled> findAllEnrolled() throws FindErrorException {
        List<Enrolled> enrollments;
        try {
            enrollments = em.createNamedQuery("findAllEnrollments").getResultList();

        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return enrollments;
    }

    /**
     * Finds an enrollment by its ID.
     *
     * @param id The ID of the enrollment to find.
     * @return The enrolled entity with the specified ID.
     * @throws FindErrorException If an error occurs while attempting to find
     * the enrollment.
     */
    @Override
    public Enrolled findEnrolledById(Integer studentId, Integer subjectId) throws FindErrorException {
        Enrolled enrolled = null;
        EnrolledId id = new EnrolledId(studentId, subjectId);
        try {
            enrolled = em.find(Enrolled.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return enrolled;
    }

}
