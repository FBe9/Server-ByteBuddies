package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Embeddable class representing the ID of the entity Mark.
 *
 * @author Alex
 */
@Embeddable
public class MarkId implements Serializable {

    /**
     * The exam ID the mark belongs to.
     */
    private Integer examId;

    /**
     * The student ID the mark belongs to.
     */
    private Integer studentId;

    /**
     * Default empty constructor for Mark ID.
     */
    public MarkId() {
    }

    /**
     * Default constructor with params.
     *
     * @param examId The ID of the exam the mark belongs to.
     * @param studentId The ID of the student the mark belongs to.
     */
    public MarkId(Integer examId, Integer studentId) {
        this.examId = examId;
        this.studentId = studentId;
    }

    /**
     * Returns the ID of the exam.
     *
     * @return The integer value of the ID.
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * Sets the ID of the exam.
     *
     * @param examId The ID of the exam.
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * Returns the ID of the student.
     *
     * @return The integer value of the ID.
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * Sets the ID of the student.
     *
     * @param studentId The ID of the student.
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * Computes the hash code for the Mark ID.
     *
     * @return The hashed code of the Mark ID.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.examId);
        hash = 47 * hash + Objects.hashCode(this.studentId);
        return hash;
    }

    /**
     * Checks for other MarkId objects equal to this one.
     *
     * @param obj The object to check for equality.
     * @return boolean value, true if it's equal to any other mark, false if
     * opposite.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MarkId other = (MarkId) obj;
        if (!Objects.equals(this.examId, other.examId)) {
            return false;
        }
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        return true;
    }
}
