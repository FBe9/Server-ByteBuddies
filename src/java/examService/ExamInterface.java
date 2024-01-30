package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for Exam EJB.
 *
 * @author Alex
 */
@Local
public interface ExamInterface {

    /**
     * Creates a new exam in the database.
     *
     * @param exam The object exam to save.
     * @throws CreateErrorException When an error of creation occurs.
     */
    public void createExam(Exam exam) throws CreateErrorException;

    /**
     * Updates the information of a specific exam.
     *
     * @param exam The exam to update.
     * @throws UpdateErrorException When an update error occurs.
     */
    public void updateExam(Exam exam) throws UpdateErrorException;

    /**
     * Sets the subject to null in an exam.
     *
     * @param id The id of the exam to modify.
     * @throws UpdateErrorException When an update error occurs.
     */
    public void updateNullSubject(Integer id) throws UpdateErrorException;

    /**
     * Deletes an exam from the database.
     *
     * @param exam The exam to delete.
     * @throws DeleteErrorException When a deletion error occurs.
     */
    public void deleteExam(Exam exam) throws DeleteErrorException;

    /**
     * Finds all the exams and sets them in a list.
     *
     * @return A List collection with all the exams.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Exam> findAllExams() throws FindErrorException;

    /**
     * Finds one exam with the given ID.
     *
     * @param id The ID to search.
     * @return The Exam object with the given ID.
     * @throws FindErrorException When a find error occurs.
     */
    public Exam findExamById(Integer id) throws FindErrorException;

    /**
     * Finds an exam that includes the given String in it's "Description"
     * attribute.
     *
     * @param description The parameter to search.
     * @return A List collection of all the exams found with the given String.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Exam> findByDescription(String description) throws FindErrorException;

    /**
     * Finds all the exams with a specific path to the solution file. ONLY
     * returns a value if the path is NULL.
     *
     * @param solutionFilePath The path to search.
     * @return A List collection of all the exams found with the given String.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Exam> findBySolution(String solutionFilePath) throws FindErrorException;

    /**
     * Finds all the exams belonging to a subject with the given Subject ID.
     *
     * @param subjectId The subject ID to search.
     * @return A List collection of all the exams found with the given subject
     * ID.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Exam> findBySubject(Integer subjectId) throws FindErrorException;
}
