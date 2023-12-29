package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing a subject.
 *
 * @author Irati
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllSubjects",
            query = "SELECT s FROM Subject s")
    ,
    @NamedQuery(
            name = "findByName",
            query = "SELECT s FROM Subject s WHERE s.name LIKE :subjectName")
    ,
    @NamedQuery(
            name = "findByLanguage",
            query = "SELECT s FROM Subject s WHERE s.languageType LIKE :subjectLanguage")
    ,
    @NamedQuery(
            name = "findByDateInit",
            query = "SELECT s FROM Subject s WHERE s.dateInit = :subjectDateInit")
    ,
    @NamedQuery(
            name = "findByDateEnd",
            query = "SELECT s FROM Subject s WHERE s.dateEnd = :subjectDateEnd")
    ,
    @NamedQuery(
            name = "findByTeacherName",
            query = "SELECT s FROM Subject s JOIN s.teachers t WHERE t.name LIKE :teacherName"
    )
    ,
    @NamedQuery(
            name = "findSubjectsWithXUnits",
            query = "SELECT s FROM Subject s INNER JOIN s.units u GROUP BY s HAVING COUNT(u) >= :numUnits")
    ,
    @NamedQuery(
            name = "findSubjectsWithEnrolledStudentsCount",
            query = "SELECT s FROM Subject s INNER JOIN s.enrollments e WHERE e.isMatriculate = true GROUP BY s HAVING COUNT(e) >= :numEnrolledStudents")
    ,
        @NamedQuery(
            name = "findByEnrollments",
            query = "SELECT s FROM Subject s INNER JOIN s.enrollments e WHERE e.isMatriculate = true AND e.student.id =:studentId")
    ,
        @NamedQuery(
            name = "findSubjectsByTeacherId",
            query = "SELECT s FROM Subject s JOIN s.teachers t WHERE t.id = :teacherId"
    )

})
@Entity
@Table(name = "subject", schema = "bytebuddiesbd")
@XmlRootElement
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Identification field for the subject.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * Name of the subject.
     */
    private String name;
    /**
     * Hours that the subject lasts.
     */
    private Integer hours;
    /**
     * Enumeration of the levels for the subject.
     */
    @Enumerated(EnumType.STRING)
    private LevelType levelType;
    /**
     * Enumeration of languages for the subject.
     */
    @Enumerated(EnumType.STRING)
    private LanguageType languageType;
    /**
     * Date init of the subject.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;
    /**
     * Date end of the subject.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateEnd;
    /**
     * Represents the set of teachers associated with this subject.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subject_teacher", schema = "bytebuddiesbd",
            joinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    )
    private Set<Teacher> teachers;
    /**
     * Relational field containing units of the subject.
     */
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Unit> units;
    /**
     * Relational field containing students of the subject.
     */
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<Enrolled> enrollments;
    /**
     * Relational field containing exams of the subject.
     */
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Exam> exams;
    
    //Setters and Getters
    /**
     * Gets the subject ID.
     *
     * @return the subject ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the subject ID.
     *
     * @param id the subject ID to be set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the subject.
     *
     * @return the name of the subject
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subject.
     *
     * @param name the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the hours that the subject lasts.
     *
     * @return the hours
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * Sets the hours that the subject lasts.
     *
     * @param hours the hours to be set
     */
    public void setHours(Integer hours) {
        this.hours = hours;
    }

    /**
     * Gets the level type of the subject.
     *
     * @return the level type
     */
    public LevelType getLevelType() {
        return levelType;
    }

    /**
     * Sets the level type of the subject.
     *
     * @param levelType the level type to be set
     */
    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }

    /**
     * Gets the language type of the subject.
     *
     * @return the language type
     */
    public LanguageType getLanguageType() {
        return languageType;
    }

    /**
     * Sets the language type of the subject.
     *
     * @param languageType the language type to be set
     */
    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    /**
     * Gets the start date of the subject.
     *
     * @return the start date
     */
    public Date getDateInit() {
        return dateInit;
    }

    /**
     * Sets the start date of the subject.
     *
     * @param dateInit the start date to be set
     */
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    /**
     * Gets the end date of the subject.
     *
     * @return the end date
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Sets the end date of the subject.
     *
     * @param dateEnd the end date to be set
     */
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * Gets the set of teachers associated with this entity.
     *
     * @return The set of teachers.
     */
    public Set<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Sets the set of teachers associated with this entity.
     *
     * @param teachers The set of teachers to be associated with this entity.
     */
    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Gets the units related to the subject.
     *
     * @return the set of units
     */
    @XmlTransient
    public Set<Unit> getUnits() {
        return units;
    }

    /**
     * Sets the units related to the subject.
     *
     * @param units the set of units to be set
     */
    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    @XmlTransient
    public Set<Enrolled> getEnrollments() {
        return enrollments;
    }

    /**
     * Gets the students related to the subject.
     *
     * @param enrollments
     */
    public void setEnrollments(Set<Enrolled> enrollments) {
        this.enrollments = enrollments;
    }

    /**
     * Gets the exams related to the subject.
     *
     * @return the set of exams
     */
    @XmlTransient
    public Set<Exam> getExams() {
        return exams;
    }

    /**
     * Sets the exams related to the subject.
     *
     * @param exams the set of exams to be set
     */
    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }

    //Constructors
    /**
     * Creates a new instance of the Subject class with specified attributes.
     *
     * @param id the subject ID
     * @param name the name of the subject
     * @param hours the hours that the subject lasts
     * @param levelType the level type of the subject
     * @param languageType the language type of the subject
     * @param dateInit the start date of the subject
     * @param dateEnd the end date of the subject
     * @param teachers the teachers of the subject
     * @param units the set of units related to the subject
     * @param enrollments the set of enrollments
     * @param exams the set of exams related to the subject
     */
    public Subject(Integer id, String name, Integer hours, LevelType levelType, LanguageType languageType,
            Date dateInit, Date dateEnd, Set<Teacher> teachers, Set<Unit> units, Set<Enrolled> enrollments, Set<Exam> exams) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.levelType = levelType;
        this.languageType = languageType;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.teachers = teachers;
        this.units = units;
        this.enrollments = enrollments;
        this.exams = exams;
    }

    /**
     * Creates a new instance of the Subject class with default constructor.
     */
    public Subject() {
    }

    /**
     * Computes the hash code for this object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Checks if this object is equal to another object.
     *
     * @param object the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    /**
     * Converts this object to a string representation.
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", name=" + name + ", hours=" + hours + ", levelType=" + levelType
                + ", languageType=" + languageType + ", dateInit=" + dateInit + ", dateEnd=" + dateEnd
                + ", teachers=" + teachers + ", units=" + units + ",enrollments=" + enrollments + ", exams=" + exams + '}';
    }
}
