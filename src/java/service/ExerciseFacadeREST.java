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

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Exercise entity) {
        try {
            LOGGER.log(Level.INFO, "Updating exercise.");
            exerciseEJB.updateExercise(entity);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Removing exercise.");
            exerciseEJB.removeExercise(id);
        } catch (DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Exercise getExerciseByID(@PathParam("id") Long id) {
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

    @GET
    @Path("getByLevelType/{levelType}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByLevel(@PathParam("levelType") LevelType levelType) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by level type.");
            exercises = exerciseEJB.getExercisesByLevel(levelType);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

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

    @GET
    @Path("getByLevelTypeAndUnitName/{levelType}/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Exercise> getExercisesByLevelAndUnitName(@PathParam("levelType") LevelType levelType, @PathParam("name") String name) {
        List<Exercise> exercises = null;
        try {
            LOGGER.log(Level.INFO, "Finding exercise by level type and unit name.");
            exercises = exerciseEJB.getExercisesByLevelAndUnitName(levelType, name);
        } catch (ExerciseErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return exercises;
    }

}
