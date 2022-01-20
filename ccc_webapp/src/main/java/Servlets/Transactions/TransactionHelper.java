/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets.Transactions;

import hy360.ccc.db.MerchantDB;
import hy360.ccc.model.Merchant;

/**
 *
 * @author panagiotisk
 */
public class TransactionHelper {

    public static void updateMerchant(String mer_id, double supply,
            double gain, int pur_total, double amount_due) {

        Merchant merchant = MerchantDB.getMerchant("USERID", mer_id);
        if (gain != -1) {
            merchant.setGain(gain);
        }
        if (amount_due != -1) {
            merchant.setAmount_due(amount_due);
        }
        if (pur_total != -1) {
            merchant.setPurchases_total(pur_total);
        }
        if (supply != -1) {
            merchant.setSupply(supply);
        }

        MerchantDB.updateMerchant(merchant);

    }
}
