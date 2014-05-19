package cvcenter.entity;
// Generated 15-Dec-2012 09:42:30 by Hibernate Tools 3.2.1.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Job generated by hbm2java This is the entity class for a job offered by a
 * company, generated using reverse engineering
 */
public class Job implements java.io.Serializable {

    //information about a job 
    private int jobId;
    private String jobCat;
    private String title;
    private Date deadline;
    private String texturl;
    //The company offering the job
    private Company company;
    //The list of CVs being submitted to apply for the job
    private Set<Cv> cvs = new HashSet<Cv>(0);

    //default constructor
    public Job() {
    }

    //constructor that requires all not-null attributes
    public Job(Company company, String jobCat, String title) {
        this.company = company;
        this.jobCat = jobCat;
        this.title = title;
    }

    //constructor that requires all attributes
    public Job(Company company, String jobCat, String title, Date deadline, String texturl, Set<Cv> cvs) {
        this.company = company;
        this.jobCat = jobCat;
        this.title = title;
        this.deadline = deadline;
        this.texturl = texturl;
        this.cvs = cvs;
    }

    //getters and setters
    
    public int getJobId() {
        return this.jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getJobCat() {
        return this.jobCat;
    }

    public void setJobCat(String jobCat) {
        this.jobCat = jobCat;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getTexturl() {
        return this.texturl;
    }

    public void setTexturl(String texturl) {
        this.texturl = texturl;
    }

    public Set<Cv> getCvs() {
        return cvs;
    }

    public void setCvs(Set<Cv> cvs) {
        this.cvs = cvs;
    }
}