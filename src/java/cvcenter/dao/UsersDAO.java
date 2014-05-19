/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.dao;

import cvcenter.entity.Users;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * DAO class for Users entity
 */
public interface UsersDAO {

    //4 basic functions
    public Users getUsersByUsername(String username);

    public void saveUsers(Users user);

    public void deleteUsers(Users user);

    public List<Users> listUsers();
}
