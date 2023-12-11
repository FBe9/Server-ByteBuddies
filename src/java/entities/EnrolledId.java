package entities;

import java.io.Serializable;
import java.util.Objects;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

/**
 * Entity to establish the id for the N:M with atributes relation.
 *
 * @author irati
 */
public class EnrolledId implements Serializable {

    private Long studentId;
    private Long subjectId;

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
    public EnrolledId(Long studentId, Long subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    /**
     * Gets the student ID associated with this enrollment ID.
     *
     * @return the student ID
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID for this enrollment ID.
     *
     * @param studentId the student ID to be set
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the subject ID associated with this enrollment ID.
     *
     * @return the subject ID
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the subject ID for this enrollment ID.
     *
     * @param subjectId the subject ID to be set
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.studentId);
        hash = 31 * hash + Objects.hashCode(this.subjectId);
        return hash;
    }

    /**
     * Checks if this enrollment ID is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EnrolledId other = (EnrolledId) obj;
        return Objects.equals(this.studentId, other.studentId) && Objects.equals(this.subjectId, other.subjectId);
    }

    /**
     * Generates a string representation of this enrollment ID.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return "EnrolledId{" + "studentId=" + studentId + ", subjectId=" + subjectId + '}';
    }

}
