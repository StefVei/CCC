/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.ProductDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.Company;
import hy360.ccc.model.Employee;
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
public class ReturnProducts extends HttpServlet {

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
        
        Transaction transaction = new Transaction();
        String customer_type;
        String merchant_id, citizen_id, company_id, employee_id, product_id, quantity;
        String customer_amount_due;
        double product_quantity, merchant_gain, merchant_supply ;
        
        
        LocalDate date = java.time.LocalDate.now();
        
        merchant_id = request.getParameter("merchantId");
        product_id = request.getParameter("productId");
        quantity = request.getParameter("quantityOfReturningProduct");
        customer_type = request.getParameter("isCitizen");
        
        transaction.setTransaction_type("E");
        transaction.setPending("Y");
        transaction.setDate(date.toString());
        
        Product returning_product = ProductDB.getProduct(product_id, merchant_id);
        product_quantity = Integer.valueOf(returning_product.getQuantity()) + Integer.valueOf(quantity);
        returning_product.setQuantity(String.valueOf(product_quantity));
        
        Merchant merchant = MerchantDB.getMerchant("USERID", merchant_id);
        merchant_gain = Double.valueOf(merchant.getGain())-(Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity));
        merchant_supply = Double.valueOf(merchant.getSupply());
        merchant.setGain(String.valueOf(merchant_gain));
        merchant.setAmount_due(String.valueOf(merchant_gain * merchant_supply));
        MerchantDB.updateMerchant(merchant);
        
        if (customer_type.equals("true")) {
            citizen_id = request.getParameter("citizenId");
            customer_amount_due = CitizenDB.getCitizen("USERID", citizen_id).getAmount_due();
            transaction.setCitizen_id(citizen_id);
            transaction.setMerchant_cit_id(merchant_id);

        } else {
            employee_id = request.getParameter("employeeId");
            company_id = EmployeeDB.getEmployee("EMPLOYEE_ID", employee_id).getCompany_id();
            customer_amount_due = CompanyDB.getCompany("USERID", company_id).getAmount_due();
            transaction.setCompany_id(company_id);
            transaction.setEmployee_id(String.valueOf(employee_id));
            transaction.setMerchant_comp_id(merchant_id);

        }
        
        if(Double.valueOf(customer_amount_due)== 0){
            if (customer_type.equals("true")){
                citizen_id = request.getParameter("citizenId");
                Citizen cit = CitizenDB.getCitizen("USERID", citizen_id);
                double new_balance = Double.valueOf(cit.getCredit_balance()) + (Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity));
                cit.setCredit_balance(String.valueOf(new_balance));
                CitizenDB.updateCitizen(cit);
            }
            else{
                employee_id = request.getParameter("employeeId");
                Employee em = EmployeeDB.getEmployee("EMPLOYEE_ID", employee_id);
                Company comp = CompanyDB.getCompany("USERID", em.getCompany_id());
                double new_balance = Double.valueOf(comp.getCredit_balance());
                new_balance = new_balance + (Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity));
                comp.setCredit_balance(String.valueOf(new_balance));
                CompanyDB.updateCompany(comp);
            }
        }
        else{
            if (customer_type.equals("true")){
                citizen_id = request.getParameter("citizenId");
                Citizen cit = CitizenDB.getCitizen("USERID", citizen_id);
                double new_balance = Double.valueOf(cit.getCredit_balance()) + (Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity));
                cit.setCredit_balance(String.valueOf(new_balance));
                cit.setAmount_due(String.valueOf(Double.valueOf(customer_amount_due)- (Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity))));
                CitizenDB.updateCitizen(cit);
            }
            else{
                employee_id = request.getParameter("employeeId");
                Employee em = EmployeeDB.getEmployee("EMPLOYEE_ID", employee_id);
                Company comp = CompanyDB.getCompany("USERID", em.getCompany_id());
                double new_balance = Double.valueOf(comp.getCredit_balance());
                new_balance = new_balance + (Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity));
                comp.setCredit_balance(String.valueOf(new_balance));
                comp.setAmount_due(String.valueOf(Double.valueOf(customer_amount_due)- (Double.valueOf(returning_product.getPrice()) * Integer.valueOf(quantity))));
                CompanyDB.updateCompany(comp);
            }
        }
    }
}