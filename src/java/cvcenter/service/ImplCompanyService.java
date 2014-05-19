/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.dao.CompanyDAO;
import cvcenter.entity.Company;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * Implementation of the Service class for Company Entity Very
 * simple and make user of the corresponding CompanyDAO class
 */
@Service("CompanyService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImplCompanyService implements CompanyService {

    @Autowired
    private CompanyDAO companydao;

    public ImplCompanyService() {
    }

    @Override
    public Company getCompanyByID(int id) {
        return companydao.getCompanyByID(id);
    }

    @Override
    public void saveCompany(Company company) {
        companydao.saveCompany(company);
    }

    @Override
    public void deleteCompany(Company company) {
        companydao.deleteCompany(company);
    }

    @Override
    public List<Company> listCompany() {
        return companydao.listCompany();
    }
}
