/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.entity.Company;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * The service providers for database transactions related to
 * Company entity The functions are just corresponding to the DAO class, because
 * the system's functionality is basic.
 */
public interface CompanyService {

    public Company getCompanyByID(int id);

    public void saveCompany(Company company);

    public void deleteCompany(Company company);

    public List<Company> listCompany();
}
