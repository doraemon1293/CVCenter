/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Job;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * implementation of JobDAO. Every function is basic and self
 * explained
 */
@Repository("JobDAO")
@Transactional
public class HibernateJobDAO implements JobDAO {

    //the session factory bean being used throughout.
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateJobDAO() {
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Job getJobById(int id) {
        Session session = currentSession();
        Job job = (Job) session.get(Job.class, id);
        return job;
    }

    @Override
    public void saveJob(Job job) {
        Session session = currentSession();
        session.saveOrUpdate(job);
    }

    @Override
    public void deleteJob(Job job) {
        Session session = currentSession();
        session.delete(job);
    }

    @Override
    public List<Job> listJobs() {
        List<Job> studentList = null;
        Query query = currentSession().createQuery("from Job");
        studentList = (List<Job>) query.list();
        return studentList;
    }
}
