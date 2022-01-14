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
    private String pay_type;
    private String transaction_type;
    private String amount;
    private String citizen_id;
    private String company_id;
    private String merchant_cit_id;
    private String merchant_comp_id;
    private String employee_id;

    private String activeFields;

    public Transaction() {
        transaction_id = "";
        date = "";
        pay_type = "U";
        transaction_type = "U";
        amount = "";
        citizen_id = "";
        company_id = "";
        merchant_cit_id = "";
        merchant_comp_id = "";
        employee_id = "";
    }

    public Transaction(String tr_id, String date, String pay_type,
            String tr_type, String amount, String cit_id, String comp_id,
            String merchant_cit_id, String merchant_comp_id, String empl_id) {
        transaction_id = tr_id;
        this.date = date;
        this.pay_type = pay_type;
        transaction_type = tr_type;
        this.amount = amount;
        citizen_id = cit_id;
        company_id = comp_id;
        this.merchant_cit_id = merchant_cit_id;
        this.merchant_comp_id = merchant_comp_id;
        this.employee_id = empl_id;

        activeFields = "PAY_TYPE, TRANSACTION_TYPE, AMOUNT, DATE, ";
        if (!citizen_id.isEmpty()) {
            activeFields += "CITIZEN_USERID, MERCHANT_TRADE";
        } else {
            activeFields += "EMPLOYEE_ID, COMPANY_USERID, MERCHANT_TRAFFIC";
        }
    }
    
    
    public void checkFields() throws Exception{
        
        
        if(date == null || date.trim().isEmpty()
            || amount == null || amount.trim().isEmpty()
                || date == null || date.trim().isEmpty()
                || (pay_type == null && pay_type.trim().isEmpty())
                || (transaction_type == null && transaction_type.trim().isEmpty()))        {
            
            throw new Exception("Missing Fields");
        }
        
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
    public String getPay_type() {
        return pay_type;
    }

    /**
     * @param pay_type the pay_type to set
     */
    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
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

    /**
     * @return the citizen_id
     */
    public String getCitizen_id() {
        return citizen_id;
    }

    /**
     * @param citizen_id the citizen_id to set
     */
    public void setCitizen_id(String citizen_id) {
        this.citizen_id = citizen_id;
    }

    /**
     * @return the company_id
     */
    public String getCompany_id() {
        return company_id;
    }

    /**
     * @param company_id the company_id to set
     */
    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    /**
     * @return the merchant_cit_id
     */
    public String getMerchant_cit_id() {
        return merchant_cit_id;
    }

    /**
     * @param merchant_cit_id the merchant_cit_id to set
     */
    public void setMerchant_cit_id(String merchant_cit_id) {
        this.merchant_cit_id = merchant_cit_id;
    }

    /**
     * @return the merchant_comp_id
     */
    public String getMerchant_comp_id() {
        return merchant_comp_id;
    }

    /**
     * @param merchant_comp_id the merchant_comp_id to set
     */
    public void setMerchant_comp_id(String merchant_comp_id) {
        this.merchant_comp_id = merchant_comp_id;
    }

    /**
     * @return the employee_id
     */
    public String getEmployee_id() {
        return employee_id;
    }

    /**
     * @param employee_id the employee_id to set
     */
    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();
        ss.append("transaction_id: ").append(transaction_id).append("\n")
                .append("pay_type: ").append(pay_type).append("\n")
                .append("transaction_type:").append(transaction_type).append("\n")
                .append("amount: ").append(amount).append("\n")
                .append("date: ").append(date).append("\n")
                .append("citizen_id : ").append(citizen_id).append("\n")
                .append("company_id: ").append(company_id).append("\n")
                .append("merchant_cit_id : ").append(merchant_cit_id).append("\n")
                .append("merchant_comp_id: ").append(merchant_comp_id).append("\n")
                .append("employee_id: ").append(employee_id).append("\n");

        return ss.toString();

    }

    /**
     * @return the activeFields
     */
    public String getActiveFields() {
        return activeFields;
    }

}
