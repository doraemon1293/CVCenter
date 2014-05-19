/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.controller;

import cvcenter.entity.Company;
import cvcenter.entity.Job;
import cvcenter.entity.Users;
import cvcenter.service.CompanyService;
import cvcenter.service.JobService;
import cvcenter.service.UsersService;
import cvcenter.upload.PropertiesUtil;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Administrator
 */
@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JobService jobService;

    //List all the jobs being offered. No thing special, just passing in all the jobs.
    @RequestMapping(value = "/companyjob", method = RequestMethod.GET)
    public ModelAndView jobList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Company> it = user.getCompanies().iterator();
        Company company = it.next();
        Set<Job> jobs = company.getJobs();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("company", company);
        model.put("joblist", jobs);
        return new ModelAndView("company", model);
    }

    //Get the current session's company and update its details, this is to create the form with the model attached
    @RequestMapping(value = "/companyeditprofile", method = RequestMethod.GET)
    public String EditView(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Company> it = user.getCompanies().iterator();
        Company company = it.next();
        model.addAttribute(company);
        return "companyeditprofile";
    }

    //This is the real form handler for editing company details
    @RequestMapping(value = "/companyeditprofile", method = RequestMethod.POST)
    public String Save(Company company, BindingResult result) {
        //if errors occur, dont save it, return to the form
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return "companyeditprofile";
        }
        //retrieved the object from the database
        Company oldCompany = companyService.getCompanyByID(company.getCompanyId());
        //update values
        oldCompany.setCompanyName(company.getCompanyName());
        oldCompany.setAddress(company.getAddress());
        oldCompany.setEmail(company.getEmail());
        oldCompany.setTel(company.getTel());
        oldCompany.setContactName(company.getContactName());
        //save
        companyService.saveCompany(oldCompany);
        return "redirect:/companyjob.htm";
    }

    //delete a job whose id being fetched in the URL
    @RequestMapping(value = "/companyDeleteJob", method = RequestMethod.GET)
    public ModelAndView deleteJob(@RequestParam("jobId") Integer id) {
        //get the job
        Job job = jobService.getJobById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Company> it = user.getCompanies().iterator();
        Company company = it.next();
        //get the authenticated company in this session
        boolean quit = true;
        //check if this job is actually of this company, avoiding unauthorised delete
        for (Job jb : company.getJobs()){
            if (jb.getJobId() == job.getJobId()){
                quit = false;
                break;
            }
        }
        //not belong to the company, return to company start page
        if (quit) {
            return jobList();
        }
        //belong to the company, delete the job and its description file in the storage
        String path = PropertiesUtil.getProperty("default.file.path");
        path = path + job.getTexturl();
        File file = new File(path);
        file.delete();
        jobService.deleteJob(job);
        return jobList();
    }
}
