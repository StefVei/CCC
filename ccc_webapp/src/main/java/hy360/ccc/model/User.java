 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

import java.io.Serializable;

/**
 *
 * @author panagiotisk
 */
public class User implements Serializable{
   
    private String userid;
    private String username;
    private String password;
    private String account_number;
    private String user_type;
    private boolean deleted;

    
    public User() {
        this.deleted = false;
        this.userid = "";
        this.username = "";
        this.password = "";
        this.account_number = "";
    }
    
    public User(
            String userid,
            String username,
            String password,
            String account_number) {
        this.deleted = false;
        this.userid=userid;
        this.username = username;
        this.password = password;
        this.account_number = account_number;  
    }
    
    /** 
     * Checks that all the necessary fields are set
     * 
     * @throws Exception
     */
    public void checkFields() throws Exception{
        
        if(getUsername() == null || getUsername().trim().isEmpty()
            || password == null || password.trim().isEmpty()
            || account_number == null || account_number.trim().isEmpty() ) {

            throw new Exception("Missing Fields");
        
        }
    }

    public String getUser_id() {
        return getUserid();
    }

    public void setUser_id(String userid) {
        this.setUserid(userid);
    }

    public String getUserName() {
        return getUsername();
    }

    public void setUserName(String username) {
        this.setUsername(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }
 
    /**
     * Return the string representation of the object in rows
     *
     * 
    */
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("userid: ").append(getUserid()).append("\n")
                .append("username: ").append(getUsername()).append("\n")
                .append("password: ").append(password).append("\n")
                .append("account_number: " ).append(account_number).append("\n");
        
        return ss.toString();
                
    }

    /**
     * @return the user_type
     */
    public String getUser_type() {
        return user_type;
    }

    /**
     * @param user_type the user_type to set
     */
    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    
   
    
}
