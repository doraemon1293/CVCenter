/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.dao.CvDAO;
import cvcenter.dao.JobDAO;
import cvcenter.entity.Cv;
import cvcenter.entity.Job;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * Implementation of the Service class for Job Entity Very
 * simple and make user of the corresponding JobDAO class
 */
@Service("JobService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImplJobService implements JobService {

    @Autowired
    private JobDAO jobdao;
    @Autowired
    private CvDAO cvdao;

    public ImplJobService() {
    }

    @Override
    public Job getJobById(int id) {
        return jobdao.getJobById(id);
    }

    @Override
    public void saveJob(Job job) {
        jobdao.saveJob(job);
    }

    //This method is a bit complicated. Because the many-to-many relationship of Job and CV has CV as the owner
    //I need to remove the association between them before deleting, and that must be done from CV's end
    @Override
    public void deleteJob(Job job) {
        //if the job has associated CVs
        if (!job.getCvs().isEmpty()) {
            Set<Cv> set = job.getCvs();
            Iterator<Cv> it = set.iterator();
            //loop through all of its CVs
            while (it.hasNext()) {
                Cv tempCv = it.next();
                Set<Job> jobSet = tempCv.getJobs();
                Iterator<Job> itJob = jobSet.iterator();
                //trying to locate the job in the list of job of the CV
                while (itJob.hasNext()) {
                    Job tempJob = itJob.next();
                    //found it
                    if (tempJob.getJobId() == job.getJobId()) {
                        //remove the association and break out
                        itJob.remove();
                        cvdao.saveCV(tempCv);
                        break;
                    }
                }
            }
        }
        //no association left, delete it now
        jobdao.deleteJob(job);
    }

    @Override
    public List<Job> listJobs() {
        return jobdao.listJobs();
    }
}
