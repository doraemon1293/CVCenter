package cvcenter.entity;
// Generated 15-Dec-2012 09:42:30 by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Company generated by hbm2java This is the entity class for the company,
 * generated using reverse engineering
 */
public class Company implements java.io.Serializable {

    //These are the information about the company
    private int companyId;
    private String companyName;
    private String address;
    private String email;
    private String tel;
    private String contactName;
    //The user associated with the company account, for authentication purpose
    private Users users;
    //The jobs being offered by the company
    private Set<Job> jobs = new HashSet<Job>(0);

    //default constructor
    public Company() {
    }

    //constructor that requires all not-null attributes
    public Company(Users users, String companyName, String address, String email, String contactName) {
        this.users = users;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.contactName = contactName;
    }
    //constructor that requires all attributes

    public Company(Users users, String companyName, String address, String email, String tel, String contactName, Set<Job> jobs) {
        this.users = users;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.tel = tel;
        this.contactName = contactName;
        this.jobs = jobs;
    }

    //getters and setters
    public int getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public Set<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
}