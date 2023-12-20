package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alex
 */
@Local
public interface ExamInterface {
    
    public void createExam(Exam exam) throws CreateErrorException;
    
    public void updateExam(Exam exam) throws UpdateErrorException;
    
    public void deleteExam(Exam exam) throws DeleteErrorException;
    
    public List<Exam> findAllExams()throws FindErrorException;
    
    public Exam findExamById(Integer id) throws FindErrorException;
    
    public List<Exam> findByDescription(String description) throws FindErrorException;
    
    public List<Exam> findBySolution(String solutionFilePath) throws FindErrorException;
    
    public List<Exam> findBySubject(String subject) throws FindErrorException;
}
