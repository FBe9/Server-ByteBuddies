/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitis;

import java.io.File;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
@Entity
@Table(name = "mark", schema = "bytebuddiesbd")
@XmlRootElement
class Mark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Exam Exam;
    private Student student;
    private Float value;
    private CallType callType;
    private File fileSolution;
    
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

    public File getFileSolution() {
        return fileSolution;
    }

    public void setFileSolution(File fileSolution) {
        this.fileSolution = fileSolution;
    }
    
    //Constructors
    public Mark(Exam Exam, Student student, Float value, CallType callType, File fileSolution) {
        this.Exam = Exam;
        this.student = student;
        this.value = value;
        this.callType = callType;
        this.fileSolution = fileSolution;
    }

    public Mark() {
    }
    
}
