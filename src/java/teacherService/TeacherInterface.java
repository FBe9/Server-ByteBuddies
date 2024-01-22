/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherService;

import entities.Teacher;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;

/**
 *
 * @author irati
 */
public interface TeacherInterface {
      /**
     * Method to create a teacher.
     *
     * @param teacher the Teacher entity object containing new data.
     * @throws CreateErrorException if there is an error duting create.
     * @throws EmailAlreadyExistsException if there email already exists.
     */
     public void createTeacher(Teacher teacher) throws CreateErrorException, EmailAlreadyExistsException;
     /**
     * Method to update a teacher.
     *
     * @param teacher the Teacher entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    public void updateTeacher(Teacher teacher) throws UpdateErrorException;

    /**
     * Method to delete a teacher.
     *
     * @param teacher the Teacher entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    public void deleteTeacher(Teacher teacher) throws DeleteErrorException;

    /**
     * Method to search for a teacher by id.
     *
     * @param id the id to make the search.
     * @return a Teacher entity containing the teacher data.
     * @throws FindErrorException if there is an error during reading.
     */
    public Teacher findTeacherById(Integer id) throws FindErrorException;

    /**
     * Method to search for all teachers.
     *
     * @return a collection of teachers.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Teacher> findAllTeachers() throws FindErrorException;
}
