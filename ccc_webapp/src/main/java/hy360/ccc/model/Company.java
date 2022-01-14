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
    
    private String name;
    private String establishment_date;
    
    public Company(){
        name = "";
        establishment_date = "";
    }
    
    public Company(String name, String establishment_date)
    {
        this.name = name;
        this.establishment_date = establishment_date;
    }
    
    public Company(String name, String establishment_date, String credit_limit,
            String credit_balance, String account_due_date) {
        
        super(credit_limit, credit_balance, account_due_date);
        
        this.name = name;
        this.establishment_date = establishment_date;
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
    
    /**
     * Return the string representation of the object in rows
     *
     * 
    */
    
    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        ss.append("name= ").append(name).append("\n")
                .append("established_date= ").append(establishment_date).append("\n");
        return super.toString() + ss.toString();
    }

    
    
    
   
}

