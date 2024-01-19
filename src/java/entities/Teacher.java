package entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a teacher entity.
 *
 * @author Irati
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllTeachers",
            query = "Select t From Teacher t")
    ,
   @NamedQuery(
            name = "findByEmailTeacher",
            query = "SELECT t FROM Teacher t WHERE t.email=:userEmail")

})
@Entity
@DiscriminatorValue("Teacher")
@XmlRootElement
public class Teacher extends User {

    private static final long serialVersionUID = 1L;
    
    /**
     * The type of studies the teacher is qualified in (e.g., Bachelor's,
     * Master's).
     */
    @Enumerated(EnumType.STRING)
    private StudiesType studiesType;

    /**
     * Qualifications and additional information about the teacher.
     */
    private String qualifications;

    /**
     * Set of subjects associated with the teacher. Mapped by the "teacher"
     * field in the Subject entity.
     */
    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    private Set<Subject> subjects;

    /**
     * Parameterized constructor for creating a Teacher object with specific
     * attributes.
     *
     * @param dni The DNI (identification number) of the teacher.
     * @param email The email address of the teacher.
     * @param name The name of the teacher.
     * @param surname The surname of the teacher.
     * @param password The password for the teacher's account.
     * @param dateInit The date of initiation for the teacher's account.
     */
    public Teacher(Integer id, String email, String name, String surname, String password, Date dateInit) {
        super(id, email, name, surname, password, dateInit);
    }

    /**
     * Default constructor for creating a Teacher object.
     */
    public Teacher() {
    }

    /**
     * Gets the type of studies the teacher is qualified in.
     *
     * @return The studies type of the teacher.
     */
    public StudiesType getStudiesType() {
        return studiesType;
    }

    /**
     * Sets the type of studies the teacher is qualified in.
     *
     * @param studiesType The studies type to set for the teacher.
     */
    public void setStudiesType(StudiesType studiesType) {
        this.studiesType = studiesType;
    }

    /**
     * Gets the qualifications of the teacher.
     *
     * @return The qualifications of the teacher.
     */
    public String getQualifications() {
        return qualifications;
    }

    /**
     * Sets the qualifications of  the teacher.
     *
     * @param qualifications The qualifications to set for the teacher.
     */
    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    /**
     * Gets the set of subjects associated with the teacher.
     *
     * @return The set of subjects associated with the teacher.
     */
    @XmlTransient
    public Set<Subject> getSubjects() {
        return subjects;
    }

    /**
     * Sets the set of subjects associated with the teacher.
     *
     * @param subjects The set of subjects to be associated with the teacher.
     */
    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
