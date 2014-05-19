/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Cv;
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
 * implementation of CvDAO. Every function is basic and self
 * explained
 */
@Repository("CvDAO")
@Transactional
public class HibernateCvDAO implements CvDAO {

    //the session factory bean being used throughout.
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateCvDAO() {
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Cv getCVById(int id) {
        Session session = currentSession();
        Cv cv = (Cv) session.get(Cv.class, id);
        return cv;
    }

    @Override
    public void saveCV(Cv cv) {
        Session session = currentSession();
        session.saveOrUpdate(cv);
    }

    @Override
    public void deleteCV(Cv cv) {
        Session session = currentSession();
        session.delete(cv);
    }
}
