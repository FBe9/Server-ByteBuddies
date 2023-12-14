package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;

/**
 *
 * @author Alex
 */
public interface ExamInterface {
    
    public void createExam(Exam exam) throws CreateErrorException;
    
    public void updateExam(Exam exam) throws UpdateErrorException;
    
    public void deleteExam(Exam exam) throws DeleteErrorException;
    
    public List<Exam> findAllExams()throws FindErrorException;
    
    public List<Exam> findByDescription(String description) throws FindErrorException;
    
    public List<Exam> findAndOrderByDuration() throws FindErrorException;
    
    public List<Exam> findByNullSolution() throws FindErrorException;
}
