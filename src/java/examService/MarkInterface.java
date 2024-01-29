package examService;

import entities.Exam;
import entities.Mark;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for Mark EJB.
 *
 * @author Alex
 */
@Local
public interface MarkInterface {

    /**
     * Creates a new mark in the database.
     *
     * @param mark The object mark to save.
     * @throws CreateErrorException When an error of creation occurs.
     */
    public void createMark(Mark mark) throws CreateErrorException;

    /**
     * Updates the information of a specific mark.
     *
     * @param mark The mark to update.
     * @throws UpdateErrorException When an update error occurs.
     */
    public void updateMark(Mark mark) throws UpdateErrorException;

    /**
     * Deletes a mark from the database.
     *
     * @param mark The mark to delete.
     * @throws DeleteErrorException When a deletion error occurs.
     */
    public void deleteMark(Mark mark) throws DeleteErrorException;

    /**
     * Finds all the marks and sets them in a list.
     *
     * @return A List collection with all the marks.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Mark> findAllMarks() throws FindErrorException;

    /**
     * Finds one mark with the given exam and student IDs.
     *
     * @param examId The exam ID to search.
     * @param studentId The student ID to search.
     * @return The Mark object with the given ID.
     * @throws FindErrorException When a find error occurs.
     */
    public Mark findMarkById(Integer examId, Integer studentId) throws FindErrorException;

    /**
     * Finds a mark that belongs to the student of the given String name.
     *
     * @param userName The parameter to search.
     * @return A List collection of all the marks found with the given String.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Exam> findExamsByStudent(String userName) throws FindErrorException;

    /**
     * Finds all the marks belonging to the exam of the given ID.
     *
     * @param examId The ID to search.
     * @return A List collection of all the marks found with the given ID.
     * @throws FindErrorException When a find error occurs.
     */
    public List<Mark> findMarkByExam(Integer examId) throws FindErrorException;
}
