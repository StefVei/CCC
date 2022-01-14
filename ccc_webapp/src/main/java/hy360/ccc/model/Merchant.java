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
    private String last_name;
    private String first_name;
    private String supply;
    private String gain;
    private String birth_date;
    private String gender;
    private String purchases_total;
    private String user_id;

    
    public Merchant(){
        last_name = "";
        first_name = "";
        supply = "";
        gain = "";
        birth_date = "";
        gender = "O";
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
    
    public Merchant( String userName,
            String password,
            String email,
            String address,
            String amount_due,
            String account_number,  
            String lastname, 
            String firstname,
            String supply, 
            String gain,
            String birth_date,
            String gender,
            String purchases_total){
        
        super(userName, password, email, address, amount_due, account_number);

        this.last_name = lastname;
        this.first_name = firstname;
        this.supply = supply;
        this.gain = gain;
        this.birth_date = birth_date;
        this.gender = gender;
        this.purchases_total = purchases_total;
    }
    
    
        
    @Override
    public void checkFields() throws Exception{
        
        super.checkFields();
        
        if(gain == null || gain.trim().isEmpty()
            || first_name == null || first_name.trim().isEmpty()
            || birth_date == null || birth_date.trim().isEmpty()
            || last_name == null || last_name.trim().isEmpty()
            || supply == null || supply.trim().isEmpty()
            || gender == null || gender.trim().isEmpty()
            || purchases_total == null || purchases_total.trim().isEmpty())
        {
            
            throw new Exception("Missing Fields");
        }
    }
    
    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

     /**
     * @return the birth_date
     */
    public String getBirth_date() {
        return birth_date;
    }

    /**
     * @param birth_date the birth_date to set
     */
    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * @return the supply
     */
    public String getSupply() {
        return supply;
    }

    /**
     * @param supply the supply to set
     */
    public void setSupply(String supply) {
        this.supply = supply;
    }

    /**
     * @return the gain
     */
    public String getGain() {
        return gain;
    }

    /**
     * @param gain the gain to set
     */
    public void setGain(String gain) {
        this.gain = gain;
    }

/**
     * @return the purchases_total
     */
    public String getPurchases_total() {
        return purchases_total;
    }

    /**
     * @param purchases_total the purchases_total to set
     */
    public void setPurchases_total(String purchases_total) {
        this.purchases_total = purchases_total;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    
    
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("last_name: ").append(last_name).append("\n")
                .append("first_name:").append(first_name).append("\n")
                .append("supply: ").append(supply).append("\n")
                .append("gain: ").append(gain).append("\n")
                .append("birth_date: ").append(birth_date).append("\n")
                .append("Gender: ").append(gender).append("\n")
                .append("Purchases_total: ").append(purchases_total).append("\n");

       
        return super.toString() + ss.toString();
    }

    
    
}
