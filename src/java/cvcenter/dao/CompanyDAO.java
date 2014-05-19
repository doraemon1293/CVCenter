/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Company;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * DAO class for Company entity
 */
public interface CompanyDAO {

    //only 4 basic functions
    public Company getCompanyByID(int id);

    public void saveCompany(Company company);

    public void deleteCompany(Company company);

    public List<Company> listCompany();
}
