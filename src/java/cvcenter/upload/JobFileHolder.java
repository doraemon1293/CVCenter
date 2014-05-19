/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.upload;

import java.util.Date;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Giang Tran Hoang
 * 
 * Simple model for the uploading job description
 */
public class JobFileHolder {
    
    private String id;
    private String title;
    private String jobCat;
    private String date;
    private CommonsMultipartFile file;
    
    public JobFileHolder(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobCat() {
        return jobCat;
    }

    public void setJobCat(String jobCat) {
        this.jobCat = jobCat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
    
    
    
}
