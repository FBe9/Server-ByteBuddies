package examService;

import entities.Exam;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface ExamInterface {
    
    public void createExam(Exam exam) throws CreateErrorException;
    
    public void updateExam(Exam exam) throws UpdateErrorException;
    
    public void deleteExam(Exam exam) throws DeleteErrorException;
    
    public Set<Exam> findAllExams()throws FindErrorException;
    
    public Set<Exam> findByDescription(String description) throws FindErrorException;
    
    public Set<Exam> findAndOrderByDuration() throws FindErrorException;
    
    public Set<Exam> findByNullSolution() throws FindErrorException;
}
