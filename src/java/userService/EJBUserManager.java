package userService;

import entities.User;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB implementation of the UserInterface for managing user-related operations.
 *
 * @author Irati
 */
@Stateless
public class EJBUserManager implements UserInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    /**
     * Creates a new user.
     *
     * @param user The User entity object containing new data.
     * @throws CreateErrorException If there is an error during creation.
     */
    @Override
    public void createUser(User user) throws CreateErrorException {
        try {
            em.persist(user);
        } catch (Exception ex) {
            throw new CreateErrorException(ex.getMessage());
        }
    }

    /**
     * Updates an existing user.
     *
     * @param user The User entity object containing updated data.
     * @throws UpdateErrorException If there is an error during the update.
     */
    @Override
    public void updateUser(User user) throws UpdateErrorException {
        try {
            if (!em.contains(user)) {
                em.merge(user);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    /**
     * Deletes a user.
     *
     * @param user The User entity to be deleted.
     * @throws DeleteErrorException If there is an error during deletion.
     */
    @Override
    public void deleteUser(User user) throws DeleteErrorException {
        try {
            em.remove(em.merge(user));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    /**
     * Finds a user by ID.
     *
     * @param id The ID used for the search.
     * @return A User entity containing the user data.
     * @throws FindErrorException If there is an error during reading.
     */
    @Override
    public User findUserById(Integer id) throws FindErrorException {
        User user;
        try {
            user = em.find(User.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return user;
    }

    /**
     * Finds all users.
     *
     * @return A collection of users.
     * @throws FindErrorException If there is an error during reading.
     */
    @Override
    public List<User> findAllUsers() throws FindErrorException {
        List<User> users;
        try {
            users = em.createNamedQuery("findAllUsers").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return users;
    }

    /**
     * Finds all teachers.
     *
     * @return A collection of teachers.
     * @throws FindErrorException If there is an error during reading.
     */
    @Override
    public List<User> findAllTeachers() throws FindErrorException {
        List<User> users;
        try {
            users = em.createNamedQuery("findTeachers").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return users;
    }

    /**
     * Finds all students.
     *
     * @return A collection of students.
     * @throws FindErrorException If there is an error during reading.
     */
    @Override
    public List<User> findAllStudents() throws FindErrorException {
        List<User> users;
        try {
            users = em.createNamedQuery("findStudents").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return users;
    }

    /**
     * Logs in a user.
     *
     * @param id The id of the user.
     * @param passwordUser The user's password.
     * @return The logged-in User entity.
     * @throws FindErrorException If there is an error during login.
     */
    @Override
    public User logInUser(String id, String passwordUser) throws FindErrorException {
        User user = null; 
        return user;
    }
}
