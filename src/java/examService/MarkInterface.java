package examService;

import entities.Exam;
import entities.Mark;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.Set;

/**
 *
 * @author Alex
 */
public interface MarkInterface {
    
    public void createMark(Mark mark) throws CreateErrorException;
    
    public void updateMark(Mark mark) throws UpdateErrorException;
    
    public void deleteMark(Mark mark) throws DeleteErrorException;
    
    public Set<Exam> findExamsByStudent(String userName)throws FindErrorException;
}
