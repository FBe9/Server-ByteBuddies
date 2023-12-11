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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author irati
 */
public class EJBSubjectManager implements SubjectInterface{
    @PersistenceContext(unitName = "Server-ByteBuddiesPU")
    private EntityManager em;
   

    @Override
    public void createSubject(Subject subject) throws CreateErrorException {
       try{
           em.persist(subject);
       }catch(Exception ex){
           throw new CreateErrorException(ex.getMessage());
       }
    }

    @Override
    public void updateSubject(Subject subject) throws UpdateErrorException {
        try{
             if (!em.contains(subject)) {
                em.merge(subject);
            }
            em.flush();
       }catch(Exception ex){
           throw new UpdateErrorException(ex.getMessage());
       }
    }

    @Override
    public void deleteSubject(Subject subject) throws DeleteErrorException {
        //Mirar
        try{
            em.remove(em.merge(subject));
        }catch(Exception e){
            throw new DeleteErrorException(e.getMessage());
        }
    }

    @Override
    public Set<Subject> findAllSubjects() throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findAllSubjects").getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Subject> findSubjectsByName(String name) throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findByName").setParameter("subjectName", name).getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Subject> findSubjectsByHours(Integer hours) throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findByHours").setParameter("subjectHours", hours).getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Subject> findSubjectsByTeacher(String name) throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findByTeacherName").setParameter("teacherName", name).getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Subject> findSubjectByInitDate(Date date) throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findByDateInit").setParameter("subjectDateInit", date).getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Subject> findSubjectByLanguage(String language) throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findByLanguage").setParameter("subjectLanguage", language).getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Set<Subject> findSubjectByEndDate(Date date) throws FindErrorException {
        Set<Subject> subjects;
        try{
            subjects = (Set<Subject>) em.createNamedQuery("findByDateEnd").setParameter("subjectDateEnd", date).getResultList();
            return subjects;
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
    }

    @Override
    public Subject findSubjectById(String id) throws FindErrorException {
        Subject subject= null;
        try{
            em.find(Subject.class, id);
        }catch(Exception ex){
            throw new FindErrorException(ex.getMessage());
        }
       return subject; 
    }
    
}
