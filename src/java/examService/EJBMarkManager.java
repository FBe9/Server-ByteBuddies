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
 * The EJB Manager for the entity Mark.
 *
 * @author Alex
 */
@Stateless
public class EJBMarkManager implements MarkInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    /**
     * Creates a new mark in the database.
     *
     * @param mark The object mark to save.
     * @throws CreateErrorException When an error of creation occurs.
     */
    @Override
    public void createMark(Mark mark) throws CreateErrorException {
        try {
            if (!em.contains(mark)) {
                mark = em.merge(mark);
            }
            em.persist(mark);
        } catch (Exception ex) {
            throw new CreateErrorException(ex.getMessage());
        }
    }

    /**
     * Updates the information of a specific mark.
     *
     * @param mark The mark to update.
     * @throws UpdateErrorException When an update error occurs.
     */
    @Override
    public void updateMark(Mark mark) throws UpdateErrorException {
        try {
            if (!em.contains(mark)) {
                em.merge(mark);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Deletes a mark from the database.
     *
     * @param mark The mark to delete.
     * @throws DeleteErrorException When a deletion error occurs.
     */
    @Override
    public void deleteMark(Mark mark) throws DeleteErrorException {
        try {
            mark = em.merge(mark);
            em.remove(mark);
        } catch (Exception ex) {
            throw new DeleteErrorException(ex.getMessage());
        }
    }

    /**
     * Finds all the marks and sets them in a list.
     *
     * @return A List collection with all the marks.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Mark> findAllMarks() throws FindErrorException {
        List<Mark> marks;
        try {
            marks = em.createNamedQuery("findAllMarks").getResultList();
            return marks;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Finds one mark with the given exam and student IDs.
     *
     * @param examId The exam ID to search.
     * @param studentId The student ID to search.
     * @return The Mark object with the given ID.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public Mark findMarkById(Integer examId, Integer studentId) throws FindErrorException {
        Mark mark = null;
        MarkId markId = new MarkId(examId, studentId);
        try {
            mark = em.find(Mark.class, markId);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return mark;
    }

    /**
     * Finds a mark that belongs to the student of the given String name.
     *
     * @param userName The parameter to search.
     * @return A List collection of all the marks found with the given String.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Exam> findExamsByStudent(String userName) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findExamsByStudent").setParameter("userName", "%" + userName + "%").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Finds all the marks belonging to the exam of the given ID.
     *
     * @param examId The ID to search.
     * @return A List collection of all the marks found with the given ID.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Mark> findMarkByExam(Integer examId) throws FindErrorException {
        List<Mark> marks;

        try {
            marks = em.createNamedQuery("findMarkByExam").setParameter("examid", examId).getResultList();
            return marks;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }
}
