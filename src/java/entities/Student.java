/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Irati
 * @author Olivia
 */
public class Student extends User implements Serializable{
    @Enumerated(EnumType.STRING)
    private LevelType levelType;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Mark> marks;
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Enrolled> enrollments;
    //Constructors
    public Student(String dni, String email, String name, String surname, String password, Date dateInit, UserType userType) {
        super(dni, email, name, surname, password, dateInit, userType);
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
