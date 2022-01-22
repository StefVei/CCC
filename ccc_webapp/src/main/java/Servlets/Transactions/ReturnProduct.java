/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets.Transactions;

import static Servlets.Transactions.TransactionHelper.updateMerchant;
import com.google.gson.Gson;
import hy360.ccc.db.BoughtProductDB;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CitizenTradesDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.CompanyTradesDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.ProductDB;
import hy360.ccc.db.TransactionDB;
import hy360.ccc.db.UserDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.Company;
import hy360.ccc.model.Merchant;
import hy360.ccc.model.Product;
import hy360.ccc.model.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sckou
 */
public class ReturnProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReturnProducts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReturnProducts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        String str;
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        Transaction transaction;
        String transactionId, user_id;
        String merchant_id, employee_id, product_id;
        double merchant_gain, merchant_supply, amount_due;
        int quantity, product_quantity;
        boolean isCitizen;
        
        LocalDate date = java.time.LocalDate.now();

        user_id = (request.getParameter("userId"));
        transactionId = request.getParameter("transactionId");
        System.out.println("transaction id is" + transactionId);
        String userType = UserDB.getUser("USERID", user_id).getUser_type();
        System.out.println("User type is "+userType.equals("C"));
        
        if ( userType.equals("I")){
            isCitizen = true;
             merchant_id = CitizenTradesDB.getTrade(transactionId).getMerchant_id();
        }
        else{
            isCitizen = false;
            merchant_id = CompanyTradesDB.getTrade(transactionId).getMerchant_id();
        }
        
        System.out.println(merchant_id);
                System.out.println(isCitizen);

        product_id = BoughtProductDB.getBoughtProduct(Integer.valueOf(transactionId)).getProduct_id();

        quantity = BoughtProductDB.getBoughtProduct(Integer.valueOf(transactionId)).getTotal();

        transaction = TransactionDB.getTransaction(transactionId);
        transaction.setTransaction_type("E");

        Product returning_product = ProductDB.getProduct(product_id);
        product_quantity = returning_product.getQuantity() + quantity;
        returning_product.setQuantity(product_quantity);
        ProductDB.updateProduct(returning_product);
        
        Merchant merchant = MerchantDB.getMerchant("USERID", merchant_id);
        merchant_gain = merchant.getGain() - (returning_product.getPrice() * quantity);
        merchant_supply = merchant.getSupply();

        updateMerchant(merchant_id, merchant_supply, merchant_gain,
                merchant.getPurchases_total() - 1,
                merchant_gain * merchant_supply);

        if (isCitizen == true) {

        } else {
            Company mycompany = CompanyDB.getCompany("USERID", user_id);
        }
        
        if (isCitizen == true) {
            Citizen cit = CitizenDB.getCitizen("USERID", user_id);
            double new_balance = cit.getCredit_balance() + (returning_product.getPrice() * quantity);
            cit.setCredit_balance(new_balance);
            if (new_balance < cit.getCredit_limit()) {
                cit.setAmount_due(cit.getCredit_limit() - cit.getCredit_balance());
            } else {
                cit.setAmount_due(0);
            }


            CitizenDB.updateCitizen(cit);
            TransactionDB.updateTransaction(transaction);

        } else {
            Company comp = CompanyDB.getCompany("USERID", user_id);
            double new_balance = comp.getCredit_balance();
            new_balance = new_balance + (returning_product.getPrice() * quantity);
            comp.setCredit_balance(new_balance);
            if (new_balance < comp.getCredit_limit()) {
                comp.setAmount_due(comp.getCredit_limit() - comp.getCredit_balance());
            } else {
                comp.setAmount_due(0);
            }


            CompanyDB.updateCompany(comp);
            TransactionDB.updateTransaction(transaction);

        }
        

    }
}