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
    private String bought_product_id;
    
    public BoughtProduct(){
        transaction_id = "";
        bought_product_id = "";
    }
    
    public BoughtProduct(String transaction, String bought_prod){
        transaction_id = transaction;
        bought_product_id = bought_prod;
        
    }
    
    public void checkFields() throws Exception{
        
        
        if(transaction_id == null || transaction_id.trim().isEmpty()
            || bought_product_id == null || bought_product_id.trim().isEmpty()){
            
            throw new Exception("Missing Fields");
        }
    }
    
    
    
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("transaction_id: ").append(transaction_id).append("\n")
                .append("bought_product_id: ").append(bought_product_id).append("\n");
       
        return ss.toString();
    }

    /**
     * @return the transaction_id
     */
    public String getTransaction_id() {
        return transaction_id;
    }

    /**
     * @param transaction_id the transaction_id to set
     */
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    /**
     * @return the bought_product_id
     */
    public String getBought_product_id() {
        return bought_product_id;
    }

    /**
     * @param bought_product_id the bought_product_id to set
     */
    public void setBought_product_id(String bought_product_id) {
        this.bought_product_id = bought_product_id;
    }
    
}
