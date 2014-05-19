/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Company;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang implementation of CompanyDAO. Every function is basic and
 * self explained
 */
@Repository("CompanyDAO")
@Transactional
public class HibernateCompanyDAO implements CompanyDAO {

    //the session factory bean being used throughout. 
    @Autowired
    private SessionFactory sessionFactory;

    public HibernateCompanyDAO() {
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Company getCompanyByID(int id) {
        Session session = currentSession();
        Company company = (Company) session.get(Company.class, id);
        return company;
    }

    @Override
    public void saveCompany(Company company) {
        Session session = currentSession();
        session.saveOrUpdate(company);
    }

    @Override
    public void deleteCompany(Company company) {
        Session session = currentSession();
        session.delete(company);
    }

    @Override
    public List<Company> listCompany() {
        List<Company> studentList = null;
        Query query = currentSession().createQuery("from Company");
        studentList = (List<Company>) query.list();
        return studentList;
    }
}
