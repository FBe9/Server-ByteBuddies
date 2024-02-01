package unitService;

import entities.*;
import exceptions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import subjectService.SubjectInterface;

/**
 * This is the stateless EJB that implements the UnitInterface interface for
 * UnitManager web service application.
 *
 * @author nerea
 */
@Stateless
public class EJBUnitManager implements UnitInterface {

    private static final Logger LOGGER = Logger.getLogger("package unitInterface");
    /**
     * EntityManager for WebBiteBuddys persistence unit.
     */
    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @EJB
    private SubjectInterface ejbS;

    /**
     * This method creates a new Unit in the data base.
     *
     * @param unit The Unit entity object containing new Unit data.
     * @throws CreateErrorException Thrown when any error or exception occurs
     * during creation.
     */
    @Override
    public void createUnit(Unit unit) throws CreateErrorException {
        try {
            em.createNamedQuery("findOneSubjectUnitByName").setParameter("name", unit.getName()).setParameter("subjectName", unit.getSubject().getName()).getSingleResult();
            throw new CreateErrorException("You already have a Unit with that name");

        } catch (NoResultException n) {
            em.persist(unit);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  createUnit(Unit Unit) {0}", e.getMessage());
            throw new CreateErrorException(e.getMessage());

        }
    }

    /**
     * This method updates a movement data in the data store.
     *
     * @param unit The Unit entity object containing modified Unit data.
     * @throws UpdateErrorException Thrown when any error or exception occurs
     * during update.
     */
    @Override
    public void updateUnit(Unit unit) throws UpdateErrorException {
        try {
            if (!em.contains(unit)) {
                em.merge(unit);
            }
            em.flush();
        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, "UnitEJB ->  updateUnit(Unit unit) {0}", e.getMessage());
            throw new UpdateErrorException(e.getMessage());
        }
    }

    /**
     * This method removes a Unit from the data store.
     *
     * @param unit The Unit entity object to be removed.
     * @throws DeleteErrorException Thrown when any error or exception occurs
     * during deletion.
     */
    @Override
    public void removeUnit(Unit unit) throws DeleteErrorException {
        try {
            em.remove(em.merge(unit));
        } catch (Exception e) {

            LOGGER.log(Level.SEVERE, "UnitEJB ->  removeUnit(Unit Unit) {0}", e.getMessage());
            throw new DeleteErrorException(e.getMessage());

        }
    }

    /**
     * The method finds a unit which id is equals the id the User introduced.
     *
     * @param id An Integer that contains the id the user introduce.
     * @return The Unit entity object to be found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public Unit findUnitByID(Integer id) throws FindErrorException {
        Unit unit = null;
        try {
            unit = em.find(Unit.class, id);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitByID(Integer id) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return unit;
    }

    /**
     * The method finds all the units.
     *
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findAllUnits() throws FindErrorException {
        List<Unit> units = null;
        try {
            units = em.createNamedQuery("findAllUnits").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findAllUnits() {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units which subject name contains the words the
     * user introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findSubjectUnits(String name) throws FindErrorException {
        List<Unit> units = null;
        try {
            units = em.createNamedQuery("findSubjectUnits").setParameter("subjectName", name).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnits(String name) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units that the name contains the words the user
     * introduced and the subject name is the one the user introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @param subject A String with the name of the subject
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findSubjectUnitsByName(String name, String subject) throws FindErrorException {
        List<Unit> units = null;
        try {
            units = em.createNamedQuery("findSubjectUnitsByName").setParameter("name", "%" + name + "%").setParameter("subjectName", subject).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByName(String name, String subject) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return units;
    }

    /**
     * This method finds a unit which name is the one the user introduced and
     * the subject name is the one the user introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @param subject A String with the name of the subject
     * @return The Unit entity object to be found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public Unit findOneSubjectUnitByName(String name, String subject) throws FindErrorException {
        Unit unitbd = null;
        try {
            unitbd = (Unit) em.createNamedQuery("findOneSubjectUnitByName").setParameter("name", name).setParameter("subjectName", subject).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findOneSubjectUnitByName(String name, String subject) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return unitbd;
    }

    /**
     * This method finds all the units where the init date of the unit is equals
     * the date the user introduced and the subject name is the one the user
     * introduce.
     *
     * @param dateInit A Date that contains the date the User introduce.
     * @param subject A String with the name of the subject
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findSubjectUnitsByDateInit(Date dateInit, String subject) throws FindErrorException {
        List<Unit> units = null;
        try {
            units = em.createNamedQuery("findSubjectUnitsByDateInit").setParameter("dateInit", dateInit).setParameter("subjectName", subject).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByDateInit(Date dateInit, String subject) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units where the end date of the unit is equals
     * the date the user introduced and the subject name is the one the user
     * introduce.
     *
     * @param dateEnd A Date that contains the date the User introduce.
     * @param subject A String with the name of the subject
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findSubjectUnitsByDateEnd(Date dateEnd, String subject) throws FindErrorException {
        List<Unit> units = null;
        try {
            units = em.createNamedQuery("findSubjectUnitsByDateEnd").setParameter("dateEnd", dateEnd).setParameter("subjectName", subject).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByDateEnd(Date dateInit, String subject) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units where the hours of the unit are equals
     * the hours the user introduced and the subject name is the one the user
     * introduce.
     *
     * @param hours An Integer with the number the user introduce.
     * @param subject A String with the name of the subject
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findSubjectUnitsByHours(Integer hours, String subject) throws FindErrorException {
        List<Unit> units = null;
        try {
            units = em.createNamedQuery("findSubjectUnitsByHours").setParameter("hours", hours).setParameter("subjectName", subject).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByHours(Integer hours, String subject) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units from the subjects where the Teacher
     * teachs.
     *
     * @param userId A Integre with the id of the user that is logged to the
     * application.
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findUnitsFromTeacherSubjects(Integer userId) throws FindErrorException {
        List<Subject> subjects;
        List<Unit> AllUnits = new ArrayList<>();
        try {

            subjects = (ArrayList<Subject>) ejbS.findSubjectsByTeacherId(userId);

            for (int i = 0; i < subjects.size(); i++) {
                String subjectName = subjects.get(i).getName();
                List<Unit> units = em.createNamedQuery("findSubjectUnits").setParameter("subjectName", subjectName).getResultList();
                AllUnits.addAll(units);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsFromTeacherSubjects(Integer userId) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return AllUnits;
    }

    /**
     * This method finds all the units from the subjects where the Student is
     * matriculated.
     *
     * @param userId A Integre with the id of the user that is logged to the
     * application.
     * @return An List of Units that contains the units that the method found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public List<Unit> findUnitsFromStudentSubjects(Integer userId) throws FindErrorException {
        List<Subject> subjects;
        List<Unit> AllUnits = new ArrayList<>();
        try {
            subjects = (ArrayList<Subject>) ejbS.findByEnrollments(userId);

            for (int i = 0; i < subjects.size(); i++) {
                String subjectName = subjects.get(i).getName();
                List<Unit> units = em.createNamedQuery("findSubjectUnits").setParameter("subjectName", subjectName).getResultList();
                AllUnits.addAll(units);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsFromStudentSubjects(Integer userId) {0}", e.getMessage());
            throw new FindErrorException(e.getMessage());
        }
        return AllUnits;
    }
}
