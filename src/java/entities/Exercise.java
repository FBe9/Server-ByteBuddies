/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "exercise", schema = "bytebuddiesbd")
@XmlRootElement
public class Exercise implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private LevelType levelType;
    private File file;
    private File fileSolution;
    private Date deadline;
    private Integer hours;
    private Unit unit;
    
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LevelType getLevelType() {
        return levelType;
    }
    public void setLevelType(LevelType levelType) {
        this.levelType = levelType;
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public File getFileSolution() {
        return fileSolution;
    }
    public void setFileSolution(File fileSolution) {
        this.fileSolution = fileSolution;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Integer getHours() {
        return hours;
    }
    public void setHours(Integer hours) {
        this.hours = hours;
    }
    public Unit getUnit() {
        return unit;
    }
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    //Constructors
    public Exercise(Integer id, String name, String description, LevelType levelType, File file, File fileSolution, Date deadline, Integer hours, Unit unit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.levelType = levelType;
        this.file = file;
        this.fileSolution = fileSolution;
        this.deadline = deadline;
        this.hours = hours;
        this.unit = unit;
    }
    public Exercise() {
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
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
