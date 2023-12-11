/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "subject", schema = "bytebuddiesbd")
@XmlRootElement
public class Subject implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer hours;
    private LevelType levelType;
    private LanguageType languageType;
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateEnd;
    private Teacher teacher;
    private Set<Unit> units;
    private Set<Student> students;
    private Set<Exam> exams;
    
    //Setters and Getters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getHours() {
        return hours;
    }
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    public LevelType getLevelType() {
        return levelType;
    }
    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }
    public LanguageType getLanguageType() {
        return languageType;
    }
    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }
    public Date getDateInit() {
        return dateInit;
    }
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public Set<Unit> getUnits() {
        return units;
    }
    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    public Set<Exam> getExams() {
        return exams;
    }
    public void setExams(Set<Exam> exams) {
        this.exams = exams;
    }   
   
    //Constructors
    
    public Subject(Integer id, String name, Integer hours, LevelType levelType, LanguageType languageType, Date dateInit, Date dateEnd, Teacher teacher, Set<Unit> units, Set<Student> students, Set<Exam> exams) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.levelType = levelType;
        this.languageType = languageType;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.teacher = teacher;
        this.units = units;
        this.students = students;
        this.exams = exams;
    }
    public Subject() {
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    
}
