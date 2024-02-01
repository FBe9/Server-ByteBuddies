package teacherService;

import encrypt.AsimetricaServer;
import entities.Teacher;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Local interface for teacher EJB.
 * @author irati
 */
@Stateless
public class EJBTeacherManager implements TeacherInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    /* Method to create a teacher.
     *
     * @param teacher the Teacher entity object containing new data.
     * @throws CreateErrorException if there is an error duting create.
     * @throws EmailAlreadyExistsException if there email already exists.
     */
    @Override
    public void createTeacher(Teacher teacher) throws CreateErrorException, EmailAlreadyExistsException {
        try {
            em.createNamedQuery("findByEmailTeacher").setParameter("userEmail", teacher.getEmail()).getSingleResult();
            throw new EmailAlreadyExistsException("User with email already exists");

        } catch (NoResultException ex) {
            try {

                String passwordClient = AsimetricaServer.decryptData(teacher.getPassword());
                String hash = AsimetricaServer.hashText(passwordClient);

                teacher.setPassword(hash);
                if (!em.contains(teacher)) {
                    teacher = em.merge(teacher);
                }
                em.persist(teacher);
            } catch (Exception e) {
                throw new CreateErrorException(e.getMessage());
            }
        }
    }

    /**
     * Method to update a teacher.
     *
     * @param teacher the Teacher entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    @Override
    public void updateTeacher(Teacher teacher) throws UpdateErrorException {
        try {
            if (!em.contains(teacher)) {
                em.merge(teacher);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Method to delete a teacher.
     *
     * @param teacher the Teacher entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    @Override
    public void deleteTeacher(Teacher teacher) throws DeleteErrorException {
        try {
            em.remove(em.merge(teacher));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    /**
     * Method to search for a teacher by id.
     *
     * @param id the id to make the search.
     * @return a Teacher entity containing the teacher data.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public Teacher findTeacherById(Integer id) throws FindErrorException {
        Teacher teacher;
        try {
            teacher = em.find(Teacher.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return teacher;
    }

    /**
     * Method to search for all teachers.
     *
     * @return a collection of teachers.
     * @throws FindErrorException if there is an error during reading.
     */
    @Override
    public List<Teacher> findAllTeachers() throws FindErrorException {
        List<Teacher> teachers;
        try {
            teachers = em.createNamedQuery("findAllTeachers").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return teachers;
    }

}
