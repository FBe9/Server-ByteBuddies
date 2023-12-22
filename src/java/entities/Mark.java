package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
/**
 *
 * @author Alex
 */
@Entity
@Table(name = "mark", schema = "bytebuddiesbd")
@NamedQueries({
        @NamedQuery(
            name = "findAllMarks",
            query = "SELECT m FROM Mark m"),
        @NamedQuery(
            name = "findExamsByStudent",
            query = "SELECT m.exam FROM Mark m WHERE m.student.name LIKE :userName "),
        @NamedQuery(
            name = "findMarkByExam",
            query = "SELECT m FROM Mark m WHERE m.exam.id = :examId")
})

@XmlRootElement
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private MarkId id;
    
    @ManyToOne
    @MapsId("examId")
    private Exam exam;
    
    @ManyToOne
    @MapsId("studentId")
    private Student student;
    private Float markValue;
    
    private String solutionFilePath;

    //Setters and Getters

    public MarkId getId() {
        return id;
    }

    public void setId(MarkId id) {
        this.id = id;
    }
    //@XmlTransient
    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam Exam) {
        this.exam = Exam;
    }
    //@XmlTransient
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Float getMarkValue() {
        return markValue;
    }

    public void setMarkValue(Float markValue) {
        this.markValue = markValue;
    }

    public String getSolutionFilePath() {
        return solutionFilePath;
    }

    public void setSolutionFilePath(String solutionFilePath) {
        this.solutionFilePath = solutionFilePath;
    }

    //Constructors
    public Mark(Exam exam, Student student, Float markValue, String solutionFilePath) {
        this.exam = exam;
        this.student = student;
        this.markValue = markValue;
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
        return "Mark{" + "Exam=" + exam + ", student=" + student + ", markValue=" + markValue + ", solutionFilePath=" + solutionFilePath + '}';
    }
}
