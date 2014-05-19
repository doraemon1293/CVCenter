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
import cvcenter.service.CvService;
import cvcenter.service.JobService;
import cvcenter.service.UsersService;
import cvcenter.upload.CVFileHolder;
import cvcenter.upload.JobFileHolder;
import cvcenter.upload.PropertiesUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
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

/**
 *
 * @author Giang Tran Hoang
 * 
 * Controller that keep track of the operations related to file download/upload/storage
 */
@Controller
public class UploadController {

    //The beans needed for persistence control
    @Autowired
    private CvService cvService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JobService jobService;

    //Student uploadingthe CV, this will return the form, with the model - CVFile Holder fetched
    @RequestMapping(value = "/studentUpload", method = RequestMethod.GET)
    public String getCvUploadForm(Model model, @RequestParam("id") Integer id) {
        CVFileHolder fileHolder = new CVFileHolder();
        fileHolder.setId(id.toString());
        model.addAttribute("fileHolder", fileHolder);
        return "uploadForm";
    }

    //handling the form, saving the CV in the database and storage
    @RequestMapping(value = "/studentUpload", method = RequestMethod.POST)
    public String createCv(CVFileHolder fileHolder, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return "uploadForm";
        }
        //get the authenticated student
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Student> it = user.getStudents().iterator();
        Student student = it.next();
        //get the file name from the uploaded file
        String fullName = fileHolder.getFile().getOriginalFilename();
        int id = Integer.parseInt(fileHolder.getId());
        //spliting the file name and the extension
        int separator = fullName.lastIndexOf(".");
        String fileName, format;
        if (separator > 0 && separator < fullName.length() - 1) {
            fileName = fullName.substring(0, separator);
            format = fullName.substring(separator + 1);
        } else { //unknow extension (cannot locate the "." character
            fileName = fullName;
            format = "pdf";
        }
        //creating the url to store the file
        String url = "cv"+File.pathSeparator + fileName + "_" + student.getStudentId();
        Cv cv;
        if (id > 0) { //id exists, means updating
            cv = cvService.getCVById(id);
            if (!student.getCvs().contains(cv)) {
                return "redirect:/studentCv.htm";
            }
            //need delete the old file
            String path = PropertiesUtil.getProperty("default.file.path");
            path = path + cv.getFileurl();
            File file = new File(path);
            file.delete();
        } else { //id = 0, new file
            cv = new Cv();
        }
        //save the CV information
        cv.setFilename(fileName);
        cv.setFormat(format);
        cv.setJobCat(fileHolder.getJobCat());
        cv.setFileurl(url);
        cv.setStudent(student);

        //save the file into storage

        File file = new File(PropertiesUtil.getProperty("default.file.path") + url);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fw = new FileOutputStream(file);
        fw.write(fileHolder.getFile().getBytes());
        fw.flush();
        fw.close();

        //save the CV into databse, do this after saving file to avoid error above create inconsistence data in database
        cvService.saveCV(cv);

        return "redirect:/studentCv.htm";
    }

    //download the CV with the id fetched in.
    @RequestMapping(value = "/downloadCV", method = RequestMethod.GET)
    public void getCvFile(@RequestParam(value = "cvId", required = true) String cvId,
            HttpServletResponse response) {
        try {
            //get the CV
            int cvID = Integer.parseInt(cvId);
            Cv cv = cvService.getCVById(cvID);

            //setting up the payload
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + cv.getFilename() + "." + cv.getFormat());
            String path = PropertiesUtil.getProperty("default.file.path");
            path = path + cv.getFileurl();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            //stream the content
            FileInputStream fileIn = new FileInputStream(file);

            ServletOutputStream out = response.getOutputStream();

            byte[] outputByte = new byte[4096];
            //copy binary contect to output stream
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    //below are the same functions, but for the job descriptions instead of the CVs
    

    @RequestMapping(value = "/companyUpload", method = RequestMethod.GET)
    public String getJobUploadForm(@RequestParam("id") Integer id, Model model) {
        JobFileHolder fileHolder = new JobFileHolder();
        fileHolder.setId(id.toString());
        model.addAttribute("fileHolder", fileHolder);
        return "editJob";
    }

    @RequestMapping(value = "/companyUpload", method = RequestMethod.POST)
    public String create(JobFileHolder fileHolder, BindingResult result) throws IOException, ParseException {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return "editJob";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = usersService.getUsersByUsername(username);
        Iterator<Company> it = user.getCompanies().iterator();
        Company company = it.next();
        String fullName = fileHolder.getFile().getOriginalFilename();
        int id = Integer.parseInt(fileHolder.getId());

        String url = "job"+File.pathSeparator + company.getCompanyId() + "_" + fullName;
        Job job;
        if (id > 0) {
            job = jobService.getJobById(id);
            if (!company.getJobs().contains(job)) {
                return "redirect:/companyjob.htm";
            }
            String path = PropertiesUtil.getProperty("default.file.path");
            path = path + job.getTexturl();
            File file = new File(path);
            file.delete();
        } else {
            job = new Job();
        }
        job.setCompany(company);
        job.setTitle(fileHolder.getTitle());
        job.setJobCat(fileHolder.getJobCat());
        job.setTexturl(url);
        Date date = new SimpleDateFormat("dd-mm-yyyy").parse(fileHolder.getDate());
        job.setDeadline(date);


        File file = new File(PropertiesUtil.getProperty("default.file.path") + url);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fw = new FileOutputStream(file);
        fw.write(fileHolder.getFile().getBytes());
        fw.flush();
        fw.close();

        jobService.saveJob(job);

        return "redirect:/companyjob.htm";
    }

    @RequestMapping(value = "/downloadJob", method = RequestMethod.GET)
    public void getFile(@RequestParam(value = "jobId", required = true) String jobId,
            HttpServletResponse response) {
        try {
            int jobID = Integer.parseInt(jobId);
            Job job = jobService.getJobById(jobID);
            int index = job.getTexturl().lastIndexOf("_");
            String fileName = job.getTexturl().substring(index + 1);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + fileName);
            String path = PropertiesUtil.getProperty("default.file.path");
            path = path + job.getTexturl();
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileInputStream fileIn = new FileInputStream(file);

            ServletOutputStream out = response.getOutputStream();

            byte[] outputByte = new byte[4096];
            //copy binary contect to output stream
            while (fileIn.read(outputByte, 0, 4096) != -1) {
                out.write(outputByte, 0, 4096);
            }
            fileIn.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }

    }
}
