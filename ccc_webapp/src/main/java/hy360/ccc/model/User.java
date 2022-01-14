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
    private String phone;
    private String email;
    private String address;
    private String amount_due;
    private String account_number;

    
    public User(){
        this.userid = "";
        this.username = "";
        this.password = "";
        this.phone = "";
        this.email = "";
        this.address = "";
        this.amount_due = "";
        this.account_number = "";
    }
    
    public User(
            String username,
            String password,
            String phone,
            String email,
            String address,
            String amount_due,
            String account_number){
        
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.amount_due = amount_due;
        this.account_number = account_number;  
    }
    
    /** 
     * Checks that all the necessary fields are set
     * 
     * @throws Exception
     */
    public void checkFields() throws Exception{
        
        if(username == null || username.trim().isEmpty()
            || password == null || password.trim().isEmpty()
            || phone == null || phone.trim().isEmpty()
            || email == null || email.trim().isEmpty()
            || address == null || address.trim().isEmpty()
            || amount_due == null || amount_due.trim().isEmpty()
            || account_number == null || account_number.trim().isEmpty() ) {

            throw new Exception("Missing Fields");
        
        }
    }

    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(String amount_due) {
        this.amount_due = amount_due;
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
        ss.append("userid: ").append(userid).append("\n")
                .append("username: ").append(username).append("\n")
                .append("password: ").append(password).append("\n")
                .append("phone :").append(phone).append("\n")
                .append("email :").append(email).append("\n")
                .append("address :").append(address).append("\n")
                .append("amount_due: ").append(amount_due).append("\n")
                .append("account_number: " ).append(account_number).append("\n");
        
        return ss.toString();
                
    }
    
   
    
}
