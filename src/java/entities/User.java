/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "user", schema = "bytebuddiesbd")
@XmlRootElement
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private String dni;
    private String email;
    private String name;
    private String surname;
    private String password;
    private Date dateInit;
    private UserType userType;
    
    //Setters and Getters
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getDateInit() {
        return dateInit;
    }
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
   
    //Constructors
    public User(String dni, String email, String name, String surname, String password, Date dateInit, UserType userType) {
        this.dni = dni;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dateInit = dateInit;
        this.userType = userType;
    }
    public User() {
    }
    
}
