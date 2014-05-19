/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.UserRoles;

/**
 *
 * @author Giang Tran Hoang 
 * DAO class for UserRoles entity
 */
public interface UserRolesDAO {

    //only need save and delete. Users when loaded will have user role in it
    public void saveUserRoles(UserRoles role);

    public void deleteUserRoles(UserRoles role);
}
