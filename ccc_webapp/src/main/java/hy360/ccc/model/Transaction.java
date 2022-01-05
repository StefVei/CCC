/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author panagiotisk
 */
public class Transaction {
    private String transaction_id;
    private String date;
    private Pay_type pay_type;
    private Transaction_type transaction_type;
    private String amount;
    private List<String> products;
    
    
    
    public void checkFields() throws Exception{
        
        
        if(date == null || date.trim().isEmpty()
            || amount == null || amount.trim().isEmpty()
            || date == null || date.trim().isEmpty()
            || (pay_type != Pay_type.CHARGE && pay_type != Pay_type.CREDIT && pay_type != Pay_type.UNKNOWN)
            || (transaction_type != Transaction_type.BUY && transaction_type != Transaction_type.RETURN &&
                transaction_type != Transaction_type.UNKNOWN))
        {
            
            throw new Exception("Missing Fields");
        }
        
    }
    
    
    
    public List<String> getProducts(){
        return products;
    }
    
    
   /** 
    * @param products a string separated with commas : 
    * "product_1, product_2, ..., product_n" 
    *
    * 
    */
    public void setProducts(String products){
        this.products = Arrays.asList(products.split(",[ ]*"));
    }
    
    public enum Pay_type{
        
        CHARGE("charge"), CREDIT("credit"), UNKNOWN("uknown");
        private final String value;
        
        private Pay_type(String value){
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
     *  for supporting transaction_type values
     */
    public enum Transaction_type {

        RETURN("return"), BUY("buy"), UNKNOWN("unknown");
        private final String value;

        private Transaction_type(String value) {
            this.value = value;
        }

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
     * @return the pay_type
     */
    public Pay_type getPay_type() {
        return pay_type;
    }

    /**
     * @param pay_type the pay_type to set
     */
    public void setPay_type(String pay_type) {
        switch (pay_type.toLowerCase().trim()) {
            case "charge":
                this.pay_type = Pay_type.CHARGE;
                break;
            case "credit":
                this.pay_type = Pay_type.CREDIT;
                break;
            default:
                this.pay_type = Pay_type.UNKNOWN;
                break;
        }

    }

    /**
     * @return the transaction_type
     */
    public Transaction_type getTransaction_type() {
        return transaction_type;
    }

    /**
     * @param transaction_type the transaction_type to set
     */
    public void setTransaction_type(String transaction_type) {
        switch (transaction_type.toLowerCase().trim()) {
            case "return":
                this.transaction_type = Transaction_type.RETURN;
                break;
            case "credit":
                this.transaction_type = Transaction_type.BUY;
                break;
            default:
                this.transaction_type = Transaction_type.UNKNOWN;
                break;
        }

    
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
