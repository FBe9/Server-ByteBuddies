package entities;

import java.io.Serializable;

/**
 * Entity to establish the id for the N:M with atributes relation.
 *
 * @author irati
 */
public class EnrolledId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studentId;
    private Integer subjectId;

    /**
     * Default constructor for creating an empty enrollment ID.
     */
    public EnrolledId() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    

}
