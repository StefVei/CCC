/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils_db;

/**
 *
 * @author sckou
 */
public class BadUser {
    private double amount_due;
    private String phone;
    private String email;
    private String first_name;
    private String last_name;
    private String name;
    private String type;
    
    public BadUser(double amount_due,
     String phone,
    String email,
     String first_name,
     String last_name,
     String name,
    String type){
        this.first_name= first_name;
        this.last_name= last_name;
                this.name = name;
                this.phone =phone;
                this.type=type;
                this.amount_due=amount_due;
                this.email=email;
    }

    public double getAmount_due() {
        return amount_due;
    }

    public void setAmount_due(double amount_due) {
        this.amount_due = amount_due;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
