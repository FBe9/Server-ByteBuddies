package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing the exams. It has the following fields: exam id,
 * description, date, duration, file path, subject, marks.
 *
 * @author Alex
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllExams",
            query = "SELECT e FROM Exam e ORDER BY e.id ASC")
    ,
    @NamedQuery(
            name = "findByDescription",
            query = "SELECT e FROM Exam e WHERE e.description LIKE :examDescription")
    ,
   @NamedQuery(
            name = "findBySolution",
            query = "SELECT e FROM Exam e LEFT JOIN e.marks m  WHERE m.solutionFilePath = :solutionFilePath")
    ,
   
    @NamedQuery(
            name = "findBySubject",
            query = "SELECT e FROM Exam e WHERE e.subject.id = :subjectId")
    ,
    @NamedQuery(
            name = "setNullSubject",
            query = "UPDATE Exam SET subject.id = null WHERE id = :examId")

})

@Entity
@Table(name = "exam", schema = "bytebuddiesbd")
@XmlRootElement
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identification (id) for the exam.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The description, title or name.
     */
    private String description;

    /**
     * The enum that marks the call of the exam, First, Second, Third...
     */
    @Enumerated(EnumType.STRING)
    private CallType callType;

    /**
     * The date when the exam is programmed.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;

    /**
     * The duration value of the exam, in minutes.
     */
    private Integer duration;

    /**
     * The path to store the exam heading.
     */
    private String filePath;

    /**
     * The subject which the exam belongs to.
     */
    @ManyToOne
    private Subject subject;

    /**
     * Relational field for the grades or marks assigned to an exam.
     */
    @OneToMany(mappedBy = "exam", fetch = EAGER, cascade = CascadeType.ALL)
    private Set<Mark> marks;

    /**
     * Gets the exam id.
     *
     * @return Integer value of the id field.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the exam id
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the name of the exam.
     *
     * @return String value of the field description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the name of the exam.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the date of the exam.
     *
     * @return Date value of the dateInit field.
     */
    public Date getDateInit() {
        return dateInit;
    }

    /**
     * Sets the date of the exam.
     *
     * @param dateInit
     */
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    /**
     * Gets the duration of the exam (minutes).
     *
     * @return Integer value (in minutes) of the duration field.
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the exam (in minutes).
     *
     * @param duration
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * Gets the path where the exam heading file is stored.
     *
     * @return String value of the filePath field.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets the path to where the exam heading is stored.
     *
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the subject the exam belongs to.
     *
     * @return Subject object of the subject field.
     */
    @XmlElement(name = "subject")
    //@XmlTransient
    public Subject getSubject() {
        return subject;
    }

    /**
     * Gets the call type of the exam.
     *
     * @return The string value of the call type.
     */
    public CallType getCallType() {
        return callType;
    }

    /**
     * Sets the call type.
     *
     * @param callType
     */
    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    /**
     * Sets the subject the exam belongs to.
     *
     * @param subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Gets the marks related to the exam.
     *
     * @return Set list of the type Mark.
     */
    @XmlTransient
    public Set<Mark> getMarks() {
        return marks;
    }

    /**
     * Sets the marks collection with the marks related to the exam.
     *
     * @param marks
     */
    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    /**
     * Constructor with parameters for the Exam entity.
     *
     * @param id
     * @param description
     * @param dateInit
     * @param duration
     * @param filePath
     * @param subject
     * @param marks
     */
    public Exam(Integer id, String description, Date dateInit, Integer duration, String filePath, Subject subject, Set<Mark> marks) {
        this.id = id;
        this.description = description;
        this.dateInit = dateInit;
        this.duration = duration;
        this.filePath = filePath;
        this.subject = subject;
        this.marks = marks;
    }

    /**
     * Default empty constructor for the Exam entity.
     */
    public Exam() {
    }

    /**
     * Computes the hash code for the exam.
     *
     * @return int value of the hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Checks if this exam is equal to any other exam.
     *
     * @param obj
     * @return boolean value, true if it's equal to any other exam, false if
     * opposite.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exam other = (Exam) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * String representation of the exam entity.
     *
     * @return the string value of the representation of the entity.
     */
    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", description=" + description + ", dateInit=" + dateInit + ", duration=" + duration + ", filePath=" + filePath + ", subject=" + subject + ", marks=" + marks + '}';
    }

}
