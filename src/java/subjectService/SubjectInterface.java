/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subjectService;

import entities.Subject;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author irati
 */
public interface SubjectInterface {
    public void createSubject(Subject subject) throws CreateErrorException;
    public void updateSubject(Subject subject) throws UpdateErrorException;
    public void deleteSubject(Subject subject)throws DeleteErrorException;
    public Subject findSubjectById(String id) throws FindErrorException;
    public Set<Subject> findAllSubjects() throws FindErrorException;
    public Set<Subject> findSubjectsByName(String name) throws FindErrorException;
    public Set<Subject> findSubjectsByHours(Integer hours) throws FindErrorException;
    public Set<Subject> findSubjectsByTeacher(String name) throws FindErrorException;
    public Set<Subject> findSubjectByInitDate(Date date) throws FindErrorException;
    public Set<Subject> findSubjectByLanguage(String language) throws FindErrorException;
    public Set<Subject> findSubjectByEndDate(Date date) throws FindErrorException;
}
