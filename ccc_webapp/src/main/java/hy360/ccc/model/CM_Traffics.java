/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author sckou
 */
public class CM_Traffics {
    private String employee_id;
    private String company_id;
    private String merchant_id;
    private String transaction_id;
    
    public CM_Traffics(){
        employee_id = "";
        company_id = "";
        merchant_id = "";
        transaction_id = "";
    }
    
    public CM_Traffics(String transaction, String merchant, String company, String employee){
        this.transaction_id = transaction;
        this.employee_id = employee;
        this.company_id = company;
        this.merchant_id = merchant;
        
        
    }
    
    public void checkFields() throws Exception{
        
        
        if(transaction_id == null || transaction_id.trim().isEmpty()
            || company_id == null || company_id.trim().isEmpty()
            || employee_id == null || company_id.trim().isEmpty()    
            || merchant_id == null || merchant_id.trim().isEmpty()){
            
            throw new Exception("Missing Fields");
        }
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("transaction_id: ").append(transaction_id).append("\n")
                .append("company_id: ").append(company_id).append("\n")
                .append("employee_id: ").append(employee_id).append("\n")
                .append("merchant_id: ").append(merchant_id).append("\n");
       
        return ss.toString();
    }
}
