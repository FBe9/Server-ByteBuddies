package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing Unit. It has the following
 * fields: unit id, name, description, dateInit, dateEnd, hours, exercises list and subject object. 
 *
 * @author Nerea
 */
@NamedQueries({
    //Find unit by ID
    @NamedQuery(
            name = "findUnitByID", query = "SELECT u FROM Unit u WHERE u.id = :id"
    )
    ,
    //Find all Units
    @NamedQuery(
            name = "findAllUnits", query = "SELECT u FROM Unit u"
    )
    ,
    //Find Units by Subject name
    @NamedQuery(
            name = "findSubjectUnits", query = "SELECT u FROM Unit u WHERE u.subject.name = :subjectName"
    )
    ,
    //Find Units by similar Name from a Subject
    @NamedQuery(
            name = "findSubjectUnitsByName", query = "SELECT u FROM Unit u WHERE u.name LIKE :name AND u.subject.name = :subjectName"
    )
    ,
    //Find Units by especific Name from a Subject
    @NamedQuery(
            name = "findOneSubjectUnitByName", query = "SELECT u FROM Unit u WHERE u.name = :name AND u.subject.name = :subjectName"
    )
    ,
    //Find Units by DateInit from a Subject
    @NamedQuery(
            name = "findSubjectUnitsByDateInit", query = "SELECT u FROM Unit u WHERE u.dateInit = :dateInit AND u.subject.name = :subjectName"
    )
    ,
    //Find Units by DateFin from a Subject
    @NamedQuery(
            name = "findSubjectUnitsByDateEnd", query = "SELECT u FROM Unit u WHERE u.dateEnd = :dateEnd AND u.subject.name = :subjectName"
    )
    ,
    //Find Units by Hours from a Subject
    @NamedQuery(
            name = "findSubjectUnitsByHours", query = "SELECT u FROM Unit u WHERE u.hours = :hours"
    )
})

@Entity
@Table(name = "unit", schema = "bytebuddiesbd")
@XmlRootElement
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identification field for the unit.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * Name of the Unit.
     */
    private String name;
    /**
     * The description 
     */
    private String description;
    /**
     * The Date of the first lesson of the Unit.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;
    /**
     * The Date of the last lesson of the Unit.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateEnd;
    /**
     * Hours that the Unit lasts.
     */
    private Integer hours;
    /**
     * Relational field containing exercises of the unit.
     */
    @OneToMany(mappedBy = "unit", fetch = FetchType.EAGER)
    private Set<Exercise> exercises;
    /**
     * Relational field containing subject of a unit.
     */
    @ManyToOne
    private Subject subject;

    //Setters and Getters
    /**
     * Gets the unit ID.
     *
     * @return An Integer with the unit ID.
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * Sets the unit ID.
     *
     * @param id the unit ID to be set.
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * Gets the unit Name. 
     * 
     * @return A String with the unit name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the unit Name.
     * 
     * @param name the unit name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the unit Description.  
     * 
     * @return A String with the unit description.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the unit Description.
     * 
     * @param description the unit description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the unit DateInit. 
     * 
     * @return A Date with the unit dateInit.
     */
    public Date getDateInit() {
        return dateInit;
    }
    
    /**
     * Sets the unit DateInit.
     * 
     * @param dateInit the unit dateInit to be set.
     */
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }
    
    /**
     * Gets the unit DateEnd. 
     * 
     * @return A Date with the unit dateEnd.
     */
    public Date getDateEnd() {
        return dateEnd;
    }
    
    /**
     * Sets the unit DateEnd.
     * 
     * @param dateEnd the unit dateEnd to be set.
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    /**
     * Gets the unit Hours.  
     * 
     * @return An Integer with the unit hours.
     */
    public Integer getHours() {
        return hours;
    }
    
    /**
     * Sets the unit Hours.
     * 
     * @param hours the unit hours to be set.
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    
    /**
     * Gets the unit Exercises.  
     * 
     * @return A Set with the unit exercises.
     */
    @XmlTransient
    public Set<Exercise> getExercises() {
        return exercises;
    }
    
    /**
     * Sets the unit Exercises.
     * 
     * @param exercises the unit exercises to be set.
     */
    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    /**
     * Gets the unit Subject. 
     * 
     * @return A Subject object with the unit Subject.
     */
    public Subject getSubject() {
        return subject;
    }
    
    /**
     * Sets the unit Subject.
     * 
     * @param subject the unit subject to be set.
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    //Constructors
    /**
     * Creates a new instance of the Unit class with specified attributes.
     * 
     * @param id the unit id.
     * @param name the name of the unit.
     * @param description the description of the unit.
     * @param dateInit the dateInit of the unit.
     * @param dateEnd the dateEnd of the unit.
     * @param hours the hours of the unit. 
     * @param exercises the exercises of the unit.
     * @param subject the subject of the unit.
     */
    public Unit(Integer id, String name, String description, Date dateInit, Date dateEnd, Integer hours, Set<Exercise> exercises, Subject subject) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.hours = hours;
        this.exercises = exercises;
        this.subject = subject;
    }
    
    /**
     * Creates a new instance of the Unit class with default constructor.
     */
    public Unit() {

    }

    //HasCode
    /**
     * Computes the hash code for this object.
     * 
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    //Equals
    /**
     * Checks if this object is equal to another object.
     *
     * @param object the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;

        if (!((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))) {
            return false;
        }
        return true;
    }
}
