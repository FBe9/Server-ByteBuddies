package entities;

import java.io.Serializable;

/**
 * Entity to establish the id for the N:M with atributes relation.
 *
 * @author irati
 */
public class EnrolledId implements Serializable {

    private Integer studentId;
    private Integer subjectId;

    /**
     * Default constructor for creating an empty enrollment ID.
     */
    public EnrolledId() {
    }

    /**
     * Parameterized constructor for creating an enrollment ID with specified
     * account and customer IDs.
     *
     * @param studentId the account ID
     * @param subjectId the customer ID
     */
    public EnrolledId(Integer studentId, Integer subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    /**
     * Gets the student ID associated with this enrollment ID.
     *
     * @return the student ID
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID for this enrollment ID.
     *
     * @param studentId the student ID to be set
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the subject ID associated with this enrollment ID.
     *
     * @return the subject ID
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the subject ID for this enrollment ID.
     *
     * @param subjectId the subject ID to be set
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

}
