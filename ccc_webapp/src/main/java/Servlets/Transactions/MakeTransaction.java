/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Transactions;

import static Servlets.Transactions.TransactionHelper.updateMerchant;
import com.google.gson.Gson;
import hy360.ccc.db.BoughtProductDB;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CitizenTradesDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.CompanyTradesDB;
import hy360.ccc.db.EmployeeDB;

import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.ProductDB;
import hy360.ccc.db.TransactionDB;
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
        int is_citizen;
        String merchant_id, citizen_id, employee_id, product_id, user_id;
        double customer_amount_due;
        double merchant_gain, merchant_supply, credit_balance, credit_limit, cost;
        int product_quantity, quantity;
        
        LocalDate date = java.time.LocalDate.now();
        
        product_id = request.getParameter("productId");
        quantity = Integer.valueOf(request.getParameter("quantityOfBuyingProduct"));
        user_id = request.getParameter("userId");
        employee_id = request.getParameter("employeeId");
        merchant_id = request.getParameter("merchantId");

        is_citizen = 0;
        
        if (employee_id.equals("undefined")) {
            is_citizen = 1;
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        transaction.setTransaction_type("A");
        transaction.setDate(date.toString());
        
        Product buying_product = ProductDB.getProduct(product_id);
        cost = buying_product.getPrice() * quantity;
        
        
        if (is_citizen == 1) {
            citizen_id = user_id;
            credit_balance = CitizenDB.getCitizen("USERID", citizen_id).getCredit_balance();
            credit_limit = CitizenDB.getCitizen("USERID", citizen_id).getCredit_limit();
            customer_amount_due = CitizenDB.getCitizen("USERID", citizen_id).getAmount_due();
            if (credit_balance >= cost && cost <= credit_limit) {
                transaction.setAmount(String.valueOf(cost));
                TransactionDB.addTransaction(transaction);

                Merchant merchant = MerchantDB.getMerchant("USERID", merchant_id);
                merchant_gain = merchant.getGain() + cost;
                merchant_supply = merchant.getSupply();

                updateMerchant(merchant_id, -1, merchant_gain,
                        merchant.getPurchases_total() + 1,
                        merchant_gain * merchant_supply);

                
                product_quantity = buying_product.getQuantity() - quantity;
                buying_product.setQuantity(product_quantity);
                ProductDB.updateProduct(buying_product);
                
                BoughtProduct pro = new BoughtProduct();
                pro.setMerchant_id(merchant_id);
                pro.setProduct_id(product_id);
                pro.setTotal(quantity);
                pro.setTransaction_id(transaction.getTransaction_id());
                BoughtProductDB.addBoughtProduct(pro.getProduct_id(), pro.getTransaction_id(),
                        pro.getMerchant_id(), pro.getTotal());
                
                CitizenTradesDB.addTrade(citizen_id, merchant_id, transaction.getTransaction_id());
                
                Citizen cit = CitizenDB.getCitizen("USERID", citizen_id);
                double new_balance = credit_balance - cost;
                double new_amount_due = credit_limit - new_balance;
                cit.setCredit_balance(new_balance);
                cit.setAmount_due(new_amount_due);
                CitizenDB.updateCitizen(cit);
                
                response.setStatus(200);
                str = gson.toJson(transaction);
                response.getWriter().print(str);
            }
            else if(credit_balance< cost){
                response.setStatus(200);
                response.getWriter().print("Not enought credit balance");
            }
        }
        else {
            employee_id = request.getParameter("employeeId");
            String comp_id = EmployeeDB.getEmployee(employee_id).getCompany_id();
            
            Company mycompany = CompanyDB.getCompany("USERID", comp_id);
            customer_amount_due = mycompany.getAmount_due();
            credit_balance = mycompany.getCredit_balance();
            credit_limit = mycompany.getCredit_limit();
            if (credit_balance >= cost && cost <= credit_limit) {
                transaction.setAmount(String.valueOf(cost));
                TransactionDB.addTransaction(transaction);
                
                Merchant merchant = MerchantDB.getMerchant("USERID", merchant_id);
                merchant_gain = merchant.getGain() + cost;
                merchant_supply = merchant.getSupply();

                updateMerchant(merchant_id, -1, merchant_gain,
                        merchant.getPurchases_total() + 1,
                        merchant_gain * merchant_supply);

                
                product_quantity = buying_product.getQuantity() - quantity;
                buying_product.setQuantity(product_quantity);
                ProductDB.updateProduct(buying_product);
                
                BoughtProduct pro = new BoughtProduct();
                pro.setMerchant_id(merchant_id);
                pro.setProduct_id(product_id);
                pro.setTotal(quantity);
                pro.setTransaction_id(transaction.getTransaction_id());
                BoughtProductDB.addBoughtProduct(pro.getProduct_id(), pro.getTransaction_id(),
                        pro.getMerchant_id(), pro.getTotal());
                CompanyTradesDB.addTrade(transaction.getTransaction_id(), merchant_id, mycompany.getUser_id(), employee_id);
                
                double new_balance = credit_balance - cost;
                double new_amount_due = credit_limit - new_balance;
                mycompany.setCredit_balance(new_balance);
                mycompany.setAmount_due(new_amount_due);
                CompanyDB.updateCompany(mycompany);
                
                response.setStatus(200);
                str = gson.toJson(transaction);
                response.getWriter().print(str);
            }else{
                response.setStatus(200);
                response.getWriter().print("Not enought credit balance");
            }
        }
    }
}
