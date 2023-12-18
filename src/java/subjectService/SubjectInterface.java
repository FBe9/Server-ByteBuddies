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
import javax.ejb.Local;

/**
 * Local interface for subject EJB.
 *
 * @author irati
 */
@Local
public interface SubjectInterface {

    /**
     * Method used to create a subject.
     *
     * @param subject the Subject entity object containing new data.
     * @throws CreateErrorException if there is an error during creation.
     */
    public void createSubject(Subject subject) throws CreateErrorException;

    /**
     * Method to update a subject.
     *
     * @param subject the Subject entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    public void updateSubject(Subject subject) throws UpdateErrorException;

    /**
     * Method to delete a subject.
     *
     * @param subject the Subject entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    public void deleteSubject(Subject subject) throws DeleteErrorException;

    /**
     * Method to search for a subject by id.
     *
     * @param id the id to make the search.
     * @return a Subject entity containing the subject data.
     * @throws FindErrorException if there is an error during reading.
     */
    public Subject findSubjectById(Integer id) throws FindErrorException;

    /**
     * Method to search for all subjects.
     *
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Subject> findAllSubjects() throws FindErrorException;

    /**
     * Method to search subjects by name.
     *
     * @param name string name to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Subject> findSubjectsByName(String name) throws FindErrorException;

    /**
     * Method to search subjects by hours.
     *
     * @param hours integer hours to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Subject> findSubjectsByTeacher(String name) throws FindErrorException;

    /**
     * Method to search subjects by its init date.
     *
     * @param date date to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Subject> findSubjectsByInitDate(Date date) throws FindErrorException;

    /**
     * Method to search subjects by language.
     *
     * @param language string to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Subject> findSubjectsByLanguage(String language) throws FindErrorException;

    /**
     * Method to search subjects by its end date.
     *
     * @param date date to make the search.
     * @return a collection of subjects.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Subject> findSubjectsByEndDate(Date date) throws FindErrorException;

    /**
     * Finds subjects with a specific number of units, based on the provided
     * comparison operator.
     *
     * @param number The number of units to compare.
     * @return A set of subjects that meet the search criteria.
     * @throws FindErrorException Thrown if there is an error during the search.
     */
    public List<Subject> findSubjectsWithXUnits(Integer number) throws FindErrorException;

    /**
     * Retrieves subjects based on the count of enrolled students.
     *
     * @param number The number for comparison.
     * @return List of subjects meeting the specified enrollment count
     * condition.
     * @throws FindErrorException If an error occurs during retrieval.
     */
    public List<Subject> findSubjectsWithEnrolledStudentsCount(Integer number) throws FindErrorException;

    public List<Subject> findByEnrollments(Integer studentId) throws FindErrorException;
}
