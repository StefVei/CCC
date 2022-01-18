/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author sckou
 */
public class CM_Trades {
    private String citizen_id;
    private String merchant_id;
    private String transaction_id;
    
    public CM_Trades(){
        citizen_id = "";
        merchant_id = "";
        transaction_id = "";
    }
    
    public CM_Trades(String transaction, String merchant, String citizen){
        this.transaction_id = transaction;
        this.citizen_id = citizen;
        this.merchant_id = merchant;
        
        
    }
    
    public void checkFields() throws Exception{
        
        
        if(transaction_id == null || transaction_id.trim().isEmpty()
            || citizen_id == null || citizen_id.trim().isEmpty()
            || merchant_id == null || merchant_id.trim().isEmpty()){
            
            throw new Exception("Missing Fields");
        }
    }

    public String getCitizen_id() {
        return citizen_id;
    }

    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    
    @Override
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("transaction_id: ").append(transaction_id).append("\n")
                .append("citizen_id: ").append(citizen_id).append("\n")
                .append("merchant_id: ").append(merchant_id).append("\n");
       
        return ss.toString();
    }
}
