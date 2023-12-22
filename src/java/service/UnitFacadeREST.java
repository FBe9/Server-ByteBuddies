package service;

import entities.Unit;
import exceptions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
 * RESTful web service for managing units.
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
     * GET method to get all the Unit data: it uses business method
     * findSubjectUnits.
     *
     * @return An ArrayList of Unit that contains the units the method found.
     */
    @GET
    @Path("findAllUnits")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findAllUnits() {
        List<Unit> units = null;
        try {
            LOGGER.log(Level.INFO, "Reading data for all units");
            units = ejbU.findAllUnits();
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all the Unit data from an especific subject: it uses
     * business method findSubjectUnits.
     *
     * @param name A String that contains the words the user introduced.
     * @return An List of Units that contains the units that the method found.
     */
    @GET
    @Path("findSubjectUnits/{subjectName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findSubjectUnits(@PathParam("subjectName") String name) {
        List<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all units by subject name");
            units = ejbU.findSubjectUnits(name);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by name: it uses business method
     * findSubjectUnitsByName.
     *
     * @param name A String that contains the words the user introduced.
     * @param subject A String with the name of the subject
     * @return An List of Units that contains the units the method found.
     */
    @GET
    @Path("findSubjectUnitsByName/{unitName}/{subjectName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findSubjectUnitsByName(@PathParam("unitName") String name, @PathParam("subjectName") String subject) {
        List<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all units from a Subject by name");
            units = ejbU.findSubjectUnitsByName(name, subject);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by name: it uses business method
     * findOneSubjectUnitByName.
     *
     * @param name A String that contains the words the user introduced.
     * @param subject A String with the name of the subject
     * @return An List of Units that contains the units the method found.
     */
    @GET
    @Path("findOneSubjectUnitByName/{unitName}/{subjectName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Unit findOneSubjectUnitByName(@PathParam("unitName") String name, @PathParam("subjectName") String subject) {
        Unit unit;
        try {
            LOGGER.log(Level.INFO, "Reading data for an especific unit from a Subject by name");
            unit = ejbU.findOneSubjectUnitByName(name, subject);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return unit;
    }

    /**
     * GET method to get all Units data by date of iitialize the unit: it uses
     * business method findSubjectUnitsByDateInit.
     *
     * @param dateInit A Date that contains the date the User introduce.
     * @param subject A String with the name of the subject
     * @return An List of Unit that contains the units the method found.
     */
    @GET
    @Path("findSubjectUnitsByDateInit/{dateInit}/{subjectName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findSubjectUnitsByDateInit(@PathParam("dateInit") String dateInit, @PathParam("subjectName") String subject) {
        List<Unit> units = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            LOGGER.log(Level.INFO, "Reading data all the units from a subject by init date");
            Date date = format.parse(dateInit);
            units = ejbU.findSubjectUnitsByDateInit(date, subject);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UnitFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return units;
    }

    /**
     * GET method to get all Units data by date of end the unit: it uses
     * business method findSubjectUnitsByDateEnd.
     *
     * @param dateEnd A Date that contains the date the User introduce.
     * @param subject A String with the name of the subject
     * @return An List of Unit that contains the units the method found.
     */
    @GET
    @Path("findSubjectUnitsByDateEnd/{dateEnd}/{subjectName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findSubjectUnitsByDateEnd(@PathParam("dateEnd") String dateEnd, @PathParam("subjectName") String subject) {
        List<Unit> units = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(dateEnd);
            LOGGER.log(Level.INFO, "Reading data all the units from a subject by end date");
            units = ejbU.findSubjectUnitsByDateEnd(date, subject);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(UnitFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return units;
    }

    /**
     * GET method to get all Units data by hours: it uses business method
     * findSubjectUnitsByHours.
     *
     * @param hours An Integer that contains the number the User introduce.
     * @param subject A String with the name of the subject
     * @return An List of Unit that contains the units the method found.
     */
    @GET
    @Path("findSubjectUnitsByHours/{hours}/{subjectName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findSubjectUnitsByHours(@PathParam("hours") Integer hours, @PathParam("subjectName") String subject) {
        List<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all the units from a subject by hours");
            units = ejbU.findSubjectUnitsByHours(hours, subject);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by subject where the Teacher teach: it
     * uses business method findUnitsFromTeacherSubjects.
     *
     * @param userID A Integer with the id of the user that is logged into the
     * app.
     * @return An List of Unit that contains the units the method found.
     */
    @GET
    @Path("findUnitsFromTeacherSubjects/{userID}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findUnitsFromTeacherSubjects(@PathParam("userID") Integer userID) {
        List<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all the units from a subject where the Teacher teachs");
            units = ejbU.findUnitsFromTeacherSubjects(userID);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }

    /**
     * GET method to get all Units data by subject where the Student is
     * matriculated: it uses business method findUnitsFromStudentSubjects.
     *
     * @param userID A Integer with the id of the user that is logged into the
     * app.
     * @return An List of Unit that contains the units the method found.
     */
    @GET
    @Path("findUnitsFromStudentSubjects/{userID}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Unit> findUnitsFromStudentSubjects(@PathParam("userID") Integer userID) {
        List<Unit> units;
        try {
            LOGGER.log(Level.INFO, "Reading data for all the units from a subject where the Student is matriculated");
            units = ejbU.findUnitsFromStudentSubjects(userID);
        } catch (FindErrorException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
        return units;
    }
}
