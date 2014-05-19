/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.dao.StudentDAO;
import cvcenter.entity.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * Implementation of the Service class for Student Entity Very
 * simple and make user of the corresponding StudentDAO class
 */
@Service("StudentService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImplStudentService implements StudentService {

    @Autowired
    private StudentDAO studentdao;

    public ImplStudentService() {
    }

    @Override
    public Student getStudentByID(int id) {
        return studentdao.getStudentById(id);
    }

    @Override
    public List<Student> listStudent() {
        return studentdao.listStudent();
    }

    @Override
    public void saveStudent(Student student) {
        studentdao.saveStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentdao.deleteStudent(student);
    }
}
