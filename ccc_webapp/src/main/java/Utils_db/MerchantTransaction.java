/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils_db;

/**
 *
 * @author panagiotisk
 */
public class MerchantTransaction {
    private String product_name;
    private double quantity;
    private String total_price;
    private String date;
    private String customer_name;
    private String employee_name;
    private String type;

    public MerchantTransaction(String pr, double qu, String am, String d, String c_name, String type) {
        product_name = pr;
        quantity = qu;
        total_price = am;
        date = d;
        customer_name = c_name;
        this.type = type;
    }


    public MerchantTransaction(String em, String pr, double qu, String am, String d, String c_name, String type) {
        product_name = pr;
        quantity = qu;
        total_price = am;
        date = d;
        customer_name = c_name;
        this.type = type;
        employee_name = em;
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
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the total_price
     */
    public String getTotal_price() {
        return total_price;
    }

    /**
     * @param total_price the total_price to set
     */
    public void setTotal_price(String total_price) {
        this.total_price = total_price;
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
     * @return the customer_name
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * @param customer_name the customer_name to set
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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
}
