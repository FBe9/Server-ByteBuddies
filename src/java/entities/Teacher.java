package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Irati
 */
@Entity
@Table(name = "teacher", schema = "bytebuddiesbd")
@XmlRootElement
public class Teacher extends User implements Serializable {

    @Enumerated(EnumType.STRING)
    private StudiesType studiesType;
    private String qualifications;
    @OneToMany(mappedBy = "teacher")
    private Set<Subject> subjects;
    @OneToMany(mappedBy = "teacher")
    private Set<Exam> Exams;

    //Constructors
    public Teacher(String dni, String email, String name, String surname, String password, Date dateInit, UserType userType) {
        super(dni, email, name, surname, password, dateInit, userType);
    }

    public Teacher() {
    }
    //Setters and Getters

    public StudiesType getStudiesType() {
        return studiesType;
    }

    public void setStudiesType(StudiesType studiesType) {
        this.studiesType = studiesType;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    @XmlTransient
    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
    @XmlTransient
    public Set<Exam> getExams() {
        return Exams;
    }

    public void setExams(Set<Exam> Exams) {
        this.Exams = Exams;
    }
    

}
