package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * The EJB Manager for the entity Exam.
 *
 * @author Alex
 */
@Stateless
public class EJBExamManager implements ExamInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    /**
     * Creates an exam in the database.
     *
     * @param exam The new exam to add.
     * @throws CreateErrorException When a creation error occurs.
     */
    @Override
    public void createExam(Exam exam) throws CreateErrorException {
        if (!em.contains(exam)) {
            exam = em.merge(exam);
        }
        em.persist(exam);
    }

    /**
     * Updates the information of the given exam.
     *
     * @param exam The exam to update.
     * @throws UpdateErrorException When an update error occurs.
     */
    @Override
    public void updateExam(Exam exam) throws UpdateErrorException {
        try {
            if (!em.contains(exam)) {
                em.merge(exam);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Updates the subject value of an exam to null.
     *
     * @param id The ID of the exam to modify.
     * @throws UpdateErrorException When an update error occurs.
     */
    @Override
    public void updateNullSubject(Integer id) throws UpdateErrorException {
        Exam exam;
        try {
            exam = findExamById(id);
            if (!em.contains(exam)) {
                em.createNamedQuery("setNullSubject").setParameter("examId", "%" + id + "%");
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Deletes an exam from the database.
     *
     * @param exam The exam to delete
     * @throws DeleteErrorException When a deletion error occurs.
     */
    @Override
    public void deleteExam(Exam exam) throws DeleteErrorException {
        try {
            exam = em.merge(exam);
            em.remove(exam);
        } catch (Exception ex) {
            throw new DeleteErrorException(ex.getMessage());
        }
    }

    /**
     * Finds all the exams.
     *
     * @return A List collection of all the exams found.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Exam> findAllExams() throws FindErrorException {
        List<Exam> exams;
        try {
            exams = em.createNamedQuery("findAllExams").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Finds an exam with the given ID.
     *
     * @param id The ID to search.
     * @return The exam found with the given ID.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public Exam findExamById(Integer id) throws FindErrorException {
        Exam exam = null;
        try {
            exam = em.find(Exam.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return exam;
    }

    /**
     * Finds all the exams containing the given String in their "Description"
     * attribute.
     *
     * @param description The given String to search.
     * @return A List collection of all the exams found.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Exam> findByDescription(String description) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findByDescription").setParameter("examDescription", "%" + description + "%").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Finds all the exams with a specific path to the solution file. ONLY
     * returns a value if the path is NULL.
     *
     * @param solutionFilePath The path to search.
     * @return A List collection of all the exams found with the given String.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Exam> findBySolution(String solutionFilePath) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findBySolution").setParameter("solutionFilePath", "%" + solutionFilePath + "%").getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Finds all the exams belonging to the subject of the given ID.
     *
     * @param subjectId The Subject ID to search for.
     * @return A List collection of all the exams found.
     * @throws FindErrorException When a find error occurs.
     */
    @Override
    public List<Exam> findBySubject(Integer subjectId) throws FindErrorException {
        List<Exam> exams;

        try {
            exams = em.createNamedQuery("findBySubject").setParameter("subjectId", subjectId).getResultList();
            return exams;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }
}
