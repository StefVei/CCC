/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class CustomerPhone {
    
    
    private String customer_id;
    private String phone;
    
    public CustomerPhone(){
        customer_id = "";
        phone = "";
    }
    
    public CustomerPhone(String id, String phone){
        customer_id = id;
        this.phone = phone;
        
    }
    
    public void checkFields() throws Exception{
        
        
        if(getCustomer_id() == null || getCustomer_id().trim().isEmpty()
            || getPhone() == null || getPhone().trim().isEmpty()){
            
            throw new Exception("Missing Fields");
        }
    }
    
    
    
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("customer_id: ").append(getCustomer_id()).append("\n")
                .append("phone: ").append(getPhone()).append("\n");
       
        return ss.toString();
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

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
