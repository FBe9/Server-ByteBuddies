package entities;

import java.io.Serializable;

/**
 * Entity to establish the id for the N:M with atributes relation.
 *
 * @author irati
 */
public class EnrolledId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer dni;
    private Integer id;

    /**
     * Default constructor for creating an empty enrollment ID.
     */
    public EnrolledId() {
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

}
