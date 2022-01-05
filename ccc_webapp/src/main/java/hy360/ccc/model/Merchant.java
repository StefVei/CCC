/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hy360.ccc.model;

/**
 *
 * @author panagiotisk
 */
public class Merchant extends User {
    private String merchant_id;
    private String last_name;
    private String first_name;
    private String supply;
    private String gain;
    
    
    public Merchant(){
        merchant_id = "";
        last_name = "";
        first_name = "";
        supply = "";
        gain = "";
    }
    
    public Merchant(String userName,
            String password,
            String email,
            String address,
            String amount_due,
            String account_number, 
            String id, 
            String lastname, 
            String firstname,
            String supply, 
            String gain){
        
        super(userName, password, email, address, amount_due, account_number);

        merchant_id = id;
        last_name = lastname;
        first_name = firstname;
        this.supply = supply;
        this.gain = gain;
    }
    
    
        
    @Override
    public void checkFields() throws Exception{
        
        super.checkFields();
        
        if(gain == null || gain.trim().isEmpty()
            || first_name == null || first_name.trim().isEmpty()
            || merchant_id == null || merchant_id.trim().isEmpty()
            || last_name == null || last_name.trim().isEmpty()
            || supply == null || supply.trim().isEmpty())
        {
            
            throw new Exception("Missing Fields");
        }
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
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the supply
     */
    public String getSupply() {
        return supply;
    }

    /**
     * @param supply the supply to set
     */
    public void setSupply(String supply) {
        this.supply = supply;
    }

    /**
     * @return the gain
     */
    public String getGain() {
        return gain;
    }

    /**
     * @param gain the gain to set
     */
    public void setGain(String gain) {
        this.gain = gain;
    }
    
    public String toString(){
        StringBuilder ss = new StringBuilder();
        ss.append("merchant_id: ").append(merchant_id).append("\n")
                .append("last_name: ").append(last_name).append("\n")
                .append("first_name:").append(first_name).append("\n")
                .append("supply: ").append(supply).append("\n")
                .append("gain: ").append(gain).append("\n");

       
        return super.toString() + ss.toString();
    }

    
    
}
