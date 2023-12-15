/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitService;

import entities.*;
import exceptions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    /**
     * This method creates a new Unit in the data base.
     *
     * @param unit The Unit entity object containing new Unit data.
     * @throws CreateErrorException Thrown when any error or exception occurs
     * during creation.
     */
    @Override
    public void createUnit(Unit unit) throws CreateErrorException {
        Unit bdUnit;

        try {
            bdUnit = (Unit) em.createNamedQuery("findOneUnitByName").setParameter("name", unit.getName()).getSingleResult();
            if (bdUnit.equals(unit)) {
                throw new CreateErrorException("The unit already exist");
            }

            em.persist(unit);
        } catch (Exception e) {
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
        }
        return unit;
    }

    /**
     * The method finds all the units.
     *
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findAllUnits() throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findAllUnits").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findAllUnits() {0}", e.getMessage());
        }
        return units;
    }

     /**
     * This method finds all the units which subject name contains the words the
     * user introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findSubjectUnits(String name) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findSubjectUnits").setParameter("name", "%" + name + "%").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnits(String name) {0}", e.getMessage());
        }
        return units;
    }

   /**
     * This method finds all the units that the name contains the words the user
     * introduced and the subject name is the one the user introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @param subject A String with the name of the subject
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findSubjectUnitsByName(String name, String subject) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findSubjectUnitsByName").setParameter("name", "%" + name + "%").setParameter("subject", subject).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByName(String name, String subject) {0}", e.getMessage());
        }
        return units;
    }

    /**
     * This method finds a units which name is the one the user introduced and
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
            unitbd = (Unit) em.createNamedQuery("findOneSubjectUnitByName").setParameter("name", name).setParameter("subject", subject).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findOneSubjectUnitByName(String name, String subject) {0}", e.getMessage());
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
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findSubjectUnitsByDateInit(Date dateInit, String subject) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findSubjectUnitsByDateInit").setParameter("dateInit", dateInit).setParameter("subject", subject).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByDateInit(Date dateInit, String subject) {0}", e.getMessage());
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
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findSubjectUnitsByDateEnd(Date dateEnd, String subject) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findSubjectUnitsByDateEnd").setParameter("dateInit", dateEnd).setParameter("subject", subject).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByDateEnd(Date dateInit, String subject) {0}", e.getMessage());
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
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findSubjectUnitsByHours(Integer hours, String subject) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findSubjectUnitsByHours").setParameter("hours", hours).setParameter("subject", subject).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findSubjectUnitsByHours(Integer hours, String subject) {0}", e.getMessage());
        }
        return units;
    }
}
