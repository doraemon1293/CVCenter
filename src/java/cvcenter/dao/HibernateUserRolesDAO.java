/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.UserRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * implementation of UserRolesDAO. Every function is basic and
 * self explained
 */
@Repository("UserRolesDAO")
@Transactional
public class HibernateUserRolesDAO implements UserRolesDAO {

    //the session factory bean being used throughout.
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateUserRolesDAO() {
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUserRoles(UserRoles role) {
        Session session = currentSession();
        session.saveOrUpdate(role);
    }

    @Override
    public void deleteUserRoles(UserRoles role) {
        Session session = currentSession();
        session.delete(role);
    }
}
