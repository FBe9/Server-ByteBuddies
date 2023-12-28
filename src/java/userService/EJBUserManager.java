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
 *
 * @author irati
 */
@Stateless
public class EJBUserManager implements UserInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @Override
    public void createUser(User user) throws CreateErrorException {
        try {
            em.persist(user);
        } catch (Exception ex) {
            throw new CreateErrorException(ex.getMessage());
        }

    }

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

    @Override
    public void deleteUser(User user) throws DeleteErrorException {
        try {
            em.remove(em.merge(user));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

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

    @Override
    public User logInUser(String dniUser, String passwordUser) throws FindErrorException {
        User user = null;
        return user;
    }

}
