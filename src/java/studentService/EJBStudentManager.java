/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentService;

import encrypt.AsimetricaServer;
import entities.Student;
import exceptions.CreateErrorException;
import exceptions.DeleteErrorException;
import exceptions.EmailAlreadyExistsException;
import exceptions.FindErrorException;
import exceptions.UpdateErrorException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author irati
 */
@Stateless
public class EJBStudentManager implements StudentInterface {

    @PersistenceContext(unitName = "WebBiteBuddys")
    private EntityManager em;

    @Override
    public void createStudent(Student student) throws CreateErrorException, EmailAlreadyExistsException {
        try {
            em.createNamedQuery("findByEmailStudent").setParameter("userEmail", student.getEmail()).getSingleResult();
            throw new EmailAlreadyExistsException("User with email already exists");

        } catch (NoResultException ex) {
            try {
                
                String passwordClient = AsimetricaServer.decryptData(student.getPassword());
                String hash = AsimetricaServer.hashText(passwordClient);

                student.setPassword(hash);
                if (!em.contains(student)) {
                    student = em.merge(student);
                }
                em.persist(student);
            } catch (Exception e) {
                throw new CreateErrorException(e.getMessage());
            }
        }
    }

    @Override
    public void updateStudent(Student student) throws UpdateErrorException {
        try {
            if (!em.contains(student)) {
                em.merge(student);
            }
            em.flush();
        } catch (Exception ex) {
            throw new UpdateErrorException(ex.getMessage());
        }
    }

    @Override
    public void deleteStudent(Student student) throws DeleteErrorException {
        try {
            em.remove(em.merge(student));
        } catch (Exception e) {
            throw new DeleteErrorException(e.getMessage());
        }
    }

    @Override
    public Student findStudentById(Integer id) throws FindErrorException {
        Student student;
        try {
            student = em.find(Student.class, id);
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return student;
    }

    @Override
    public List<Student> findAllStudents() throws FindErrorException {
        List<Student> students;
        try {
            students = em.createNamedQuery("findAllStudents").getResultList();
        } catch (Exception ex) {
            throw new FindErrorException(ex.getMessage());
        }
        return students;
    }

}
