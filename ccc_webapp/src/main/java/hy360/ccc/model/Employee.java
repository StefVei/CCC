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

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    private String employee_id;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String gender;
    private String email;
    private String address;
    private String phone;
    private String company_id;
    private boolean deleted;

    public Employee(){
        employee_id = "";
        first_name = "";
        last_name = "";
        birth_date = "";
        gender = "";
        email = "";
        address = "";
        phone="";
        company_id = "";
        deleted = false;
    }
    
    
    public Employee(String employee_id, String first_name, String last_name,
            String birth_date, String gender, String email,
            String address, String phone, String company_id) {
        this.employee_id=employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.company_id = company_id;
        deleted = false;
    }

    public void checkFields() throws Exception {

        if (first_name == null || first_name.trim().isEmpty()
                || last_name == null || last_name.trim().isEmpty()
                || birth_date == null || birth_date.trim().isEmpty()
                || gender == null || gender.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || address == null || address.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()
                || company_id == null || company_id.trim().isEmpty()) {

            throw new Exception("Missing Fields");
        }
    }

            
    
    /**
     * @return the first_name
     */
    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

     /**
     * @return the company_id
     */
    public String getCompany_id() {
        return company_id;
    }

    /**
     * @param company_id the company_id to set
     */
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("employee_id: ").append(employee_id).append("\n")
                .append("first_name: ").append(first_name).append("\n")
                .append("last_name: ").append(last_name).append("\n")
                .append("birth_date: ").append(birth_date).append("\n")
                .append("gender: ").append(gender).append("\n")
                .append("email: ").append(email).append("\n")
                .append("address:").append(address).append("\n")
                .append("phone: ").append(phone).append("\n")
                .append("company_id: ").append(company_id).append("\n");
        return ss.toString();
    }

}
