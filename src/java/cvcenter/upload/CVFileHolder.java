/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.upload;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author Giang Tran Hoang
 * The simple wrapper for the uploading CV
 */
public class CVFileHolder {
    
    private String id;
    private String jobCat;
    private CommonsMultipartFile file;
    
    public CVFileHolder(){}

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }

    public String getJobCat() {
        return jobCat;
    }

    public void setJobCat(String jobCat) {
        this.jobCat = jobCat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    
}
