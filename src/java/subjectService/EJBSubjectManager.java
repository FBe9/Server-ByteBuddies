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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author irati
 */
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
            em.persist(subject);
        } catch (Exception ex) {
            throw new CreateErrorException(ex.getMessage());
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
    public Set<Subject> findAllSubjects() throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<>(em.createNamedQuery("findAllSubjects").getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search subjects by name.
     *
     * @param name string name to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Set<Subject> findSubjectsByName(String name) throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<>(em.createNamedQuery("findByName").setParameter("subjectName", "%" + name + "%").getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search subjects by hours.
     *
     * @param hours integer hours to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Set<Subject> findSubjectsByHours(Integer hours) throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<>( em.createNamedQuery("findByHours").setParameter("subjectHours", hours).getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search subjects by teacher name.
     *
     * @param name string teacher's name to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Set<Subject> findSubjectsByTeacher(String name) throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<>( em.createNamedQuery("findByTeacherName").setParameter("teacherName", "%" + name + "%").getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search subjects by its init date.
     *
     * @param date date to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Set<Subject> findSubjectsByInitDate(Date date) throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<> (em.createNamedQuery("findByDateInit").setParameter("subjectDateInit", date).getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search subjects by language.
     *
     * @param language string to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Set<Subject> findSubjectsByLanguage(String language) throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<> ( em.createNamedQuery("findByLanguage").setParameter("subjectLanguage", "%" + language + "%").getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search subjects by its end date.
     *
     * @param date date to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Set<Subject> findSubjectsByEndDate(Date date) throws FindErrorException {
        Set<Subject> subjects;
        try {
            subjects = new HashSet<>( em.createNamedQuery("findByDateEnd").setParameter("subjectDateEnd", date).getResultList());
            return subjects;
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
    }

    /**
     * Method to search for a subject by id.
     *
     * @param id the id to make the search.
     * @return a Subject entity containing the subject data.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Subject findSubjectById(String id) throws FindErrorException {
        Subject subject = null;
        try {
            em.find(Subject.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return subject;
    }

    @Override
    public Set<Subject> findSubjectsWithXUnits(Integer number, String comparisonOperator) throws FindErrorException {
        Set<Subject> subjects;
        String comparison;
        if ("=".equals(comparisonOperator)) {
            comparison = "=";
        } else if (">".equals(comparisonOperator)) {
            comparison = ">";
        } else {
            comparison = "<";
        }
        try {
            subjects = new HashSet<> (em.createNamedQuery("findSubjectsWithXUnits").setParameter("comparisonOperator", comparison).setParameter("numUnits", number).getResultList());
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }

    @Override
    public Set<Subject> findSubjectsWithXExamDuration(Integer number, String comparisonOperator) throws FindErrorException {
        Set<Subject> subjects;
        String comparison;
        if ("=".equals(comparisonOperator)) {
            comparison = "=";
        } else if (">".equals(comparisonOperator)) {
            comparison = ">";
        } else {
            comparison = "<";
        }
        try {
            subjects = new HashSet<> (em.createNamedQuery("findSubjectsWithExamsDuration").setParameter("comparisonOperator", comparison).setParameter("numExamDuration", number).getResultList());
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }

    @Override
    public Set<Subject> findSubjectsWithXStudents(Integer number, String comparisonOperator) throws FindErrorException {
       Set<Subject> subjects;
        String comparison;
        if ("=".equals(comparisonOperator)) {
            comparison = "=";
        } else if (">".equals(comparisonOperator)) {
            comparison = ">";
        } else {
            comparison = "<";
        }
        try {
            subjects = new HashSet<> (em.createNamedQuery("findSubjectsWithStudentsCount").setParameter("comparisonOperator", comparison).setParameter("numStudents", number).getResultList());
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }

        return subjects;
    }

}
