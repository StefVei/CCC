/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class BoughtProduct {
    private String transaction_id;
    private String product_id;
    private String merchant_id;
    private double total;
    
    public BoughtProduct(){
        transaction_id = "";
        product_id = "";
        merchant_id = "";
        total = 0;
    }
    
    public BoughtProduct(String transaction, String bought_prod,
            String merchant, double total) {
        this.transaction_id = transaction;
        this.product_id = bought_prod;
        this.merchant_id = merchant;
        this.total = total;
        
    }
    
    public void checkFields() throws Exception{
        
        
        if(transaction_id == null || transaction_id.trim().isEmpty()
            || product_id == null || product_id.trim().isEmpty()
                || merchant_id == null || merchant_id.trim().isEmpty()) {

            throw new Exception("Missing Fields");
        }
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("transaction_id: ").append(transaction_id).append("\n")
                .append("product_id: ").append(product_id).append("\n")
                .append("merchant_id: ").append(merchant_id).append("\n")
                .append("total: ").append(total).append("\n");
       
        return ss.toString();
    }
}