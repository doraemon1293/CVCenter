/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Users;
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
 * implementation of UserDAO. Every function is basic and self
 * explained
 */
@Repository("UsersDAO")
@Transactional
public class HibernateUsersDAO implements UsersDAO {

    //the session factory bean being used throughout.
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateUsersDAO() {
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Users getUsersByUsername(String username) {
        Users user = null;
        Query query = currentSession().createQuery("from Users where username = :name");
        query.setParameter("name", username);
        user = (Users) query.uniqueResult();
        return user;
    }

    @Override
    public void saveUsers(Users user) {
        Session session = currentSession();
        session.saveOrUpdate(user);
    }

    @Override
    public void deleteUsers(Users user) {
        Session session = currentSession();
        session.delete(user);
    }

    @Override
    public List<Users> listUsers() {
        List<Users> studentList = null;
        Query query = currentSession().createQuery("from Users");
        studentList = (List<Users>) query.list();
        return studentList;

    }
}
