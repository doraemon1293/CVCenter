/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Student;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * implementation of StudentDAO. Every function is basic and
 * self explained
 */
@Repository("StudentDAO")
@Transactional
public class HibernateStudentDAO implements StudentDAO {

    //the session factory bean being used throughout.
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateStudentDAO() {
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Student getStudentById(int id) {
        Session session = currentSession();
        Student student = (Student) session.get(Student.class, id);
        return student;
    }

    @Override
    public List<Student> listStudent() {
        List<Student> studentList = null;
        Query query = currentSession().createQuery("from Student");
        studentList = (List<Student>) query.list();
        return studentList;
    }

    @Override
    public void saveStudent(Student student) {
        Session session = currentSession();
        session.saveOrUpdate(student);
    }

    @Override
    public void deleteStudent(Student student) {
        Session session = currentSession();
        session.delete(student);
    }
}
