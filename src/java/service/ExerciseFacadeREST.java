package service;

import entities.Exercise;
import entities.LevelType;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.ExerciseErrorException;
import exceptions.UpdateErrorException;
import exerciseService.ExerciseInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * RESTful web service for managing exercises.
 * 
 * @author Leire
 */
@Path("entities.exercise")
public class ExerciseFacadeREST {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @EJB
    private ExerciseInterface exerciseEJB;

    private static final Logger LOGGER = Logger.getLogger(ExerciseFacadeREST.class.getName());

    /**
     * POST method to create exercises..
     * 
     * @param entity The exercise to post.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Exercise entity) {
        try {
            LOGGER.log(Level.INFO, "Creating exercise.");
            exerciseEJB.createExercise(entity);
        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update an exercise.
     * 
     * @param id The id of the exercise.
     * @param entity The exercise to post.
     */
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Exercise entity) {
        try {
            LOGGER.log(Level.INFO, "Updating exercise.");
            exerciseEJB.updateExercise(entity);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * DELETE method to remove an exercise.
     * 
     * @param id The id of the exercise to remove.
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Removing exercise.");
            exerciseEJB.removeExercise(id);
        } catch (DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method by ID to find an exercise with a given ID.
     * 
     * @param id The id of the exercise.
     * @return The exercise found.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Exercise getExerciseByID(@PathParam("id") Integer id) {
        Exercise exercise = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by ID.");
            exercise = exerciseEJB.getExerciseByID(id);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercise;
    }

    /**
     * GET method to find all exercises.
     * 
     * @return Collection of all exercises.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding all exercises.");
            exercises = exerciseEJB.getAllExercises();
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    /**
     * GET method to find an exercise by its number.
     * 
     * @param number The number to look for.
     * @return Collection of all found exercises.
     */
    @GET
    @Path("getByNumber/{number}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByNumber(@PathParam("number") Integer number) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by number.");
            exercises = exerciseEJB.getExercisesByNumber(number);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    /**
     * GET method to find an exercise by its date.
     * 
     * @param date The date to search.
     * @return A collection of all found exercises.
     */
    @GET
    @Path("getByDate/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByDate(@PathParam("date") String date) {
        List<Exercise> exercises = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {LOGGER.log(Level.INFO, "Finding exercise by date.");
            Date formatDate = format.parse(date);
            exercises = exerciseEJB.getExercisesByDate(formatDate);
        } catch (ExerciseErrorException | ParseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    /**
     * GET method to find an exercise by its level type.
     * 
     * @param levelType The level type to search.
     * @return A collection of all found exercises.
     */
    @GET
    @Path("getByLevelType/{levelType}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByLevel(@PathParam("levelType") String levelType) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by level type.");
            
            exercises = exerciseEJB.getExercisesByLevel(LevelType.valueOf(levelType.toUpperCase()));
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    /**
     * GET method to find an exercise by the name of unit.
     * 
     * @param name The name of the unit.
     * @return A collection of all found exercises.
     */
    @GET
    @Path("getByUnitName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByUnitName(@PathParam("name") String name) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by unit name.");
            exercises = exerciseEJB.getExercisesByUnitName(name);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    /**
     * GET method to find an exercise by its number and name of unit.
     * 
     * @param number The number.
     * @param name The name of the unit.
     * @return A collection of all found exercises.
     */
    @GET
    @Path("getByNumberAndUnitName/{number}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByNumberAndUnitName(@PathParam("number") Integer number, @PathParam("name") String name) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by number and unit name.");
            exercises = exerciseEJB.getExercisesByNumberAndUnitName(number, name);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    
    /**
     * GET method to find an exercise by its date and name of unit.
     * 
     * @param date The date.
     * @param name The name of the unit.
     * @return A collection of all found exercises.
     */
    @GET
    @Path("getByDateAndUnitName/{date}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByDateAndUnitName(@PathParam("date") String date, @PathParam("name") String name) {
        List<Exercise> exercises = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            LOGGER.log(Level.INFO, "Finding exercise by date and unit name.");
            Date formatDate = format.parse(date);
            exercises = exerciseEJB.getExercisesByDateAndUnitName(formatDate, name);
        } catch (ExerciseErrorException | ParseException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

    /**
     * GET method to find an exercise by its level type and name of unit.
     * 
     * @param levelType The level type.
     * @param name The name of the unit.
     * @return A collection of all found exercises.
     */
    @GET
    @Path("getByLevelTypeAndUnitName/{levelType}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByLevelAndUnitName(@PathParam("levelType") String levelType, @PathParam("name") String name) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by level type and unit name.");
            exercises = exerciseEJB.getExercisesByLevelAndUnitName(LevelType.valueOf(levelType.toUpperCase()), name);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }
}
