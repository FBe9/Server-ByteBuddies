/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subjectService;

import entities.Subject;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.SubjectNameAlreadyExistsException;
import exceptions.UpdateErrorException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the SubjectInterface.
 *
 * @author Irati
 */
@Stateless
public class EJBSubjectManager implements SubjectInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    /**
     * Method used to create a subject.
     *
     * @param subject the Subject entity object containing new data.
     * @throws CreateErrorException if there is an error during creation.
     * @throws SubjectNameAlreadyExistsException Subject with the same name already exists
     */
    @Override
    public void createSubject(Subject subject) throws CreateErrorException, SubjectNameAlreadyExistsException{
        try {
            em.createNamedQuery("findByName").setParameter("subjectName", subject.getName()).getSingleResult();
            throw new SubjectNameAlreadyExistsException("Subject with the same name already exists");
        } catch (NoResultException ex) {
            try {
                if (!em.contains(subject)) {
                    subject = em.merge(subject);
                }

                em.persist(subject);
            } catch (Exception e) {
                throw new CreateErrorException(e.getMessage());
            }
        }

    }

    /**
     * Method to update a subject.
     *
     * @param subject the Subject entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    @Override
    public void updateSubject(Subject subject) throws UpdateErrorException {
        try {
            if (!em.contains(subject)) {
                em.merge(subject);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Method to delete a subject.
     *
     * @param subject the Subject entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    @Override
    public void deleteSubject(Subject subject) throws DeleteErrorException {
        try {
            em.remove(em.merge(subject));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    /**
     * Method to search for all subjects.
     *
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Subject> findAllSubjects() throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findAllSubjects").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * Method to search subjects by name.
     *
     * @param name string name to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Subject> findSubjectsByName(String name) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findByName").setParameter("subjectName", "%" + name + "%").getResultList();

        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * Method to search subjects by teacher name.
     *
     * @param name string teacher's name to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Subject> findSubjectsByTeacher(String name) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findByTeacherName").setParameter("teacherName", "%" + name + "%").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * Method to search subjects by its init date.
     *
     * @param date date to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Subject> findSubjectsByInitDate(Date date) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findByDateInit").setParameter("subjectDateInit", date).getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * Method to search subjects by its end date.
     *
     * @param date date to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Subject> findSubjectsByEndDate(Date date) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findByDateEnd").setParameter("subjectDateEnd", date).getResultList();

        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subjects;
    }

    /**
     * Method to search for a subject by id.
     *
     * @param id the id to make the search.
     * @return a Subject entity containing the subject data.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Subject findSubjectById(Integer id) throws FindErrorException {
        Subject subject = null;
        try {
            subject = em.find(Subject.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subject;
    }

    /**
     * Finds subjects with a specific number of units, based on the provided
     * comparison operator.
     *
     * @param number The number of units to compare.
     * @return A set of subjects that meet the search criteria.
     * @throws FindErrorException Thrown if there is an error during the search.
     */
    @Override
    public List<Subject> findSubjectsWithXUnits(Long number) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findSubjectsWithXUnits").setParameter("numUnits", number).getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }

    /**
     * Retrieves subjects based on the count of enrolled students.
     *
     * @param number The number for comparison.
     * @return List of subjects meeting the specified enrollment count
     * condition.
     * @throws FindErrorException If an error occurs during retrieval.
     */
    @Override
    public List<Subject> findSubjectsWithEnrolledStudentsCount(Long number) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findSubjectsWithEnrolledStudentsCount").setParameter("numEnrolledStudents", number).getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }

    /**
     * Retrieves a list of subjects based on the enrollments of a student
     * identified by the provided student ID.
     *
     * @param studentId The unique identifier of the student for whom subjects
     * are to be retrieved.
     * @return A List of Subject objects representing the subjects associated
     * with the specified student.
     * @throws FindErrorException If an error occurs while attempting to find
     * and retrieve the subjects.
     */
    @Override
    public List<Subject> findByEnrollments(Integer studentId) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findByEnrollments").setParameter("studentId", studentId).getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }

    /**
     * Retrieves a list of subjects taught by a teacher identified by the
     * provided teacher ID.
     *
     * @param teacherId The unique identifier of the teacher for whom subjects
     * are to be retrieved.
     * @return A List of Subject objects representing the subjects taught by the
     * specified teacher.
     * @throws FindErrorException If an error occurs while attempting to find
     * and retrieve the subjects.
     */
    @Override
    public List<Subject> findSubjectsByTeacherId(Integer teacherId) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findSubjectsByTeacherId").setParameter("teacherId", teacherId).getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }
}
