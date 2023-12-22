package examService;

import entities.Exam;
import entities.Mark;
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
public interface MarkInterface {
    
    public void createMark(Mark mark) throws CreateErrorException;
    
    public void updateMark(Mark mark) throws UpdateErrorException;
    
    public void deleteMark(Mark mark) throws DeleteErrorException;
    
    public List<Mark> findAllMarks() throws FindErrorException;
    
    public Mark findMarkById(Integer examId, Integer studentId) throws FindErrorException;
    
    public List<Exam> findExamsByStudent(String userName)throws FindErrorException;
    
    public List<Mark> findMarkByExam(Integer examId) throws FindErrorException;
}
