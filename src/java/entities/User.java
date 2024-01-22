package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Represents a user entity in the system.
 *
 * @author Nerea
 * @author Irati
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllUsers",
            query = "Select u From User u")
    ,
    @NamedQuery(
            name = "findStudents",
            query = "SELECT u FROM User u WHERE TYPE(u) = Student"
    )
    ,
    @NamedQuery(
            name = "findTeachers",
            query = "SELECT u FROM User u WHERE TYPE(u) = Teacher")
    ,
   @NamedQuery(
            name = "login",
            query = "SELECT u FROM User u WHERE u.email = :userEmail AND u.password = :userPassword"
    )
    ,
   @NamedQuery(
            name = "findByEmail",
            query = "SELECT u FROM User u WHERE u.email=:userEmail")

})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "user", schema = "bytebuddiesbd")
@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * The type of the user (e.g., Student, Teacher). Note: This field is not
     * insertable or updatable.
     */
    @Column(insertable = false, updatable = false)
    private String user_type;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The first name of the user.
     */
    private String name;

    /**
     * The last name of the user.
     */
    private String surname;

    /**
     * The password associated with the user. Note: For security reasons, avoid
     * exposing or storing plain passwords.
     */
    private String password;

    /**
     * The date and time when the user was initialized or created.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;

    /**
     * Constructs a user with the specified parameters.
     *
     * @param id The user's ID.
     * @param email The user's email.
     * @param name The user's name.
     * @param surname The user's surname.
     * @param password The user's password.
     * @param dateInit The date of user initialization.
     */
    public User(Integer id, String email, String name, String surname, String password, Date dateInit) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.dateInit = dateInit;
    }

    /**
     * Default constructor for a user.
     */
    public User() {
    }

    /**
     * Gets the user type.
     *
     * @return The user type.
     */
    public String getUser_type() {
        return user_type;
    }

    /**
     * Sets the user type.
     *
     * @param user_type The user type to set.
     */
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    /**
     * Gets the user's ID.
     *
     * @return The user's ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id The user's ID to set.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the user's email.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email The user's email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's name.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name The user's name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's surname.
     *
     * @return The user's surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the user's surname.
     *
     * @param surname The user's surname to set.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the user's password.
     *
     * @return The user's password.
     */
    @XmlTransient
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password The user's password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the date of user initialization.
     *
     * @return The date of user initialization.
     */
    public Date getDateInit() {
        return dateInit;
    }

    /**
     * Sets the date of user initialization.
     *
     * @param dateInit The date of user initialization to set.
     */
    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }
}
