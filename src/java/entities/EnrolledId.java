package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Represents the composite primary key for the many-to-many relationship with attributes.
 * This class is used as an embedded identifier in the Enrolled entity.
 * 
 * @author irati
 */
@Embeddable
public class EnrolledId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studentId;
    private Integer subjectId;

    /**
     * Constructs a new EnrolledId with default values.
     * This default constructor is used for creating an empty enrollment ID.
     */
    public EnrolledId() {
    }

    /**
     * Constructs a new EnrolledId with the specified student and subject IDs.
     * 
     * @param studentId The ID of the student.
     * @param subjectId The ID of the subject.
     */
    public EnrolledId(Integer studentId, Integer subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    /**
     * Gets the ID of the student.
     * 
     * @return The student ID.
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student.
     * 
     * @param studentId The student ID to set.
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the ID of the subject.
     * 
     * @return The subject ID.
     */
    public Integer getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the ID of the subject.
     * 
     * @param subjectId The subject ID to set.
     */
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * Compares this EnrolledId with another object for equality.
     * 
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        EnrolledId that = (EnrolledId) obj;

        if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) {
            return false;
        }
        return subjectId != null ? subjectId.equals(that.subjectId) : that.subjectId == null;
    }

    /**
     * Generates a hash code for this EnrolledId.
     * 
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }
}
