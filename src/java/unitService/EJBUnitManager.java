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
     * The method finds a unit which id is equals the id the User introduce for
     * a new unit.
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
     * The method finds all the units where the User is the creator
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
     * This method finds all the units which name contains the words the user
     * introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findUnitsByName(String name) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findUnitsByName").setParameter("name", "%" + name + "%").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsByName(String name) {0}", e.getMessage());
        }
        return units;
    }
    
    /**
     * This method finds a unit which name is the one the user
     * introduced.
     *
     * @param name A String that contains the words the user introduced.
     * @return The Unit entity object to be found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public Unit findOneUnitByName(String name) throws FindErrorException {
        Unit unitbd = null;
        try {
            unitbd = (Unit) em.createNamedQuery("findOneUnitByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitByName(String name) {0}", e.getMessage());
        }
        return unitbd;
    }

    /**
     * This method finds all the units where the init date of the unit is equals
     * the date the user introduced.
     *
     * @param dateInit A Date that contains the date the User introduce.
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findUnitsByDateInit(Date dateInit) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findUnitsByDateInit").setParameter("dateInit", dateInit).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsByDateInit(Date dateInit) {0}", e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units where the end date of the unit is equals
     * the date the user introduced.
     *
     * @param dateEnd A Date that contains the date the User introduce.
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findUnitsByDateEnd(Date dateEnd) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findUnitsByDateEnd").setParameter("dateInit", dateEnd).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsByDateEnd(Date dateInit) {0}", e.getMessage());
        }
        return units;
    }

    /**
     * This method finds all the units where the hours of the unit are equals
     * the hours the user introduced.
     *
     * @param hours An Integer with the number the user introduce.
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    @Override
    public ArrayList<Unit> findUnitsByHours(Integer hours) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findUnitsByHours").setParameter("hours", hours).getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsByHours(Integer hours) {0}", e.getMessage());
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
    public ArrayList<Unit> findUnitsBySubject(String name) throws FindErrorException {
        ArrayList<Unit> units = null;
        try {
            units = new ArrayList<>(em.createNamedQuery("findUnitsBySubject").setParameter("name", "%" + name + "%").getResultList());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UnitEJB ->  findUnitsByName(String name) {0}", e.getMessage());
        }
        return units;
    }
}
