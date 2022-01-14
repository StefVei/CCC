/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Citizen extends Customer{
    private String amka;
    private String vat;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String gender;
    
    
    
    public Citizen(){
        amka = "";
        vat = "";
        first_name = "";
        last_name = "";
        birth_date = "";
        gender = "O";
    }
    
    public Citizen(String amka, String vat, String first_name, 
            String last_name, String birth_date, String gender){
        
        this.amka = amka;
        this.vat = vat;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.gender = gender;

    }
    
    public Citizen(String credit_limit,String credit_balance,
            String account_due_date, String amka, String vat, String first_name, 
            String last_name, String birth_date, String gender){
        
        super(credit_limit, credit_balance, account_due_date);
        
        this.amka = amka;
        this.vat = vat;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.gender = gender;
    }

    public String getAmka() {
        return amka;
    }

    public void setAmka(String amka) {
        this.amka = amka;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("amka:").append(amka).append("\n")
                .append("vat: ").append(vat).append("\n")
                .append("first_name: ").append(first_name).append("\n")
                .append("last_name: ").append(last_name).append("\n")
                .append("birth_date: ").append(birth_date).append("\n")
                .append("gender: ").append(gender).append("\n");

       
        return super.toString() + ss.toString();

    }

}
