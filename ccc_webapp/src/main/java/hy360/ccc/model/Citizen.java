/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Citizen extends User{
    private String amka;
    private String vat;
    private String first_name;
    private String last_name;
    private String birth_date;
    private String gender;
    private String email;
    private String address;
    private String phone;
    private double amount_due;
    private double credit_limit;
    private double credit_balance;
    private String account_due_date;

    
    
    public Citizen(){
        amka = "";
        vat = "";
        first_name = "";
        last_name = "";
        birth_date = "";
        gender = "O";
        email="";
        address="";
        phone="";
        amount_due = 0;
        credit_limit = 0;
        credit_balance = 0;
        account_due_date = "";
    }

    public Citizen(String amka, String vat, String first_name, 
            String last_name, String birth_date, String gender,
            String email, String address, String phone, double amount_due,
            double credit_limit, double credit_balance, String account_due_date) {

        this.amka = amka;
        this.vat = vat;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.amount_due=amount_due;
        this.credit_limit = credit_limit;
        this.credit_balance = credit_balance;
        this.account_due_date = account_due_date;
    }
    
    public Citizen(
            String userid, String username, String password, String account_number,
            String amka, String vat, String first_name, String last_name, String birth_date,
            String gender, String email, String address, String phone, double amount_due,
            double credit_limit, double credit_balance, String account_due_date) {

        super(userid, username, password, account_number);

        this.amka = amka;
        this.vat = vat;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.gender = gender;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.amount_due=amount_due;
        this.credit_limit = credit_limit;
        this.credit_balance = credit_balance;
        this.account_due_date = account_due_date;

    }
    
    
    @Override
    public void checkFields() throws Exception{
        
        super.checkFields();
        
        if( amka == null || first_name.trim().isEmpty()
            || vat == null || vat.trim().isEmpty()   
            || first_name == null || first_name.trim().isEmpty()
            || last_name == null || last_name.trim().isEmpty()
            || birth_date == null || birth_date.trim().isEmpty()
            || gender == null || gender.trim().isEmpty()
            || email == null || email.trim().isEmpty()
            || address == null || address.trim().isEmpty()
                || phone == null || phone.trim().isEmpty()
                || account_due_date == null || account_due_date.trim().isEmpty())
        {
            
            throw new Exception("Missing Fields");
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public double getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(double credit_limit) {
        this.credit_limit = credit_limit;
    }

    public double getCredit_balance() {
        return credit_balance;
    }

    public void setCredit_balance(double credit_balance) {
        this.credit_balance = credit_balance;
    }

    public String getAccount_due_date() {
        return account_due_date;
    }

    public void setAccount_due_date(String account_due_date) {
        this.account_due_date = account_due_date;
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("amka:").append(amka).append("\n")
                .append("vat: ").append(vat).append("\n")
                .append("first_name: ").append(first_name).append("\n")
                .append("last_name: ").append(last_name).append("\n")
                .append("birth_date: ").append(birth_date).append("\n")
                .append("gender: ").append(gender).append("\n")
                .append("email: ").append(email).append("\n")
                .append("address: ").append(address).append("\n")
                .append("phone: ").append(phone).append("\n")
                .append("amount_due: ").append(amount_due).append("\n")
                .append("credit_limit: ").append(credit_limit).append("\n")
                .append("credit_balance: ").append(credit_balance).append("\n")
                .append("account_due_date:").append(account_due_date).append("\n");

       
        return super.toString() + ss.toString();

    }

}
