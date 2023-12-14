/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Unit;
import exceptions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
import unitService.UnitInterface;

/**
 *
 * @author Nerea
 */
@Stateless
@Path("entities.unit")
public class UnitFacadeREST {

    @EJB
    private UnitInterface ejbU;

    /**
     * Logger for this class.
     */
    private static final Logger LOGGER = Logger.getLogger("package service");

    /**
     * POST method to create a new Unit: uses createUnit business logic method.
     *
     * @param unit The Unit entity object containing new Unit data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUnit(Unit unit) {
        try {
            LOGGER.log(Level.INFO, "Creating a new Unit id= {0}", unit.getId());
            ejbU.createUnit(unit);

        } catch (CreateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to modify a Unit: uses updateUnit business logic method.
     *
     * @param unit The Unit entity object containing new Unit data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateAlbum(Unit unit) {
        try {
            LOGGER.log(Level.INFO, "Updating the unit  id= {0}", unit.getId());
            ejbU.updateUnit(unit);
        } catch (UpdateErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * DELETE method to remove a Unit: uses removeUnit business logic method.
     *
     * @param id The id for the unit to be deleted.
     */
    @DELETE
    @Path("{id}")
    public void removeUnit(@PathParam("id") Integer id) {
        Unit unit;
        try {

            LOGGER.log(Level.INFO, "Deleting the unit  id= {0}", id);
            unit = ejbU.findUnitByID(id);
            ejbU.removeUnit(unit);

        } catch (FindErrorException | DeleteErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to get an Unit data by id: it uses business method
     * findUnitByID.
     *
     * @param id The id for the unit to be found.
     * @return A Unit that contains the unit the method found.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Unit findUnitByID(@PathParam("id") Integer id
    ) {
        Unit unit;
        try {
            LOGGER.log(Level.INFO, "Finding the unit id= {0} ", id);
            unit = ejbU.findUnitByID(id);

        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return unit;
    }

    /**
     * GET method to get all Units data by name: it uses business method
     * findUnitsByName.
     *
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Units that contains the units the method found.
     */
    @GET
    @Path("findUnitsByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Unit> findUnitsByName(@PathParam("name") String name) {
        ArrayList<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all units by name");
            units = ejbU.findUnitsByName(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by name: it uses business method
     * findUnitsByName.
     *
     * @param name A String that contains the words the user introduced.
     * @return An ArrayList of Units that contains the units the method found.
     */
    @GET
    @Path("findOneUnitByName/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Unit findOneUnitByName(@PathParam("name") String name) {
        Unit unit;
        try {
            LOGGER.log(Level.INFO, "Reading data for an especific unit by name");
            unit = ejbU.findOneUnitByName(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return unit;
    }

    /**
     * GET method to get all Units data by date of iitialize the unit: it uses
     * business method findUnitsByDateInit.
     *
     * @param dateInit A Date that contains the date the User introduce.
     * @return An ArrayList of Unit that contains the units the method found.
     */
    @GET
    @Path("findUnitsByDateInit/{dateInit}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Unit> findUnitsByDateInit(@PathParam("dateInit") Date dateInit) {
        ArrayList<Unit> units = null;
        try {
            LOGGER.log(Level.INFO, "Reading data for all Units by init date");
            units = ejbU.findUnitsByDateEnd(dateInit);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by date of end the unit: it uses
     * business method findUnitsByDateInit.
     *
     * @param dateEnd A Date that contains the date the User introduce.
     * @return An ArrayList of Unit that contains the units the method found.
     */
    @GET
    @Path("findUnitsByDateEnd/{dateEnd}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Unit> findUnitsByDateEnd(@PathParam("dateEnd") Date dateEnd) {
        ArrayList<Unit> units = null;
        try {
            LOGGER.log(Level.INFO, "Reading data for all Units by end date");
            units = ejbU.findUnitsByDateEnd(dateEnd);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by hours: it uses business method
     * findUnitsByHours.
     *
     * @param hours An Integer that contains the number the User introduce.
     * @return An ArrayList of Unit that contains the units the method found.
     */
    @GET
    @Path("findUnitsByHours/{hours}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Unit> findUnitsByHours(@PathParam("hours") Integer hours) {
        ArrayList<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all units by hours");
            units = ejbU.findUnitsByHours(hours);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    @GET
    @Path("findUnitsBySubject/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Unit> findUnitsBySubject(@PathParam("name") String name) {
        ArrayList<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all units by subject name");
            units = ejbU.findUnitsBySubject(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }
}
