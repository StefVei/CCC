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
    private String price;
    private String quantity;
    
    
    public Product(){
        product_id = "";
        name = "";
        price = "";
        quantity = "";
    }
    
    public Product(String name, String price, String quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void checkFields() throws Exception {

        if (name == null || name.trim().isEmpty()
                || price == null || price.trim().isEmpty()
                || quantity == null || quantity.trim().isEmpty()) {

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("name: ").append(name).append("\n")
                .append("price: ").append(price).append("\n")
                .append("quantity: ").append(quantity).append("\n");
        return ss.toString();
    }
}