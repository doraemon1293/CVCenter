/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.controller;

import cvcenter.entity.Company;
import cvcenter.entity.Cv;
import cvcenter.entity.Job;
import cvcenter.entity.Student;
import cvcenter.entity.Users;
import cvcenter.service.CompanyService;
import cvcenter.service.CvService;
import cvcenter.service.JobService;
import cvcenter.service.StudentService;
import cvcenter.service.UsersService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Giang Tran Hoang
 * This is the controller for the functions related to admin roles
 */
@Controller
public class AdminController {
    
    //need all those beans to do proper delete actions
    @Autowired
    StudentService studentService;
    @Autowired
    CompanyService companyService;
    @Autowired
    UsersService userService;
    @Autowired
    CvService cvService;
    @Autowired
    JobService jobService;
    
    //redirect to the start page of admin after typing in just admin.htm
    @RequestMapping(value="/admin", method= RequestMethod.GET)
    public ModelAndView viewAdmin(){
        return viewStudent();
    }
    
    //The view of the list of student account, nothing special
    @RequestMapping(value="/adminStudent", method= RequestMethod.GET)
    public ModelAndView viewStudent(){
        List<Student> studentList = studentService.listStudent();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("studentList", studentList);
        return new ModelAndView("adminStudent", model);
    }
    
    //The view of the list of Company account, nothing special
    @RequestMapping(value="/adminCompany", method= RequestMethod.GET)
    public ModelAndView viewCompany(){
        List<Company> companyList = companyService.listCompany();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("companyList", companyList);
        return new ModelAndView("adminCompany", model);
    }
    
    //Delete a student, with the student id in the url
    @RequestMapping(value="/adminDeleteStudent", method= RequestMethod.GET)
    public ModelAndView deleteStudent(@RequestParam("id") Integer id){
        Student student = studentService.getStudentByID(id);
        //lazy intition off for this function, I know it is bad, will fix it if time permits
        Users user = student.getUsers();
        //delete all CVs first
        for (Cv cv : student.getCvs()){
            cvService.deleteCV(cv);
        }
        //delete the Student
        studentService.deleteStudent(student);
        //delete the user, the roles will be deleted by the service as well
        userService.deleteUsers(user);
        return viewStudent();
    }
    
    //Delete a company account, just the same as student
    @RequestMapping(value="/adminDeleteCompany", method= RequestMethod.GET)
    public ModelAndView deleteCompany(@RequestParam("id") Integer id){
        Company company = companyService.getCompanyByID(id);
        Users user = company.getUsers();
        for (Job job : company.getJobs()){
            jobService.deleteJob(job);
        }
        companyService.deleteCompany(company);
        userService.deleteUsers(user);
        return viewCompany();
    }
    
    //Change the status of a student to Disabled from Enabled and vice-versace
    @RequestMapping(value="/adminChangeStatusStudent", method= RequestMethod.GET)
    public ModelAndView changeStudent(@RequestParam("id") Integer id){
        Student student = studentService.getStudentByID(id);
        Users user = student.getUsers();
        if (user.isEnabled()) {
            user.setEnabled(false);
        }
        else {
            user.setEnabled(true);
        }
        userService.saveUsers(user);
        return viewStudent();
    }
    
    //Change the status of a company to Disabled from Enabled and vice-versace
    @RequestMapping(value="/adminChangeStatusCompany", method= RequestMethod.GET)
    public ModelAndView changeCompany(@RequestParam("id") Integer id){
        Company company = companyService.getCompanyByID(id);
        Users user = company.getUsers();
        if (user.isEnabled()) {
            user.setEnabled(false);
        }
        else {
            user.setEnabled(true);
        }
        userService.saveUsers(user);
        return viewCompany();
    }
    
    //intended to make the change password form, but time runs out and the debugging fails, so just leave it there for now
    @RequestMapping(value="adminEdit", method = RequestMethod.GET)
    public ModelAndView editAdmin(){        
        throw new UnsupportedOperationException("not support yet");
    }
    
    @RequestMapping(value="adminEdit", method = RequestMethod.POST)
    public ModelAndView saveAdmin(Users newAdmin, BindingResult result){        
        throw new UnsupportedOperationException("not support yet");
    }
}
