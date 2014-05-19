/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.entity.Users;
import java.util.List;

/**
 *
 * @author Giang Tran Hoang 
 * The service providers for database transactions related to
 * Users and UserRoles entity UserRoles does not need Service class, because all
 * of its service will be served at the same time with those for a user
 */
public interface UsersService {

    public Users getUsersByUsername(String username);

    public void createUsers(Users user, String userRoles);

    public void saveUsers(Users user);

    public void deleteUsers(Users user);

    public List<Users> listUsers();
}
