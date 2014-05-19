/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Job;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * DAO class for Job entity
 */
public interface JobDAO {

    //4 basic functions
    public Job getJobById(int id);

    public void saveJob(Job job);

    public void deleteJob(Job job);

    public List<Job> listJobs();
}
