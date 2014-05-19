/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.entity.Job;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * The service providers for database transactions related to
 * Job entity The functions are just corresponding to the DAO class, because the
 * system's functionality is basic.
 */
public interface JobService {

    public Job getJobById(int id);

    public void saveJob(Job job);

    public void deleteJob(Job job);

    public List<Job> listJobs();
}
