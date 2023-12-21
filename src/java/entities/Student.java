package entities;

import java.util.Date;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * @author Irati
 * @author Olivia
 */
@Entity
@DiscriminatorValue("student")
@XmlRootElement
public class Student extends User {
    private static final long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private LevelType levelType;
    @OneToMany(cascade=ALL,mappedBy="student",fetch=FetchType.EAGER)
    private Set<Mark> marks;
    @OneToMany(cascade=ALL, mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Enrolled> enrollments;
    //Constructors
    public Student(Integer dni, String email, String name, String surname, String password, Date dateInit) {
        super(dni, email, name, surname, password, dateInit);
    }
    public Student() {    
    }
    //Setters and Getters
    public LevelType getLevelType() {
        return levelType;
    }

    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }
    @XmlTransient
    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
    @XmlTransient
    public Set<Enrolled> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrolled> enrollments) {
        this.enrollments = enrollments;
    }
}
