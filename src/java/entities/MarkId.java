package entities;

import java.io.Serializable;
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
    public String toString() {
        return "MarkId{" + "examId=" + examId + ", studentId=" + studentId + '}';
    }

}
