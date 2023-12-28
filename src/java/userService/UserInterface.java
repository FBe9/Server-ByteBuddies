package userService;

import entities.User;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author irati
 */
@Local
public interface UserInterface {
    /**
     * Method used to create a user.
     *
     * @param user the User entity object containing new data.
     * @throws CreateErrorException if there is an error during creation.
     */
    public void createUser(User user) throws CreateErrorException;

    /**
     * Method to update a user.
     *
     * @param user the User entity object containing new data.
     * @throws UpdateErrorException if there is an error duting update.
     */
    public void updateUser(User user) throws UpdateErrorException;

    /**
     * Method to delete a user.
     *
     * @param user the User entity to be deleted.
     * @throws DeleteErrorException if there is an error during delete.
     */
    public void deleteUser(User user) throws DeleteErrorException;

    /**
     * Method to search for a user by id.
     *
     * @param id the id to make the search.
     * @return a User entity containing the user data.
     * @throws FindErrorException if there is an error during reading.
     */
    public User findUserById(Integer id) throws FindErrorException;

    /**
     * Method to search for all users.
     *
     * @return a collection of users.
     * @throws FindErrorException if there is an error during reading.
     */
    public List<User> findAllUsers() throws FindErrorException;
    
    public List<User> findAllTeachers() throws FindErrorException;
    public List<User> findAllStudents() throws FindErrorException;
    
    public User logInUser(String dniUser, String passwordUser) throws FindErrorException;
    
    
}
