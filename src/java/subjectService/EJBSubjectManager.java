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
     */
    @Override
    public void createSubject(Subject subject) throws CreateErrorException {
        try {
            em.createNamedQuery("findByName").setParameter("subjectName", subject.getName()).getSingleResult();
        } catch (NoResultException ex) {
            try {
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
        //Mirar
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
     * Method to search subjects by language.
     *
     * @param language string to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Subject> findSubjectsByLanguage(String language) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findByLanguage").setParameter("subjectLanguage", "%" + language + "%").getResultList();
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
     * @param comparisonOperator The comparison operator to use.
     * @return A set of subjects that meet the search criteria.
     * @throws FindErrorException Thrown if there is an error during the search.
     */
    @Override
    public List<Subject> findSubjectsWithXUnits(Integer number) throws FindErrorException {
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
     * @param comparisonOperator The operator for comparison.
     * @return List of subjects meeting the specified enrollment count
     * condition.
     * @throws FindErrorException If an error occurs during retrieval.
     */
    @Override
    public List<Subject> findSubjectsWithEnrolledStudentsCount(Integer number) throws FindErrorException {
        List<Subject> subjects;
        try {
            subjects = em.createNamedQuery("findSubjectsWithEnrolledStudentsCount").setParameter("numUnits", number).getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }
}
