package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import static javax.persistence.FetchType.EAGER;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Alex
 */
@NamedQueries({
    @NamedQuery(
            name = "findAllExams",
            query = "SELECT e FROM Exam e ORDER BY e.id DESC"),
    @NamedQuery(
            name = "findByDescription",
            query = "SELECT e FROM Exam e WHERE e.description LIKE :examDescription"), 
    @NamedQuery(
            name = "findAndOrderByDuration",
            query = "SELECT e FROM Exam e ORDER BY e.duration DESC"),
   /*@NamedQuery(
            name = "findByNullSolution",
            query = "SELECT e FROM Exam e LEFT JOIN Mark m on e.id = m.exam.id WHERE m.solutionFilePath = ''"), */
    @NamedQuery(
            name ="findBySubject",
            query = "SELECT e FROM Exam e WHERE e.subject.name LIKE :subjectName")
        
})

@Entity
@Table(name = "exam", schema = "bytebuddiesbd")
@XmlRootElement
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String description;
    @Temporal(javax.persistence.TemporalType.DATE)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Date dateInit;
    private Integer duration;
    private String filePath;
    @ManyToOne
    private Subject subject;
    @OneToMany(cascade=ALL,mappedBy="exam",fetch=EAGER)
    private Set<Mark> marks;

    //Setters and Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @XmlTransient
    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }

    //Constructor
    public Exam(Integer id, String description, Date dateInit, Integer duration, String filePath, Subject subject, Set<Mark> marks) {
        this.id = id;
        this.description = description;
        this.dateInit = dateInit;
        this.duration = duration;
        this.filePath = filePath;
        this.subject = subject;
        this.marks = marks;
    }

    public Exam() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Exam other = (Exam) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Exam{" + "id=" + id + ", description=" + description + ", dateInit=" + dateInit + ", duration=" + duration + ", filePath=" + filePath + ", subject=" + subject + ", marks=" + marks + '}';
    }

}
