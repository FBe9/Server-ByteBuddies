package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity representing exercise. It contains the following
 * fields: exercise id, exercise unit, exercise number, exercise description, 
 * exercise level type, exercise file, exercise file solution, exercise deadline,
 * ´exercise hours.
 *
 * @author Leire
 */
@Entity
@Table(name = "exercise", schema = "bytebuddiesbd")
@NamedQueries({
    //Teachers queries
    @NamedQuery(
            name = "getExercisesByID", 
            query = "SELECT e FROM Exercise e WHERE e.id=:id"
    )
    , @NamedQuery(
            name = "getAllExercises",
            query = "SELECT e FROM Exercise e"
    )
    ,
    @NamedQuery(
            name = "getExercisesByNumber",
            query = "SELECT e FROM Exercise e WHERE e.number=:number"
    )
    ,
    @NamedQuery(
            name = "getExercisesByDate",
            query = "SELECT e FROM Exercise e WHERE e.deadline=:deadline"
    )
    ,
    @NamedQuery(
            name = "getExercisesByLevel",
            query = "SELECT e FROM Exercise e WHERE e.levelType=:levelType"
    )
    //Teacher and student queries
    ,
    @NamedQuery(
            name = "getExercisesByUnitName",
            query = "SELECT e FROM Exercise e WHERE e.unit.name=:name"
    )
    //Student queries
    , 
    @NamedQuery(
            name = "getExercisesByNumberAndUnitName",
            query = "SELECT e FROM Exercise e WHERE e.number=:number AND e.unit.name=:name"
    )
    ,
    @NamedQuery(
            name = "getExercisesByDateAndUnitName",
            query = "SELECT e FROM Exercise e WHERE e.deadline=:deadline AND e.unit.name=:name"
    )
    ,
    @NamedQuery(
            name = "getExercisesByLevelAndUnitName",
            query = "SELECT e FROM Exercise e WHERE e.levelType=:levelType AND e.unit.name=:name"
    )
})
@XmlRootElement
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Auto generated exercise id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Unit exercise
     */
    @ManyToOne
    private Unit unit;

    /**
     * Exercise number
     */
    private Integer number;

    /**
     * Exercise description
     */
    private String description;

    /**
     * Enumeration for the level of the exercise BEGGINER MEDIUM EXPERIENCED
     */
    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    /**
     * This is the file with the exercise
     */
    private String file;

    /**
     * This is the file with the exercise solution
     */
    private String fileSolution;

    /**
     * Date of the deadline
     */
    @Temporal(TemporalType.DATE)
    private Date deadline;

    /**
     * The hours that the exercise takes
     */
    private Integer hours;

    //Costructors
    /**
     * Empty constructor
     */
    public Exercise() {
    }

    /**
     * Constructor with parameters
     *
     * @param id ID of the exercise.
     * @param unit Unit of the exercise.
     * @param number Number of the exercise.
     * @param description Description of the exercise.
     * @param levelType Level type of the exercise.
     * @param file File with the exercise.
     * @param fileSolution File with the exercise solution.
     * @param deadline Deadline of the exercise.
     * @param hours Hours  of the exercise.
     */
    public Exercise(Integer id, Unit unit, Integer number, String description, LevelType levelType, String file, String fileSolution, Date deadline, Integer hours) {
        this.id = id;
        this.unit = unit;
        this.number = number;
        this.description = description;
        this.levelType = levelType;
        this.file = file;
        this.fileSolution = fileSolution;
        this.deadline = deadline;
        this.hours = hours;
    }

    //Getters and Setters
    /**
     * Gets the id
     *
     * @return The ID of the exercise.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id The ID to be set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the unit
     *
     * @return The unit of the exercise.
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets the unit
     *
     * @param unit The unit to be set.
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets the number
     *
     * @return The number of the exercise.
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets the number
     *
     * @param number The number to be set.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Gets the description
     *
     * @return The description of the exercise.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     *
     * @param description The description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the level type
     *
     * @return The level type of the exercise.
     */
    public LevelType getLevelType() {
        return levelType;
    }

    /**
     * Sets the level type
     *
     * @param levelType The level type to be set.
     */
    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }

    /**
     * Gets the file
     *
     * @return The file with the exercise.
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the file
     *
     * @param file The file to be set.
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * Gets the file solution
     *
     * @return The file with the exercise solution.
     */
    public String getFileSolution() {
        return fileSolution;
    }

    /**
     * Sets the file solution
     *
     * @param fileSolution The file with the solution to be set.
     */
    public void setFileSolution(String fileSolution) {
        this.fileSolution = fileSolution;
    }

    /**
     * Gets the deadline
     *
     * @return The deadline of the exercise.
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Sets the deadline
     *
     * @param deadline The deadline to be set.
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * Gets the hours
     *
     * @return The hours of the exercise.
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * Sets the hours
     *
     * @param hours The hours to be set.
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
