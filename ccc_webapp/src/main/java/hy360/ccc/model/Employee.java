/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author sckou
 */
public class Employee {
    private String first_name;
    private String last_name;
    private String birth_date;
    private Gender gender;
    private String address;
    private String email;
    private String phone;
    
    public Employee(){
        first_name = "";
        last_name = "";
        birth_date = "";
        gender = Gender.UNKNOWN;
        address = "";
        email = "";
    }
    
    public Employee(String first_name, 
            String last_name, String birth_date,
            Gender gender, String address, String email){
        this.gender = gender;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.address = address;
        this.email = email;

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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the amka to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the vat
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the vat to set
     */
    public void setEmail(String email) {
        this.email = email;
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
                .append("address:").append(address).append("\n")
                .append("email: ").append(email).append("\n")
                .append("birth_date: ").append(birth_date).append("\n");
       
        return ss.toString();

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