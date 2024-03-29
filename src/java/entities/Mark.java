package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity representing the marks. It has the following fields: markId, exam,
 * student, markValue and solutionFilePath.
 *
 *
 * @author Alex
 */
@Entity
@Table(name = "mark", schema = "bytebuddiesbd")
@NamedQueries({
    @NamedQuery(
            name = "findAllMarks",
            query = "SELECT m FROM Mark m")
    ,
        @NamedQuery(
            name = "findExamsByStudent",
            query = "SELECT m.exam FROM Mark m WHERE m.student.name LIKE :userName ")
    ,
        @NamedQuery(
            name = "findMarkByExam",
            query = "SELECT m FROM Mark m WHERE m.exam.id = :examId")
})

@XmlRootElement
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the mark. Embedded by the exam and student ids.
     */
    @EmbeddedId
    private MarkId id;

    /**
     * The exam id the mark is assigned to.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapsId("examId")
    private Exam exam;

    /**
     * The student id the mark is assigned to.
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @MapsId("studentId")
    private Student student;

    /**
     * The student's score in the exam.
     */
    private Float markValue;

    /**
     * The path where the student's file is stored in the DB.
     */
    private String solutionFilePath;

    /**
     * Returns the id of the mark.
     *
     * @return the MarkId value of the id.
     */
    public MarkId getId() {
        return id;
    }

    /**
     * Sets the id of the mark.
     *
     * @param id The ID of the mark
     */
    public void setId(MarkId id) {
        this.id = id;
    }

    /**
     * Gets the exam object the mark is assigned to.
     *
     * @return exam object.
     */
    public Exam getExam() {
        return exam;
    }

    /**
     * Sets the exam the mark is assigned to.
     *
     * @param Exam The exam.
     */
    public void setExam(Exam Exam) {
        this.exam = Exam;
    }

    /**
     * Gets the student the mark is assigned to.
     *
     * @return student object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the student the mark is assigned to.
     *
     * @param student The student.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Gets the student's score.
     *
     * @return float value of the score.
     */
    public Float getMarkValue() {
        return markValue;
    }

    /**
     * Sets the student's score in the exam.
     *
     * @param markValue The value of the mark.
     */
    public void setMarkValue(Float markValue) {
        this.markValue = markValue;
    }

    /**
     * Gets the path to the student's solution.
     *
     * @return String value with the location of the document with the student's
     * solution. The program reads this string and sends the client the entire
     * document.
     */
    public String getSolutionFilePath() {
        return solutionFilePath;
    }

    /**
     * Sets the solutionFilePath
     *
     * @param solutionFilePath The path.
     */
    public void setSolutionFilePath(String solutionFilePath) {
        this.solutionFilePath = solutionFilePath;
    }

    /**
     * Default constructor with parameters.
     *
     * @param exam The exam it belongs to.
     * @param student The student it belongs to.
     * @param markValue The value of the mark.
     * @param solutionFilePath The path.
     */
    public Mark(Exam exam, Student student, Float markValue, String solutionFilePath) {
        this.exam = exam;
        this.student = student;
        this.markValue = markValue;
        this.solutionFilePath = solutionFilePath;
    }

    /**
     * Default empty constructor.
     */
    public Mark() {
    }

    /**
     * Computes the hash code for the mark.
     *
     * @return The hashed code of the mark.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * Checks for other Mark objects equal to this one.
     *
     * @param obj The object to check
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
        final Mark other = (Mark) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
