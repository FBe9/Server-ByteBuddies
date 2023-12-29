package entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a student entity.
 *
 * @author Olivia
 * @author Irati
 */
@Entity
@DiscriminatorValue("Student")
@XmlRootElement
public class Student extends User {

    private static final long serialVersionUID = 1L;

    /**
     * The level type of the student (e.g., Freshman, Sophomore).
     */
    @Enumerated(EnumType.STRING)
    private LevelType levelType;

    /**
     * Set of marks associated with the student. Mapped by the "student" field
     * in the Mark entity.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Mark> marks;

    /**
     * Set of enrollments associated with the student. Mapped by the "student"
     * field in the Enrolled entity.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Enrolled> enrollments;

    /**
     * Default constructor for creating a Student object.
     */
    public Student() {
    }

    /**
     * Parameterized constructor for creating a Student object with specific
     * attributes.
     *
     * @param dni The DNI (identification number) of the student.
     * @param email The email address of the student.
     * @param name The name of the student.
     * @param surname The surname of the student.
     * @param password The password for the student's account.
     * @param dateInit The date of initiation for the student's account.
     */
    public Student(Integer dni, String email, String name, String surname, String password, Date dateInit) {
        super(dni, email, name, surname, password, dateInit);
    }

    /**
     * Gets the level type of the student.
     *
     * @return The level type of the student.
     */
    public LevelType getLevelType() {
        return levelType;
    }

    /**
     * Sets the level type of the student.
     *
     * @param levelType The level type to set for the student.
     */
    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }

    /**
     * Gets the set of marks associated with the student.
     *
     * @return The set of marks associated with the student.
     */
    @XmlTransient
    public Set<Mark> getMarks() {
        return marks;
    }

    /**
     * Sets the set of marks associated with the student.
     *
     * @param marks The set of marks to be associated with the student.
     */
    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    /**
     * Gets the set of enrollments associated with the student.
     *
     * @return The set of enrollments associated with the student.
     */
    @XmlTransient
    public Set<Enrolled> getEnrollments() {
        return enrollments;
    }

    /**
     * Sets the set of enrollments associated with the student.
     *
     * @param enrollments The set of enrollments to be associated with the
     * student.
     */
    public void setEnrollments(Set<Enrolled> enrollments) {
        this.enrollments = enrollments;
    }
}
