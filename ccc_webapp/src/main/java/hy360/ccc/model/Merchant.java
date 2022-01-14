/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Merchant extends User {
    
    private String first_name;
    private String last_name;
    private String birth_date;
    private String gender;
    private String supply;
    private String gain;
    private String purchases_total;

    
    public Merchant(){
        first_name = "";
        last_name = "";
        birth_date = "";
        gender = "O";
        supply = "";
        gain = "";
        purchases_total = "";
    }
    
    public Merchant(String first_name, 
            String last_name, String birth_date, String gender,
            String supply, String gain, String purchases_total ) {
        
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.supply = supply;
        this.gain = gain;
        this.purchases_total = purchases_total;

    }
    
    public Merchant( String userid,
            String username,
            String password,
            String phone,
            String email,
            String address,
            String amount_due,
            String account_number,
            String firstname,  
            String lastname, 
            String birth_date,
            String gender,
            String supply, 
            String gain,
            String purchases_total){
        
        super(userid, username, password, phone, email, address, amount_due, account_number);

        this.first_name = firstname;
        this.last_name = lastname;
        this.birth_date = birth_date;
        this.gender = gender;
        this.supply = supply;
        this.gain = gain;
        this.purchases_total = purchases_total;
    }
    
    
        
    @Override
    public void checkFields() throws Exception{
        
        super.checkFields();
        
        if(first_name == null || first_name.trim().isEmpty()
            || last_name == null || last_name.trim().isEmpty()
            || birth_date == null || birth_date.trim().isEmpty()
            || gender == null || gender.trim().isEmpty()
            || supply == null || supply.trim().isEmpty()
            || gain == null || gain.trim().isEmpty()
            || purchases_total == null || purchases_total.trim().isEmpty())
        {
            
            throw new Exception("Missing Fields");
        }
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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public String getPurchases_total() {
        return purchases_total;
    }

    public void setPurchases_total(String purchases_total) {
        this.purchases_total = purchases_total;
    }
    
    /**
     * Return the string representation of the object in rows
     *
     * 
    */
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("first_name:").append(first_name).append("\n")
                .append("last_name: ").append(last_name).append("\n")
                .append("birth_date: ").append(birth_date).append("\n")
                .append("gender: ").append(gender).append("\n")
                .append("supply: ").append(supply).append("\n")
                .append("gain: ").append(gain).append("\n")
                .append("Purchases_total: ").append(purchases_total).append("\n");
        return super.toString() + ss.toString();
    }

    
    
}
