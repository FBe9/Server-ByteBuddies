package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Alex
 */
@Entity
@Table(name = "mark", schema = "bytebuddiesbd")
@NamedQueries({
        @NamedQuery(
            name = "findExamsByStudent",
            query = "SELECT m.exam FROM Mark m WHERE m.student.name = :userName")
})

@XmlRootElement
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private MarkId id;
    @MapsId("examId")
    @ManyToOne
    private Exam Exam;
    @MapsId("studentId")
    @ManyToOne
    private Student student;
    private Float value;
    @Enumerated(EnumType.STRING)
    private CallType callType;
    private String solutionFilePath;

    //Setters and Getters
    public Exam getExam() {
        return Exam;
    }

    public void setExam(Exam Exam) {
        this.Exam = Exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public String getSolutionFilePath() {
        return solutionFilePath;
    }

    public void setSolutionFilePath(String solutionFilePath) {
        this.solutionFilePath = solutionFilePath;
    }

    //Constructors
    public Mark(Exam Exam, Student student, Float value, CallType callType, String solutionFilePath) {
        this.Exam = Exam;
        this.student = student;
        this.value = value;
        this.callType = callType;
        this.solutionFilePath = solutionFilePath;
    }

    public Mark() {
    }
  
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Mark other = (Mark) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
  
    @Override
    public String toString() {
        return "Mark{" + "Exam=" + Exam + ", student=" + student + ", value=" + value + ", callType=" + callType + ", solutionFilePath=" + solutionFilePath + '}';
    }
}
