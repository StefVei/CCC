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
   
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String amount_due;
    private String account_number;

    
    public User(){
        this.userName = "";
        this.account_number = "";
        this.password = "";
        this.email = "";
        this.address = "";
        this.amount_due = "";
        this.phone = "";
    }
    
    
    public User(String userName,
            String password,
            String email,
            String address,
            String amount_due,
            String account_number){
        
        this.userName = userName;
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
        
        if(userName == null || userName.trim().isEmpty()
            || email == null || email.trim().isEmpty()
            || phone == null || phone.trim().isEmpty() 
            || address == null || address.trim().isEmpty()
                || amount_due == null || amount_due.trim().isEmpty()) {

            throw new Exception("Missing Fields");
        
        }
    }
    
    
    /**
     * @return the UserName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param UserName the UserName to set
     */
    public void setUserName(String UserName) {
        this.userName = UserName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the amount_due
     */
    public String getAmount_due() {
        return amount_due;
    }

    /**
     * @param amount_due the amount_due to set
     */
    public void setAmount_due(String amount_due) {
        this.amount_due = amount_due;
    }

    /**
     * @return the account_number
     */
    public String getAccount_number() {
        return account_number;
    }

    /**
     * @param account_number the account_number to set
     */
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
        ss.append("User Name: ").append(userName).append("\n")
                .append("password: ").append(password).append("\n")
                .append("phone :").append(phone).append("\n")
                .append("email :").append(email).append("\n")
                .append("amount_due: ").append(amount_due).append("\n")
                .append("account_number: " ).append(account_number).append("\n");
        
        return ss.toString();
                
    }
    
}
