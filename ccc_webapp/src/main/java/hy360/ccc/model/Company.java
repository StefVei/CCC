/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author sckou
 */
public class Company extends Customer{
    private String company_id;
    private String name;
    private String establishment_date;
    
    public Company(){
        company_id = "";
        name = "";
        establishment_date = "";
    }
    
    public Company(String company_id, 
            String name, String establishment_date)
    {
        this.company_id = company_id;
        this.name = name;
        this.establishment_date = establishment_date;
    }
    
    public Company(String CustomerId, String account_due, 
            String company_id, String name, String establishment_date, String credit_limit, String credit_balance){
        
        super(CustomerId, credit_limit, account_due, credit_balance);
        
        this.company_id = company_id;
        this.name = name;
        this.establishment_date = establishment_date;
    }

    public String getCompany_id() {
        return company_id;
    }

    public String getName() {
        return name;
    }

    public String getEstablishment_date() {
        return establishment_date;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEstablishment_date(String establishment_date) {
        this.establishment_date = establishment_date;
    }

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        ss.append("company_id= ").append(company_id).append("\n")
                .append("name= ").append(name).append("\n")
                .append("established_date= ").append(establishment_date).append("\n");
        return super.toString() + ss.toString();
    }
    
    
    
    
   
}

