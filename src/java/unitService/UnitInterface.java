/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitService;

import entities.Unit;
import exceptions.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Nerea
 */
public interface UnitInterface {

    /**
     * This method creates a new Unit in the data base.
     *
     * @param unit The Unit entity object containing new Unit data.
     * @throws CreateErrorException Thrown when any error or exception occurs
     * during creation.
     */
    public void createUnit(Unit unit) throws CreateErrorException;

    /**
     * This method updates a movement data in the data store.
     *
     * @param unit The Unit entity object containing modified Unit data.
     * @throws UpdateErrorException Thrown when any error or exception occurs
     * during update.
     */
    public void updateUnit(Unit unit) throws UpdateErrorException;

    /**
     * This method removes a Unit from the data store.
     *
     * @param unit The Unit entity object to be removed.
     * @throws DeleteErrorException Thrown when any error or exception occurs
     * during deletion.
     */
    public void removeUnit(Unit unit) throws DeleteErrorException;

    /**
     * The method finds a unit which id is equals the id the User introduced.
     *
     * @param id An Integer that contains the id the user introduce.
     * @return The Unit entity object to be found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    public Unit findUnitByID(Integer id) throws FindErrorException;

    /**
     * The method finds all the units.
     *
     * @return An ArrayList of Units that contains the units that the method
     * found.
     * @throws FindErrorException Thrown when any error or exception occurs
     * during reading.
     */
    public ArrayList<Unit> findAllUnits() throws FindErrorException;

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
    public ArrayList<Unit> findSubjectUnits(String name) throws FindErrorException;

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
    public ArrayList<Unit> findSubjectUnitsByName(String name, String subject) throws FindErrorException;

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
    public Unit findOneSubjectUnitByName(String name, String subject) throws FindErrorException;

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
    public ArrayList<Unit> findSubjectUnitsByDateInit(Date dateInit, String subject) throws FindErrorException;

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
    public ArrayList<Unit> findSubjectUnitsByDateEnd(Date dateEnd, String subject) throws FindErrorException;

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
    public ArrayList<Unit> findSubjectUnitsByHours(Integer hours, String subject) throws FindErrorException;

}
