/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Product {
    private String product_id;
    private String name;
    private double price;
    private int quantity;
    private String merchant_id;
    private boolean deleted;
    
    
    public Product(){
        product_id = "";
        name = "";
        price = 0;
        quantity = 0;
        merchant_id = "";
        deleted = false;
    }
    
    public Product(String product_id, String name, double price, int quantity, String merchant_id) {
        this.product_id=product_id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.merchant_id = merchant_id;
        this.deleted = false;
    }

    public void checkFields() throws Exception {

        if (name == null || name.trim().isEmpty()
                || merchant_id == null || merchant_id.trim().isEmpty()) {

            throw new Exception("Missing Fields");
        }
    }
    
    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("product_id: ").append(product_id).append("\n")
                .append("name: ").append(name).append("\n")
                .append("price: ").append(price).append("\n")
                .append("quantity: ").append(quantity).append("\n")
                .append("merchant_id: ").append(merchant_id).append("\n");
        return ss.toString();
    }
}