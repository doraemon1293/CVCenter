package cvcenter.entity;
// Generated 15-Dec-2012 09:42:30 by Hibernate Tools 3.2.1.GA



/**
 * UserRoles generated by hbm2java
 * This is the entity class for the role of each user, mostly used for authentication, generated using reverse engineering
 */
public class UserRoles  implements java.io.Serializable {

    //Information about the role associated with a user
     private int userRoleId;
     //the user
     private Users users;
     //the authority level, three values only ROLE_STUDENT, ROLE_ADMIN, ROLE_COMPANY
     private String authority;

     //default constructor
    public UserRoles() {
    }

    //constructor with all not-null attributes
    public UserRoles( Users users, String authority) {
       this.users = users;
       this.authority = authority;
    }
   
    public int getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }
    public String getAuthority() {
        return this.authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }




}


