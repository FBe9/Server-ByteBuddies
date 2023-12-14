package entities;

import java.io.Serializable;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 2dam
 */
public class Student extends User implements Serializable{
    private LevelType levelType;
    @OneToMany(cascade=ALL,mappedBy="student",fetch=EAGER)
    @XmlTransient
    private Set<Mark> marks;
    
    //Setters and Getters
    //Constructors
}
