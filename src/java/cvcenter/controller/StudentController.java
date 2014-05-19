/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.controller;

import cvcenter.entity.Cv;
import cvcenter.entity.Job;
import cvcenter.entity.Student;
import cvcenter.entity.Users;
import cvcenter.service.CvService;
import cvcenter.service.JobService;
import cvcenter.service.StudentService;
import cvcenter.service.UsersService;
import cvcenter.upload.PropertiesUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Giang Tran Hoang
 * Controller class handling the functions performed by a student user
 * 
 */
@Controller
public class StudentController {

    //required beans for database operations
    @Autowired
    private JobService jobService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CvService cvService;
    @Autowired
    private UsersService usersService;

    //List the CVs, nothing special
    @RequestMapping(value = "/studentCv", method = RequestMethod.GET)
    public ModelAndView cvList() {
        //get the current authenticated student
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();
        //return the cv list
        Set<Cv> cvs = student.getCvs();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("student", student);
        model.put("CVList", cvs);

        return new ModelAndView("studentCV", model);
    }

    //Return the view for the list of jobs not being applied
    @RequestMapping(value = "/studentBrowsejob", method = RequestMethod.GET)
    public ModelAndView jobList() {
        //get the authenticated student
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();
        //list all the job
        List<Job> jobs = jobService.listJobs();
        for (Cv cv : student.getCvs()){
            //remove the job already associated with this student's CVs
            jobs.removeAll(cv.getJobs());
        }
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("student", student);
        model.put("jobList", jobs);
        return new ModelAndView("studentJob", model);
    }

   //Delete a CV, similar to the delete job of company
    @RequestMapping(value = "/studentDeleteCV", method = RequestMethod.GET)
    public ModelAndView delelteCV(@RequestParam("cvId") Integer id) {
        Cv cv = cvService.getCVById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();
        boolean quit = true;
        //check if the CV really belongs to the student.
        for (Cv tempCv : student.getCvs()){
            if (tempCv.getCvId() == cv.getCvId()){
                quit = false;
                break;
            }
        }
        //return to start page if not
        if (quit) {
            return cvList();
        }
        //delete the CV as well as the file in storage.
        String path = PropertiesUtil.getProperty("default.file.path");
        path = path + cv.getFileurl();
        File file = new File(path);
        file.delete();
        cvService.deleteCV(cv);
        return cvList();
    }
    
    //Returning the form for editing student profile
    @RequestMapping(value = "/studentProfile", method = RequestMethod.GET)
    public String createForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();       
        //fetching the authenticated student to the form
        model.addAttribute(student);
        return "studenteditprofile";
    }
    
    //saving it
    @RequestMapping(value = "/studentProfile", method = RequestMethod.POST)
    public String saveStudent(Student student, BindingResult result) {
        //return to the form is errors occur
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return "studenteditprofile";
        }
        //get student fromt database
        Student oldStudent = studentService.getStudentByID(student.getStudentId());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setFirstName(student.getFirstName());
        oldStudent.setInitial(student.getInitial());
        oldStudent.setLastName(student.getLastName());
        oldStudent.setTel(student.getTel());
        //udpdate and save values
        studentService.saveStudent(oldStudent);
        return "redirect:/studentCv.htm";
    }
    
    //this is when student see a job and press apply
    @RequestMapping(value = "/studentApplyJob", method = RequestMethod.GET)
    public String applyJob(@RequestParam("jobId") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();   
        //get authenticated student
        if (student.getCvs().isEmpty()) {
            //no CV uploaded? upload new one plz
            return "redirect:/studentUpload.htm?id=0";
        }else if (student.getCvs().size()==1){
            //only has one CV? use it to apply
            Iterator<Cv> itCv = student.getCvs().iterator();
            Cv cv = itCv.next();
            Job job = jobService.getJobById(id);
            cv.getJobs().add(job);
            cvService.saveCV(cv);
            return "redirect:/studentCv.htm";
        }else{
            //has more than one? choose which one. Passing the jobId with url
            return "redirect:/studentCvChoice.htm?jobId="+id;
        }
    }
    
    //return the form to choose from CVs.
    @RequestMapping(value = "/studentCvChoice", method = RequestMethod.GET)
    public ModelAndView matchCvJob(@RequestParam("jobId") Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();  
        //get the CVs ans fetch them in
        Set<Cv> cvs = student.getCvs();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("jobId", id);
        model.put("CVList", cvs);
        return new ModelAndView("studentCvChoice", model);
    }
    

    //get the id of the CVs to applied to the job with that id
    @RequestMapping(value = "/studentCvAndJob", method = RequestMethod.GET)
    public ModelAndView saveCvJob(@RequestParam("cvId")Integer cvId, @RequestParam("jobId")Integer jobId) {
        //get the entities and save them. The Cascading will save the relationship
        Cv cv = cvService.getCVById(cvId);
        Job job = jobService.getJobById(jobId);
        cv.getJobs().add(job);
        cvService.saveCV(cv);
        
        return cvList();
    }
}
