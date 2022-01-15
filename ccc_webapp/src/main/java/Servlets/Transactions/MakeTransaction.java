/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.db.TransactionDB;
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

        int balance, cost, limit;
        String credit_balance, products_cost, credit_limit, transaction_type;
        String citizen_or_employee;
        String merchant_id;
        String isPending;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Transaction transaction = new Transaction();

        LocalDate date = java.time.LocalDate.now();

        merchant_id = request.getParameter("merchantId");
        products_cost = request.getParameter("amount");
        transaction_type = request.getParameter("transactionType");

        citizen_or_employee = request.getParameter("isCitizen");
        if (citizen_or_employee.equals("true")) {
            String citizen_id = request.getParameter("citizenId");
            credit_balance = CitizenDB.getCitizen(Integer.valueOf(citizen_id)).getCredit_balance();
            credit_limit = CitizenDB.getCitizen(Integer.valueOf(citizen_id)).getCredit_limit();
            transaction.setCitizen_id(citizen_id);
            transaction.setMerchant_cit_id(merchant_id);

        } else {
            int employee_id = Integer.valueOf(request.getParameter("employeeId"));
            String company_id = EmployeeDB.getEmployee(employee_id).getCompany_id();
            credit_balance = CompanyDB.getCompany("USERID", company_id).getCredit_balance();
            credit_limit = CompanyDB.getCompany("USERID", company_id).getCredit_limit();
            transaction.setCompany_id(company_id);
            transaction.setEmployee_id(String.valueOf(employee_id));
            transaction.setMerchant_comp_id(merchant_id);

        }

        balance = Integer.valueOf(credit_balance);
        limit = Integer.valueOf(credit_limit);
        cost = Integer.valueOf(products_cost);
        if (transaction_type.equals("AGORA")) {
            if (balance > cost) {
                transaction.setPending("F");
                // UPDATE MERCHANT
            } else if (balance < cost) {
                transaction.setPending("T");
                // UPDATE AMOUNT_DUE ON CITIZEN
            } else if (1 == 2) { // ANYTHING ELSE HERE ???? TODO
                // ANYTHING ELSE
            }

        } else if (transaction_type.equals("EPISTROFI")) {

            transaction.setTransaction_type("A");
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
