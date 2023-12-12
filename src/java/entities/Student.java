/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.OneToMany;

/**
 *
 * @author 2dam
 */
public class Student extends User implements Serializable{
    private LevelType levelType;
    @OneToMany(cascade=ALL,mappedBy="student",fetch=EAGER)
    private Set<Mark> marks;
    
    //Setters and Getters
    //Constructors
}
