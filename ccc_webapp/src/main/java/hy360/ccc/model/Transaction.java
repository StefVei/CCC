/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Transaction {
    private String transaction_id;
    private String date;
    private String pending;
    private String transaction_type;
    private String amount;

    public Transaction() {
        transaction_id = "";
        date = "";
        pending = "Y";
        transaction_type = "A";
        amount = "";
    }

    public Transaction(String tr_id, String date, String pending, String tr_type, String amount) {
        this.transaction_id = tr_id;
        this.date = date;
        this.pending = pending;
        this.transaction_type = tr_type;
        this.amount = amount;
    }
    
    
    public void checkFields() throws Exception{
        
        
        if(date == null || date.trim().isEmpty()
            || amount == null || amount.trim().isEmpty()
                || date == null || date.trim().isEmpty()
                || (pending == null && pending.trim().isEmpty())
                || (transaction_type == null && transaction_type.trim().isEmpty()))        {
            
            throw new Exception("Missing Fields");
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
     * @return the pending
     */
    public String getPending() {
        return pending;
    }

    /**
     * @param pending the pending to set
     */
    public void setPending(String pending) {
        this.pending = pending;
    }

    /**
     * @return the transaction_type
     */
    public String getTransaction_type() {
        return transaction_type;
    }

    /**
     * @param transaction_type the transaction_type to set
     */
    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
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

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        ss.append("transaction_id: ").append(transaction_id).append("\n")
                .append("pay_type: ").append(pending).append("\n")
                .append("transaction_type:").append(transaction_type).append("\n")
                .append("amount: ").append(amount).append("\n")
                .append("date: ").append(date).append("\n");
        return ss.toString();

    }

}
