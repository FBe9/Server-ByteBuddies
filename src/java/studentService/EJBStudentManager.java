package studentService;

import encrypt.AsimetricaServer;
import entities.Student;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.EncryptException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the StudentInterface.
 *
 * @author irati
 */
@Stateless
public class EJBStudentManager implements StudentInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    /**
     * Method to create a Student.
     *
     * @param student the Student entity object containing new data.
     * @return a student.
     * @throws CreateErrorException if there is an error duting create.
     * @throws EmailAlreadyExistsException if there email already exists.
     * @throws EncryptException error during encryption.
     */
    @Override
    public Student createStudent(Student student) throws CreateErrorException, EmailAlreadyExistsException, EncryptException {
        try {
            // Check if the email already exists using EntityManager
            em.createNamedQuery("findByEmailStudent", Student.class)
                    .setParameter("userEmail", student.getEmail())
                    .getSingleResult();

            // If the email exists, throw an exception
            throw new EmailAlreadyExistsException("User with email already exists");
        } catch (NoResultException ex) {
            try {
                // Continue with the student creation process
                String passwordClient = AsimetricaServer.decryptData(student.getPassword());
                String hash = AsimetricaServer.hashText(passwordClient);

                student.setPassword(hash);
                if (!em.contains(student)) {
                    student = em.merge(student);
                }
                em.persist(student);
                return student;
            } catch (EncryptException et) {
                throw new EncryptException(et.getMessage());
            } catch (Exception e) {
                throw new CreateErrorException(e.getMessage());
            }
        }

    }

    /**
     * Method to update a student.
     *
     * @param student the Student entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    @Override
    public void updateStudent(Student student) throws UpdateErrorException {
        try {
            if (!em.contains(student)) {
                em.merge(student);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Method to delete a student.
     *
     * @param student the Student entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    @Override
    public void deleteStudent(Student student) throws DeleteErrorException {
        try {
            em.remove(em.merge(student));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    /**
     * Method to search for a student by id.
     *
     * @param id the id to make the search.
     * @return a Student entity containing the student data.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Student findStudentById(Integer id) throws FindErrorException {
        Student student;
        try {
            student = em.find(Student.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return student;
    }

    /**
     * Method to search for all students.
     *
     * @return a collection of students.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Student> findAllStudents() throws FindErrorException {
        List<Student> students;
        try {
            students = em.createNamedQuery("findAllStudents").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return students;
    }

    public Student findStudentByEmail(String email) {
        Student student;
        student = em.createNamedQuery("findByEmailStudent", Student.class)
                .setParameter("userEmail", email)
                .getSingleResult();

        return student;
    }

}
