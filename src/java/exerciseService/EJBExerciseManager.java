package exerciseService;

import entities.Exercise;
import entities.LevelType;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.ExerciseErrorException;
import exceptions.UpdateErrorException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the Exercise interface for
 * Exercises Management web service application.
 *
 * @author Leire
 */
@Stateless
public class EJBExerciseManager implements ExerciseInterface {

    /**
     * EntityManager for WebBiteBuddys persistence unit.
     */
    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager entityManager;

    /**
     * This method create a new exercise.
     *
     * @param exercise The Exercise entity object containing new exercises data.
     * @throws CreateErrorException Thrown when any error or exception occurs
     * during creation.
     */
    @Override
    public void createExercise(Exercise exercise) throws CreateErrorException {
        try {
            entityManager.persist(exercise);
        } catch (Exception e) {
            throw new CreateErrorException(e.getMessage());
        }
    }

    /**
     * This method update an exercise.
     *
     * @param exercise The Exercise entity object containing modified exercise
     * data.
     * @throws UpdateErrorException Thrown when any error or exception occurs
     * during update.
     */
    @Override
    public void updateExercise(Exercise exercise) throws UpdateErrorException {
        try {
            if (!entityManager.contains(exercise)) {
                entityManager.merge(exercise);
            }
            entityManager.flush();
        } catch (Exception e) {
            throw new UpdateErrorException(e.getMessage());
        }
    }

    /**
     * This method delete an exercise by id.
     *
     * @param id The Exercise entity object to be removed.
     * @throws DeleteErrorException Thrown when any error or exception occurs
     * during deletion.
     */
    @Override
    public void removeExercise(Long id) throws DeleteErrorException {
        try {
            entityManager.remove(entityManager.merge(getExerciseByID(id)));
        } catch (ExerciseErrorException e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    /**
     * This method obtains an exercise using its id.
     *
     * @param id The id for the exercise to be got.
     * @return An Exercise entity object containing exercise data.
     * @throws ExerciseErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public Exercise getExerciseByID(Long id) throws ExerciseErrorException {
        Exercise exercise;
        try {
            exercise = entityManager.find(Exercise.class, id);
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercise;
    }

    /**
     * This method gets a list with all exercises.
     *
     * @return A List of Exercise entity objects.
     * @throws ExerciseErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Exercise> getAllExercises() throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getAllExercises").getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }

    /**
     * This method gets a list with all exercises using the number.
     *
     * @param number Number of the exercise.
     * @return List of the exercises with that specific number.
     * @throws ExerciseErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Exercise> getExercisesByNumber(Integer number) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByNumber").setParameter("number", number).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }

    /**
     * This method gets a list with all exercises using the date.
     *
     * @param date Date of the date of the exercise.
     * @return List of the exercises with that specific date.
     * @throws ExerciseErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Exercise> getExercisesByDate(Date date) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByDate").setParameter("date", date).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }

    /**
     * This method gets a list with all exercises using the level type.
     *
     * @param levelType Level type of the exercises.
     * @return List of the exercises with that specific level type.
     * @throws ExerciseErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Exercise> getExercisesByLevel(LevelType levelType) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByLevel").setParameter("levelType", levelType).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }

    /**
     * This method gets a list with all exercises using the unit name.
     *
     * @param name Name of the unit.
     * @return List of the exercises with that specific unit name.
     * @throws ExerciseErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Exercise> getExercisesByUnitName(String name) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByUnitName").setParameter("name", name).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }
    
    /**
     * This method gets a list with all exercises using the exercise number and
     * the unit name.
     *
     * @param number Number of the exercise.
     * @param name Name of the unit.
     * @return List of the exercises with that specific number and unit.
     * @throws ExerciseErrorException ExerciseErrorException Thrown when any
     * error or exception occurs during reading.
     */
    @Override
    public List<Exercise> getExercisesByNumberAndUnitName(Integer number, String name) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByNumberAndUnitName").setParameter("number", number).setParameter("name", name).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }

    /**
     * This method gets a list with all exercises using the exercise date and
     * the unit name.
     *
     * @param date Date of the date of the exercise.
     * @param name Name of the unit.
     * @return List of the exercises with that specific date and unit.
     * @throws ExerciseErrorException ExerciseErrorException Thrown when any
     * error or exception occurs during reading.
     */
    @Override
    public List<Exercise> getExercisesByDateAndUnitName(Date date, String name) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByDateAndUnitName").setParameter("date", date).setParameter("name", name).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }

    /**
     * This method gets a list with all exercises using the exercise level type
     * and the unit name.
     *
     * @param levelType Level type of the exercises.
     * @param name Name of the unit.
     * @return List of the exercises with that specific level type and unit.
     * @throws ExerciseErrorException ExerciseErrorException Thrown when any
     * error or exception occurs during reading.
     */
    @Override
    public List<Exercise> getExercisesByLevelAndUnitName(LevelType levelType, String name) throws ExerciseErrorException {
        List<Exercise> exercises;
        try {
            exercises = entityManager.createNamedQuery("getExercisesByLevelAndUnitName").setParameter("levelType", levelType).setParameter("name", name).getResultList();
        } catch (Exception e) {
            throw new ExerciseErrorException(e.getMessage());
        }
        return exercises;
    }
}
