/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
import hy360.ccc.db.BoughtProductDB;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import static hy360.ccc.db.CitizenTradesDB.addTrade;
import static hy360.ccc.db.CompanyTradesDB.addTraffic;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.ProductDB;
import hy360.ccc.model.BoughtProduct;
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
 * @author panagiotisk
 */
public class MakeTransaction extends HttpServlet {

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
            out.println("<title>Servlet MakeTransaction</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MakeTransaction at " + request.getContextPath() + "</h1>");
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

        Transaction transaction = new Transaction();
        String customer_type;
        String merchant_id, citizen_id, employee_id, product_id, quantity;
        String customer_amount_due;
        double product_quantity, merchant_gain, merchant_supply, credit_balance, credit_limit, cost;
        
        
        LocalDate date = java.time.LocalDate.now();
        
        merchant_id = request.getParameter("merchantId");
        product_id = request.getParameter("productId");
        quantity = request.getParameter("quantityOfBuyingProduct");
        customer_type = request.getParameter("isCitizen");
        
        transaction.setTransaction_type("A");
        transaction.setDate(date.toString());
        
        Product buying_product = ProductDB.getProduct(product_id, merchant_id);
        cost = Double.valueOf(buying_product.getPrice()) * Integer.valueOf(quantity);
        
        
        if (customer_type.equals("true")) {
            citizen_id = request.getParameter("citizenId");
            credit_balance = Double.valueOf(CitizenDB.getCitizen(Integer.valueOf(citizen_id)).getCredit_balance());
            credit_limit = Double.valueOf(CitizenDB.getCitizen(Integer.valueOf(citizen_id)).getCredit_limit());
            customer_amount_due = CitizenDB.getCitizen(Integer.valueOf(citizen_id)).getAmount_due();
            if ( credit_balance >= cost && credit_balance == credit_limit){
                transaction.setPending("Y");
                transaction.setAmount(String.valueOf(cost));
                
                
                Merchant merchant = MerchantDB.getMerchant(Integer.valueOf(merchant_id));
                merchant_gain = Double.valueOf(merchant.getGain())+ cost;
                merchant_supply = Double.valueOf(merchant.getSupply());
                merchant.setGain(String.valueOf(merchant_gain));
                merchant.setAmount_due(String.valueOf(merchant_gain * merchant_supply));
                merchant.setPurchases_total(String.valueOf(Integer.valueOf(merchant.getPurchases_total()) + 1));
                MerchantDB.updateMerchant(merchant);
                
                product_quantity = Integer.valueOf(buying_product.getQuantity()) - Integer.valueOf(quantity);
                buying_product.setQuantity(String.valueOf(product_quantity));
                ProductDB.updateProduct(buying_product);
                
                BoughtProduct pro = new BoughtProduct();
                pro.setMerchant_id(merchant_id);
                pro.setProduct_id(product_id);
                pro.setTotal(quantity);
                pro.setTransaction_id(transaction.getTransaction_id());
                BoughtProductDB.addBoughtProducts(pro.getTransaction_id()+" "+pro.getProduct_id()+" "+pro.getMerchant_id());
                
                addTrade(citizen_id, merchant_id, transaction.getTransaction_id());
                
                int cit_id = Integer.valueOf(citizen_id);
                Citizen cit = CitizenDB.getCitizen(cit_id);
                double new_balance = credit_balance - cost;
                double new_amount_due = Double.valueOf(customer_amount_due) + cost;
                cit.setCredit_balance(String.valueOf(new_balance));
                cit.setAmount_due(String.valueOf(new_amount_due));
                CitizenDB.updateCitizen(cit); 
            }
            else if(credit_balance >= cost && credit_balance != credit_limit){
                transaction.setPending("Y");
                transaction.setAmount(String.valueOf(cost));
                
                
                Merchant merchant = MerchantDB.getMerchant(Integer.valueOf(merchant_id));
                merchant_gain = Double.valueOf(merchant.getGain())+ cost;
                merchant_supply = Double.valueOf(merchant.getSupply());
                merchant.setGain(String.valueOf(merchant_gain));
                merchant.setAmount_due(String.valueOf(merchant_gain * merchant_supply));
                merchant.setPurchases_total(String.valueOf(Integer.valueOf(merchant.getPurchases_total()) + 1));
                MerchantDB.updateMerchant(merchant);
                
                product_quantity = Integer.valueOf(buying_product.getQuantity()) - Integer.valueOf(quantity);
                buying_product.setQuantity(String.valueOf(product_quantity));
                ProductDB.updateProduct(buying_product);
                
                BoughtProduct pro = new BoughtProduct();
                pro.setMerchant_id(merchant_id);
                pro.setProduct_id(product_id);
                pro.setTotal(quantity);
                pro.setTransaction_id(transaction.getTransaction_id());
                BoughtProductDB.addBoughtProducts(pro.getTransaction_id()+" "+pro.getProduct_id()+" "+pro.getMerchant_id());
                
                addTrade(citizen_id, merchant_id, transaction.getTransaction_id());
                
                int cit_id = Integer.valueOf(citizen_id);
                Citizen cit = CitizenDB.getCitizen(cit_id);
                double new_balance = credit_balance - cost;
                double new_amount_due = Double.valueOf(customer_amount_due) + (cost - (credit_balance-credit_limit));
                cit.setCredit_balance(String.valueOf(new_balance));
                cit.setAmount_due(String.valueOf(new_amount_due));
                CitizenDB.updateCitizen(cit);
            }
            else if(credit_balance< cost){
                transaction.setPending("N");
                transaction.setAmount(String.valueOf(cost));
                
                addTrade(citizen_id, merchant_id, transaction.getTransaction_id());
            }
        } 
        
        else {
            employee_id = request.getParameter("employeeId");
            String company_name = request.getParameter("Name");
            Company mycompany = CompanyDB.getCompany("NAME", company_name);
            customer_amount_due = mycompany.getAmount_due();
            credit_balance = Double.valueOf(mycompany.getCredit_balance());
            credit_limit = Double.valueOf(mycompany.getCredit_limit());
            if ( credit_balance >= cost && credit_balance == credit_limit){
                transaction.setPending("Y");
                transaction.setAmount(String.valueOf(cost));
                
                
                Merchant merchant = MerchantDB.getMerchant(Integer.valueOf(merchant_id));
                merchant_gain = Double.valueOf(merchant.getGain())+ cost;
                merchant_supply = Double.valueOf(merchant.getSupply());
                merchant.setGain(String.valueOf(merchant_gain));
                merchant.setAmount_due(String.valueOf(merchant_gain * merchant_supply));
                merchant.setPurchases_total(String.valueOf(Integer.valueOf(merchant.getPurchases_total()) + 1));
                MerchantDB.updateMerchant(merchant);
                
                product_quantity = Integer.valueOf(buying_product.getQuantity()) - Integer.valueOf(quantity);
                buying_product.setQuantity(String.valueOf(product_quantity));
                ProductDB.updateProduct(buying_product);
                
                BoughtProduct pro = new BoughtProduct();
                pro.setMerchant_id(merchant_id);
                pro.setProduct_id(product_id);
                pro.setTotal(quantity);
                pro.setTransaction_id(transaction.getTransaction_id());
                BoughtProductDB.addBoughtProducts(pro.getTransaction_id()+" "+pro.getProduct_id()+" "+pro.getMerchant_id());
                
                addTraffic(transaction.getTransaction_id(), merchant_id, mycompany.getUser_id(), employee_id);
                
                Company comp = CompanyDB.getCompany("NAME", company_name);
                double new_balance = credit_balance - cost ;
                double new_amount_due = Double.valueOf(customer_amount_due) + cost;
                comp.setCredit_balance(String.valueOf(new_balance));
                comp.setAmount_due(String.valueOf(new_amount_due));
                CompanyDB.updateCompany(comp); 
            }
            else if(credit_balance >= cost && credit_balance != credit_limit){
                transaction.setPending("Y");
                transaction.setAmount(String.valueOf(cost));
                
                
                Merchant merchant = MerchantDB.getMerchant(Integer.valueOf(merchant_id));
                merchant_gain = Double.valueOf(merchant.getGain())+ cost;
                merchant_supply = Double.valueOf(merchant.getSupply());
                merchant.setGain(String.valueOf(merchant_gain));
                merchant.setAmount_due(String.valueOf(merchant_gain * merchant_supply));
                merchant.setPurchases_total(String.valueOf(Integer.valueOf(merchant.getPurchases_total()) + 1));
                MerchantDB.updateMerchant(merchant);
                
                product_quantity = Integer.valueOf(buying_product.getQuantity()) - Integer.valueOf(quantity);
                buying_product.setQuantity(String.valueOf(product_quantity));
                ProductDB.updateProduct(buying_product);
                
                BoughtProduct pro = new BoughtProduct();
                pro.setMerchant_id(merchant_id);
                pro.setProduct_id(product_id);
                pro.setTotal(quantity);
                pro.setTransaction_id(transaction.getTransaction_id());
                BoughtProductDB.addBoughtProducts(pro.getTransaction_id()+" "+pro.getProduct_id()+" "+pro.getMerchant_id());
                
                addTraffic(transaction.getTransaction_id(), merchant_id, mycompany.getUser_id(), employee_id);
                
                Company comp = CompanyDB.getCompany("NAME", company_name);
                double new_balance = credit_balance - cost;
                double new_amount_due = Double.valueOf(customer_amount_due) + (cost - (credit_balance-credit_limit));
                comp.setCredit_balance(String.valueOf(new_balance));
                comp.setAmount_due(String.valueOf(new_amount_due));
                CompanyDB.updateCompany(comp);
            }
            else if(credit_balance< cost){
                transaction.setPending("N");
                transaction.setAmount(String.valueOf(cost));
                
                addTraffic(transaction.getTransaction_id(), merchant_id, mycompany.getUser_id(), employee_id);
            }
        }
    }
}
