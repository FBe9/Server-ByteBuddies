package studentService;

import entities.Student;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for student EJB.
 * @author irati
 */
@Local
public interface StudentInterface {

    /* Method to create a Student.
     *
     * @param student the Student entity object containing new data.
     * @throws CreateErrorException if there is an error duting create.
     * @throws EmailAlreadyExistsException if there email already exists.
     */
    public void createStudent(Student student) throws CreateErrorException, EmailAlreadyExistsException;

    /**
     * Method to update a student.
     *
     * @param student the Student entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    public void updateStudent(Student student) throws UpdateErrorException;

    /**
     * Method to delete a student.
     *
     * @param student the Student entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    public void deleteStudent(Student student) throws DeleteErrorException;

    /**
     * Method to search for a student by id.
     *
     * @param id the id to make the search.
     * @return a Student entity containing the student data.
     * @throws FindErrorException if there is an error during reading.
     */
    public Student findStudentById(Integer id) throws FindErrorException;

    /**
     * Method to search for all students.
     *
     * @return a collection of students.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<Student> findAllStudents() throws FindErrorException;

}
