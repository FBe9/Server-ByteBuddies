package subjectService;

import entities.Enrolled;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for enrolled EJB.
 *
 * @author Irati
 */
@Local
public interface EnrolledInterface {

    /**
     * Creates a new enrollment with the provided data.
     *
     * @param enrolled the entity object representing the new enrollment.
     * @throws CreateErrorException if there is an error during the creation
     * process.
     */
    public void createEnrolled(Enrolled enrolled) throws CreateErrorException;

    /**
     * Updates an existing enrollment with the provided data.
     *
     * @param enrolled the entity object representing the updated enrollment.
     * @throws UpdateErrorException if there is an error during the update
     * process.
     */
    public void updateEnrolled(Enrolled enrolled) throws UpdateErrorException;

    /**
     * Deletes the specified enrollment.
     *
     * @param enrolled the entity object representing the enrollment to be
     * deleted.
     * @throws DeleteErrorException if there is an error during the deletion
     * process.
     */
    public void deleteEnrolled(Enrolled enrolled) throws DeleteErrorException;

    /**
     * Finds enrollments based on the matriculation status.
     *
     * @param isMatriculated the matriculation status to filter enrollments.
     * @return a set of enrollments matching the specified matriculation status.
     * @throws FindErrorException if there is an error during the retrieval
     * process.
     */
    /**
     * Retrieves a list of all enrollments.
     *
     * @return A list of enrolled entities.
     * @throws FindErrorException If an error occurs while attempting to find
     * enrollments.
     */
    public List<Enrolled> findAllEnrolled() throws FindErrorException;

    /**
     * Finds an enrollment by its ID.
     *
     * @param id The ID of the enrollment to find.
     * @return The enrolled entity with the specified ID.
     * @throws FindErrorException If an error occurs while attempting to find
     * the enrollment.
     */
    public Enrolled findEnrolledById(Integer id) throws FindErrorException;

    /**
     * Finds all matriculated enrollments for a given student.
     *
     * @param studentId The ID of the student for whom to find matriculated
     * enrollments.
     * @return A list of matriculated enrollments for the specified student.
     * @throws FindErrorException If an error occurs while attempting to find
     * matriculated enrollments.
     */
    public List<Enrolled> findMatriculated(Integer studentId) throws FindErrorException;

}
