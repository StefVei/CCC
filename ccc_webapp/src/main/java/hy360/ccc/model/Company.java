/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author sckou
 */
public class Company extends User{
    
    private String name;
    private String establishment_date;
    private String email;
    private String address;
    private String phone;
    private double amount_due;
    private double credit_limit;
    private double credit_balance;
    private String account_due_date;
    
    
    public Company(){
        name = "";
        establishment_date = "";
        email="";
        address="";
        phone="";
        amount_due = 0;
        credit_limit = 0;
        credit_balance = 0;
        account_due_date = "";
    }
    
    public Company(String name, String establishment_date,
            String email, String address, String phone, double amount_due,
            double credit_limit, double credit_balance, String account_due_date) {
        this.name = name;
        this.establishment_date = establishment_date;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.amount_due=amount_due;
        this.credit_limit = credit_limit;
        this.credit_balance = credit_balance;
        this.account_due_date = account_due_date;
    }
    
    public Company(String userid, String username, String password, String account_number,
            String name, String establishment_date,
            String email, String address, String phone, double amount_due,
            double credit_limit, double credit_balance, String account_due_date) {
        
        super(userid, username, password, account_number);
        
        this.name = name;
        this.establishment_date = establishment_date;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.amount_due=amount_due;
        this.credit_limit = credit_limit;
        this.credit_balance = credit_balance;
        this.account_due_date = account_due_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEstablishment_date() {
        return establishment_date;
    }

    public void setEstablishment_date(String establishment_date) {
        this.establishment_date = establishment_date;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public double getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(double credit_limit) {
        this.credit_limit = credit_limit;
    }

    public double getCredit_balance() {
        return credit_balance;
    }

    public void setCredit_balance(double credit_balance) {
        this.credit_balance = credit_balance;
    }

    public String getAccount_due_date() {
        return account_due_date;
    }

    public void setAccount_due_date(String account_due_date) {
        this.account_due_date = account_due_date;
    }
    
    
    
    /**
     * Return the string representation of the object in rows
     *
     * 
    */
    
    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        ss.append("name= ").append(name).append("\n")
                .append("established_date= ").append(establishment_date).append("\n")
                .append("email: ").append(email).append("\n")
                .append("address: ").append(address).append("\n")
                .append("phone: ").append(phone).append("\n")
                .append("amount_due: ").append(amount_due).append("\n")
                .append("credit_limit: ").append(credit_limit).append("\n")
                .append("credit_balance: ").append(credit_balance).append("\n")
                .append("account_due_date:").append(account_due_date).append("\n");
        return super.toString() + ss.toString();
    }

    
    
    
   
}

