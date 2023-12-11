package subjectService;


import entities.Enrolled;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.Set;

/**
 * Interface defining operations for managing enrollments.
 * @author Irati
 */
public interface EnrolledInterface {
    /**
     * Creates a new enrollment with the provided data.
     *
     * @param enrolled the entity object representing the new enrollment.
     * @throws CreateErrorException if there is an error during the creation process.
     */
    public void createEnrolled(Enrolled enrolled) throws CreateErrorException;

    /**
     * Updates an existing enrollment with the provided data.
     *
     * @param enrolled the entity object representing the updated enrollment.
     * @throws UpdateErrorException if there is an error during the update process.
     */
    public void updateEnrolled(Enrolled enrolled) throws UpdateErrorException;

    /**
     * Deletes the specified enrollment.
     *
     * @param enrolled the entity object representing the enrollment to be deleted.
     * @throws DeleteErrorException if there is an error during the deletion process.
     */
    public void deleteEnrolled(Enrolled enrolled) throws DeleteErrorException;

    /**
     * Finds enrollments based on the matriculation status.
     *
     * @param isMatriculated the matriculation status to filter enrollments.
     * @return a set of enrollments matching the specified matriculation status.
     * @throws FindErrorException if there is an error during the retrieval process.
     */
    public Set<Enrolled> findEnrolled(Boolean isMatriculated) throws FindErrorException;
}
