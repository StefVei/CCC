/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils_db;

/**
 *
 * @author panagiotisk
 */
public class CompanyTransaction {

    private String merchant_name;
    private String date;
    private int quantity;
    private double amount;
    private String product_name;
    private String employee_name;
    private String type;

    public CompanyTransaction(String mer, String d, int qu, double am, String product,
            String emplo, String type) {
        merchant_name = mer;
        date = d;
        quantity = qu;
        amount = am;
        product_name = product;
        employee_name = emplo;
        this.type = type;
    }

    /**
     * @return the merchant_name
     */
    public String getMerchant_name() {
        return merchant_name;
    }

    /**
     * @param merchant_name the merchant_name to set
     */
    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * @return the product_name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @param product_name the product_name to set
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * @return the employee_name
     */
    public String getEmployee_name() {
        return employee_name;
    }

    /**
     * @param employee_name the employee_name to set
     */
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
