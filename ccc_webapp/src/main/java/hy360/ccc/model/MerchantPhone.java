/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class MerchantPhone {
    private String merchant_id;
    private String phone;
    
    public MerchantPhone(){
        merchant_id = "";
        phone = "";
    }
    
    public MerchantPhone(String merchant, String phone){
        merchant_id = merchant;
        this.phone = phone;
    }
    
    
       public void checkFields() throws Exception{
        
        
        if(merchant_id == null || merchant_id.trim().isEmpty()
            || phone == null || phone.trim().isEmpty()){
            
            throw new Exception("Missing Fields");
        }
    }
    
    
    
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("merchant_id: ").append(getMerchant_id()).append("\n")
                .append("phone: ").append(getPhone()).append("\n");
       
        return ss.toString();
    }


    /**
     * @return the merchant_id
     */
    public String getMerchant_id() {
        return merchant_id;
    }

    /**
     * @param merchant_id the merchant_id to set
     */
    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
    
    
}
