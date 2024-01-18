/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherService;

import encrypt.AsimetricaServer;
import entities.Teacher;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author irati
 */
@Stateless
public class EJBTeacherManager implements TeacherInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @Override
    public void createTeacher(Teacher teacher) throws CreateErrorException, EmailAlreadyExistsException {
        try {
            em.createNamedQuery("findByEmailTeacher").setParameter("userEmail", teacher.getEmail()).getSingleResult();
            throw new EmailAlreadyExistsException("User with email already exists");

        } catch (NoResultException ex) {
            try {

                String passwordClient = AsimetricaServer.decryptData(teacher.getPassword());
                String hash = AsimetricaServer.hashText(passwordClient);

                teacher.setPassword(hash);
                if (!em.contains(teacher)) {
                    teacher = em.merge(teacher);
                }
                em.persist(teacher);
            } catch (Exception e) {
                throw new CreateErrorException(e.getMessage());
            }
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) throws UpdateErrorException {
        try {
            if (!em.contains(teacher)) {
                em.merge(teacher);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    @Override
    public void deleteTeacher(Teacher teacher) throws DeleteErrorException {
        try {
            em.remove(em.merge(teacher));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    @Override
    public Teacher findTeacherById(Integer id) throws FindErrorException {
        Teacher teacher;
        try {
            teacher = em.find(Teacher.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAllTeachers() throws FindErrorException {
        List<Teacher> teachers;
        try {
            teachers = em.createNamedQuery("findAllTeachers").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return teachers;
    }


}
