/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.TransactionDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.Company;
import hy360.ccc.model.Employee;
import hy360.ccc.model.Merchant;
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

        double balance, cost, limit;
        String credit_balance, products_cost, credit_limit;
        String citizen_or_employee, isPending;
        String merchant_id, citizen_id, employee_id;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Transaction transaction = new Transaction();

        LocalDate date = java.time.LocalDate.now();

        merchant_id = request.getParameter("merchantId");
        products_cost = request.getParameter("amount");

        citizen_or_employee = request.getParameter("isCitizen");
        if (citizen_or_employee.equals("true")) {
            citizen_id = request.getParameter("citizenId");
            credit_balance = CitizenDB.getCitizen("USERID", citizen_id).getCredit_balance();
            credit_limit = CitizenDB.getCitizen("USERID", citizen_id).getCredit_limit();
            transaction.setCitizen_id(citizen_id);
            transaction.setMerchant_cit_id(merchant_id);

        } else {
            employee_id = request.getParameter("employeeId");
            String company_id = EmployeeDB.getEmployee("EMPLOYEE_ID", employee_id).getCompany_id();
            credit_balance = CompanyDB.getCompany("USERID", company_id).getCredit_balance();
            credit_limit = CompanyDB.getCompany("USERID", company_id).getCredit_limit();
            transaction.setCompany_id(company_id);
            transaction.setEmployee_id(String.valueOf(employee_id));
            transaction.setMerchant_comp_id(merchant_id);

        }

        balance = Integer.valueOf(credit_balance);
        limit = Integer.valueOf(credit_limit);
        cost = Integer.valueOf(products_cost);
        if (balance > cost) {
            transaction.setPending("N");
            if (citizen_or_employee.equals("true")) { // CITIZEN
                citizen_id = request.getParameter("citizenId");
                Citizen cit = CitizenDB.getCitizen("USERID", citizen_id);
                double new_balance = Double.valueOf(cit.getCredit_balance()) - cost;
                cit.setCredit_balance(String.valueOf(new_balance));
                CitizenDB.updateCitizen(cit);
            } else {
                employee_id = request.getParameter("employeeId");
                Employee em = EmployeeDB.getEmployee("EMPLOYEE_ID", employee_id);
                Company comp = CompanyDB.getCompany("USERID", em.getCompany_id());
                double new_balance = Double.valueOf(comp.getCredit_balance());
                new_balance = new_balance - cost;
                comp.setCredit_balance(String.valueOf(new_balance));
                CompanyDB.updateCompany(comp);

            }

            Merchant mer = MerchantDB.getMerchant("USERID", merchant_id);
            double new_total = Double.valueOf(mer.getPurchases_total()) + cost;
            mer.setPurchases_total(String.valueOf(new_total));
            MerchantDB.updateMerchant(mer);

                // UPdate credit_Balance on citizen or employye->company
        } else if (balance < cost) {
            transaction.setPending("Y");
            if (citizen_or_employee.equals("true")) { // CITIZEN
                citizen_id = request.getParameter("citizenId");
                Citizen cit = CitizenDB.getCitizen("USERID", citizen_id);
                double new_amountDue = Double.valueOf(cit.getAmount_due());
                new_amountDue += cost;
                cit.setAmount_due(String.valueOf(new_amountDue));
                CitizenDB.updateCitizen(cit);
            } else {                                // EMPLOYEE
                employee_id = request.getParameter("employeeId");
                Employee em = EmployeeDB.getEmployee("EMPLOYEE_ID", employee_id);
                Company comp = CompanyDB.getCompany("USERID", em.getCompany_id());
                double new_amountDue = Double.valueOf(comp.getAmount_due());
                new_amountDue += cost;
                comp.setAmount_due(String.valueOf(new_amountDue));
                CompanyDB.updateCompany(comp);
            }
        } else if (1 == 2) { // ANYTHING ELSE HERE ???? TODO
                // ANYTHING ELSE
        }


        transaction.setDate(date.toString());
        transaction.setAmount(products_cost);

        TransactionDB.addTransaction(transaction);
        response.setStatus(200);
        str = gson.toJson(transaction);
        response.getWriter().print(str);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
