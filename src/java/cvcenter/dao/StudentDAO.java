/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Student;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * DAO class for Student entity
 */
public interface StudentDAO {

    //4 basic functions only
    public Student getStudentById(int id);

    public List<Student> listStudent();

    public void saveStudent(Student student);

    public void deleteStudent(Student student);
}
