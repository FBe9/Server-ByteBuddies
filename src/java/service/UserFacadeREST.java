package service;

import entities.User;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import exceptions.UserNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import userService.UserInterface;

/**
 * RESTful web service for managing users.
 *
 * @author Irati
 */
@Path("entities.user")
public class UserFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private UserInterface ejb;

    /**
     * Logger for this class.
     */
    private Logger LOGGER = Logger.getLogger(UserFacadeREST.class.getName());

    /**
     * POST method to create users: uses createUser business logic method.
     *
     * @param user The user object containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUser(User user) {
        try {
            LOGGER.log(Level.INFO, "Creating user {0}", user.getId());
            ejb.createUser(user);
            LOGGER.log(Level.INFO, "Creating user {0} successfully", user.getId());
        } catch (EmailAlreadyExistsException | CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update user: uses updateUser business logic method.
     *
     * @param user The user object containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateUser(User user) {

        try {
            LOGGER.log(Level.INFO, "Updating user", user.getId());
            ejb.updateUser(user);
            LOGGER.log(Level.INFO, "Updated user successfully", user.getId());
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());

        }

    }

    /**
     * DELETE method to delete users: uses deleteUser business logic method.
     *
     * @param id the id of the user.
     */
    @DELETE
    @Path("{id}")
    public void removeUser(@PathParam("id") Integer id) {

        try {
            LOGGER.log(Level.INFO, "Deleting user", id);
            ejb.deleteUser(ejb.findUserById(id));
            LOGGER.log(Level.INFO, "Deleted user successfully", id);
        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }

    }

    /**
     * GET method to find a user by id: uses findUserById business logic method.
     *
     * @param id the id of the user.
     * @return the user containing data.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Integer id) {
        User user;
        try {
            LOGGER.log(Level.INFO, "Reading data for user", id);
            user = ejb.findUserById(id);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return user;
    }

    /**
     * GET method to find all users: uses findAllUsers business logic method.
     *
     * @return the users.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        List<User> users;
        try {
            LOGGER.log(Level.INFO, "Reading data for all users");
            users = ejb.findAllUsers();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return users;
    }

    /**
     * GET method to find all teachers: uses findAllTeachers business logic
     * method.
     *
     * @return the users.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findAllTeachers")
    public List<User> findAllTeachers() {
        List<User> users;
        try {
            LOGGER.log(Level.INFO, "Reading data for all teachers");
            users = ejb.findAllTeachers();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return users;
    }

    /**
     * GET method to find all students: uses findAllTeachers business logic
     * method.
     *
     * @return the users.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("findAllStudents")
    public List<User> findAllStudents() {
        List<User> users;
        try {
            LOGGER.log(Level.INFO, "Reading data for all students");
            users = ejb.findAllStudents();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotFoundException(ex.getMessage());
        }
        return users;
    }

    /**
     * Validates user credentials and performs user login.
     *
     * @param user The user object containing email and password for
     * authentication.
     * @return The authenticated user information.
     * @throws NotAuthorizedException If the user is not authorized (invalid
     * credentials).
     * @throws BadRequestException If there is a bad request, such as missing or
     * invalid parameters.
     * @throws InternalServerErrorException If an unexpected server error
     * occurs.
     */
    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public User login(User user) {
        try {
            LOGGER.log(Level.INFO, "Login attempt for user: " + user.getEmail());

            // Perform user login
            user = ejb.logInUser(user.getEmail(), user.getPassword());

        } catch (UserNotFoundException ex) {
            LOGGER.severe(ex.getMessage());
            throw new NotAuthorizedException(ex.getMessage());
        } catch (BadRequestException e) {
            LOGGER.severe(e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }

        LOGGER.log(Level.INFO, "Login successful for user: " + user.getEmail());
        return user;
    }

    /**
     * Initiates the process of resetting the user's password.
     *
     * @param email The email of the user whose password needs to be reset.
     */
    @POST
    @Path("{email}")
    public void resetPassword(@PathParam("email") String email) {
        // Perform password reset
        ejb.resetPassword(email);
    }
}
