package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity representing the enrollment of a student in a subject.
 * @author Irati.
 */
@NamedQueries({
    @NamedQuery(
    name= "findMatriculated", query="SELECT e FROM Enrolled e WHERE e.isMatriculate = true"),
    
})
@Entity
@Table(name = "enrolled", schema = "bytebuddiesbd")
@XmlRootElement
public class Enrolled implements Serializable {
     private static final long serialVersionUID = 1L;

    /**
     * Composite key for the enrollment, consisting of the subject ID and student ID.
     */
    @EmbeddedId
    private EnrolledId id;

    /**
     * Reference to the associated subject.
     */
    @MapsId("subjectId")
    @ManyToOne
    private Subject subject;

    /**
     * Reference to the associated student.
     */
    @MapsId("subjectStudent")
    @ManyToOne
    private Student student;

    /**
     * Flag indicating whether the student is matriculated in the subject.
     */
    private Boolean isMatriculate;

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
     * Gets the flag indicating whether the student is matriculated in the subject.
     *
     * @return true if the student is matriculated, false otherwise
     */
    public Boolean getIsMatriculate() {
        return isMatriculate;
    }

    /**
     * Sets the flag indicating whether the student is matriculated in the subject.
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
}
