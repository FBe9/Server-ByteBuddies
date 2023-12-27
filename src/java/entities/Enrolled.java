package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity representing the enrollment of a student in a subject.
 *
 * @author Irati.
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllEnrollments",
            query = "Select e From Enrolled e")

})
@Entity
@Table(name = "enrolled", schema = "bytebuddiesbd")
@XmlRootElement
public class Enrolled implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Composite key for the enrollment, consisting of the subject ID and
     * student ID.
     */
    @EmbeddedId
    private EnrolledId id;

    /**
     * Reference to the associated student.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapsId("studentId")
    private Student student;

    /**
     * Reference to the associated subject.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapsId("subjectId")
    private Subject subject;

    /**
     * Flag indicating whether the student is matriculated in the subject.
     */
    private Boolean isMatriculate;

    public Enrolled() {
    }

    public Enrolled(EnrolledId id, Student student, Subject subject, Boolean isMatriculate) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.isMatriculate = isMatriculate;
    }

    
    // Setters and Getters
    /**
     * Gets the composite key for the enrollment.
     *
     * @return the enrollment ID
     */
    public EnrolledId getId() {
        return id;
    }

    /**
     * Sets the composite key for the enrollment.
     *
     * @param id the enrollment ID to be set
     */
    public void setId(EnrolledId id) {
        this.id = id;
    }

    /**
     * Gets the associated subject.
     *
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the associated subject.
     *
     * @param subject the subject to be set
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Gets the associated student.
     *
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the associated student.
     *
     * @param student the student to be set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the flag indicating whether the student is matriculated in the
     * subject.
     *
     * @return true if the student is matriculated, false otherwise
     */
    public Boolean getIsMatriculate() {
        return isMatriculate;
    }

    /**
     * Sets the flag indicating whether the student is matriculated in the
     * subject.
     *
     * @param isMatriculate true if the student is matriculated, false otherwise
     */
    public void setIsMatriculate(Boolean isMatriculate) {
        this.isMatriculate = isMatriculate;
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
        final Enrolled other = (Enrolled) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * Converts this object to a string representation.
     *
     * @return the string representation of the object
     */
    @Override
    public String toString() {
        return "Enrolled{" + "id=" + id + ", subject=" + subject + ", student=" + student + ", isMatriculate=" + isMatriculate + '}';
    }
}
