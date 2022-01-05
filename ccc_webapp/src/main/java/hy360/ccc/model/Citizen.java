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
    private String first_name;
    private String last_name;
    private String amka;
    private String vat;
    private String birth_date;
    private Gender gender;
    
    
    
    public Citizen(){
        first_name = "";
        last_name = "";
        amka = "";
        vat = "";
        birth_date = "";
        gender = Gender.UNKNOWN;
    }
    
    public Citizen(String first_name, 
            String last_name, String amka, String vat,
            String birth_date, Gender gender){
        
        this.amka = amka;
        this.vat = vat;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;

    }
    
    public Citizen(String username, String password, 
            String email, String address, String amount_due, String account_number, 
            String CustomerId, String account_due, 
            String amka, String vat, String first_name, 
            String last_name, String credit_limit, String credit_balance,
            String birth_date, Gender gender){
        
        super(username, password, email, address, amount_due, account_number, 
                CustomerId, credit_limit, account_due, credit_balance);
        
        this.amka = amka;
        this.vat = vat;
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        
    }
    
    
    
    
    /**
     * Enum for supporting gender values
     */
    public enum Gender {

        MALE("Male"), FEMALE("Female"), UNKNOWN("Unknown");
        private final String value;

        private Gender(String value) {
            this.value = value;
        }

        /**
         * Returns string representation of value
         *
         * @return
         */
        @Override
        public String toString() {
            return this.value;
        }
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
     * @return the amka
     */
    public String getAmka() {
        return amka;
    }

    /**
     * @param amka the amka to set
     */
    public void setAmka(String amka) {
        this.amka = amka;
    }

    /**
     * @return the vat
     */
    public String getVat() {
        return vat;
    }

    /**
     * @param vat the vat to set
     */
    public void setVat(String vat) {
        this.vat = vat;
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
    public Gender getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("first_name: ").append(first_name).append("\n")
                .append("last_name: ").append(last_name).append("\n")
                .append("amka:").append(amka).append("\n")
                .append("vat: ").append(vat).append("\n")
                .append("birth_date: ").append(birth_date).append("\n")
                .append("Gender: ").append(gender).append("\n");

       
        return super.toString() + ss.toString();

    }
}
