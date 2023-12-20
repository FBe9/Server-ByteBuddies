package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Irati
 */
@Entity
@DiscriminatorValue("teacher")
@XmlRootElement
public class Teacher extends User {
    private static final long serialVersionUID = 1L;
    
    @Enumerated(EnumType.STRING)
    private StudiesType studiesType;
    private String qualifications;
    @ManyToMany(mappedBy = "teachers", fetch = FetchType.EAGER)
    private Set<Subject> subjects;

    //Constructors
    public Teacher(Integer dni, String email, String name, String surname, String password, Date dateInit) {
        super(dni, email, name, surname, password, dateInit);
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
    

}
