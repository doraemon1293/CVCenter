/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvcenter.service;

import cvcenter.dao.UserRolesDAO;
import cvcenter.dao.UsersDAO;
import cvcenter.entity.UserRoles;
import cvcenter.entity.Users;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Giang Tran Hoang 
 * Implementation of the Service class for Users and UserRoles
 * Entity Very simple and make user of the corresponding DAO classes
 */
@Service("UsersService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ImplUsersService implements UsersService {

    @Autowired
    private UsersDAO usersdao;
    @Autowired
    private UserRolesDAO userRolesdao;

    public ImplUsersService() {
    }

    @Override
    public Users getUsersByUsername(String username) {
        return usersdao.getUsersByUsername(username);
    }

    //updating
    @Override
    public void saveUsers(Users user) {
        usersdao.saveUsers(user);
    }

    //delete roles before delete user
    @Override
    public void deleteUsers(Users user) {
        Iterator<UserRoles> it = user.getUserRoleses().iterator();
        userRolesdao.deleteUserRoles(it.next());
        usersdao.deleteUsers(user);
    }

    @Override
    public List<Users> listUsers() {
        return usersdao.listUsers();
    }

    //create new user, then create the role come with it
    @Override
    public void createUsers(Users user, String userRoles) {
        UserRoles role = new UserRoles(user, userRoles);
        user.getUserRoleses().add(role);
        usersdao.saveUsers(user);
        userRolesdao.saveUserRoles(role);
    }
}
