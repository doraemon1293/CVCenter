/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.entity.Student;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * The service providers for database transactions related to
 * Student entity The functions are just corresponding to the DAO class, because
 * the system's functionality is basic.
 */
public interface StudentService {

    public Student getStudentByID(int id);

    public List<Student> listStudent();

    public void saveStudent(Student student);

    public void deleteStudent(Student student);
}
