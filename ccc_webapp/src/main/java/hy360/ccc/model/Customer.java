/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Customer extends User{
   
    private String credit_limit;
    private String credit_balance;
    private String account_due_date;
    
    public Customer(){
        credit_limit = "";
        credit_balance = "";
        account_due_date = "";
    }
    
    public Customer(String credit_limit,
            String credit_balance,
            String account_due_date){
        
        this.credit_limit = credit_limit;
        this.credit_balance = credit_balance;
        this.account_due_date = account_due_date;
   
    }

    
    public Customer(String userid,
            String username,
            String password,
            String phone,
            String email,
            String address,
            String amount_due,
            String account_number,
            String credit_limit,
            String credit_balance,
            String account_due_date){
        
        super(userid, username, password, phone, email, address, amount_due, account_number);
        
        this.credit_limit = credit_limit;
        this.credit_balance = credit_balance;
        this.account_due_date = account_due_date;
    }

    
    public void checkFields() throws Exception{
        
        super.checkFields();
        
        if (credit_limit == null || credit_limit.trim().isEmpty()
                || credit_balance == null || credit_balance.trim().isEmpty()
                || account_due_date == null || account_due_date.trim().isEmpty()) {
            
            throw new Exception("Missing Fields");
        }
    }

    public String getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(String credit_limit) {
        this.credit_limit = credit_limit;
    }

    public String getCredit_balance() {
        return credit_balance;
    }

    public void setCredit_balance(String credit_balance) {
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
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("credit_limit: ").append(credit_limit).append("\n")
                .append("credit_balance: ").append(credit_balance).append("\n")
                .append("account_due_date:").append(account_due_date).append("\n");
        return super.toString() + ss.toString();
    }
    
}
