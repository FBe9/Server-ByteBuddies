package subjectService;


import entities.Enrolled;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.Set;

/**
 *
 * @author irati
 */
public interface EnrolledInterface {
    public void createEnrolled(Enrolled enrolled) throws CreateErrorException;
    public void updateEnrolled(Enrolled enrolled) throws UpdateErrorException;
    public void deleteEnrolled(Enrolled enrolled) throws DeleteErrorException;
    public Set<Enrolled> findEnrolled(Boolean isMatriculated) throws FindErrorException;
}
