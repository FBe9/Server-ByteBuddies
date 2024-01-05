package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;


/**
 *
 * @author Alex
 */
@Embeddable
public class MarkId implements Serializable{

    private Integer examId;
    private Integer studentId;

    public MarkId() {
    }

    public MarkId(Integer examId, Integer studentId) {
        this.examId = examId;
        this.studentId = studentId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.examId);
        hash = 47 * hash + Objects.hashCode(this.studentId);
        return hash;
    }

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

    
    
    @Override
    public String toString() {
        return "MarkId{" + "examId=" + examId + ", studentId=" + studentId + '}';
    }

}
