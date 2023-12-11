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
 * Unit Entity class 
 * 
 * @author Nerea
 */
@NamedQueries({ 
    @NamedQuery(
            name = "findUnitByID", query = "SELECT u FROM Unit u WHERE u.id = :id"
    ),
    //Find all Units
    @NamedQuery(
            name = "findAllUnits", query = "SELECT u FROM Unit u"
    ),
    //Find Unit by Name
    @NamedQuery(
            name = "findUnitByName", query = "SELECT u FROM Unit u WHERE u.name LIKE :name"
    ),
    //Find Unit by DateInit
    @NamedQuery(
            name = "findUnitsByDateInit", query = "SELECT u FROM Unit u WHERE u.dateInit = :dateInit"
    ),
    //Find Unit by DateFin 
    @NamedQuery(
            name = "findUnitsByDateEnd", query = "SELECT u FROM Unit u WHERE u.dateEnd = :dateEnd"
    ),
    //Find Unit by Hours
    @NamedQuery(
            name = "findUnitsByHours", query = "SELECT u FROM Unit u WHERE u.hours = :hours"
    ),
    //Find Unit by Subject
    @NamedQuery(
            name = "findUnitsBySubject", query = "SELECT u FROM Unit u WHERE u.subject.name LIKE :subjectName"
    )
    })

@Entity
@Table(name = "unit", schema = "bytebuddiesbd")
@XmlRootElement
public class Unit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateEnd;
    private Integer hours;
    @OneToMany(mappedBy = "unit", fetch = FetchType.EAGER)
    private Set<Exercise> exercises;
    @ManyToOne
    private Subject subject;

    //Setters and Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
    @XmlTransient
    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    //Constructors
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
    public Unit() {
    }
    
    //HasCode
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    //Equals
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
