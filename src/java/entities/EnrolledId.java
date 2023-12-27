package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Entity to establish the id for the N:M with atributes relation.
 *
 * @author irati
 */
@Embeddable
public class EnrolledId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer studentId;
    private Integer subjectId;

    /**
     * Default constructor for creating an empty enrollment ID.
     */
    public EnrolledId() {
    }

    public EnrolledId(Integer studentId, Integer subjectId) {
        this.studentId = studentId;
        this.subjectId = subjectId;
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

    @Override
    public int hashCode() {
        int result = studentId != null ? studentId.hashCode() : 0;
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }

}
