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
   
    private String customer_id;
    private String credit_limit;
    private String account_due_date;
    private String credit_balance;
    
    public Customer(){
        customer_id = "";
        credit_limit = "";
        account_due_date = "";
        credit_balance = "";
    }
    
    public Customer(String userName,
            String password,
            String email,
            String phone,
            String address,
            String amount_due,
            String account_number,
            String customer_id,
            String credit_limit,
            String account_due_date,
            String credit_balance){
        
        super(userName, password, email, phone, address, amount_due, account_number);
        
        this.credit_balance = credit_balance;
        this.credit_limit = credit_limit;
        this.customer_id = customer_id;
        this.account_due_date = account_due_date;
    }

    
    public void checkFields() throws Exception{
        
        super.checkFields();
        
        if(credit_balance == null || credit_balance.trim().isEmpty()
            || credit_limit == null || credit_limit.trim().isEmpty()
            || customer_id == null || customer_id.trim().isEmpty()
            || account_due_date == null || account_due_date.trim().isEmpty()){
            
            throw new Exception("Missing Fields");
        }
    }
    
    
    /**
     * @return the customer_id
     */
    public String getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the credit_limit
     */
    public String getCredit_limit() {
        return credit_limit;
    }

    /**
     * @param credit_limit the credit_limit to set
     */
    public void setCredit_limit(String credit_limit) {
        this.credit_limit = credit_limit;
    }

    /**
     * @return the account_due_date
     */
    public String getAccount_due_date() {
        return account_due_date;
    }

    /**
     * @param account_due_date the account_due_date to set
     */
    public void setAccount_due_date(String account_due_date) {
        this.account_due_date = account_due_date;
    }

    /**
     * @return the credit_balance
     */
    public String getCredit_balance() {
        return credit_balance;
    }

    /**
     * @param credit_balance the credit_balance to set
     */
    public void setCredit_balance(String credit_balance) {
        this.credit_balance = credit_balance;
    }
    
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("customer_id: ").append(customer_id).append("\n")
                .append("credit_limit: ").append(credit_limit).append("\n")
                .append("account_due_date:").append(account_due_date).append("\n")
                .append("credit_balance: ").append(credit_balance).append("\n");
       
        return super.toString() + ss.toString();
    }
    
}
