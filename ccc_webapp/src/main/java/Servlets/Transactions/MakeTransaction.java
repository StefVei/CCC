/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
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

        String citizen_or_employee;
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Transaction transaction = new Transaction();

        LocalDate date = java.time.LocalDate.now();

        citizen_or_employee = request.getParameter("isCustomer");
        if (citizen_or_employee.equals("true")) {
            transaction.setCitizen_id(request.getParameter("citizenId"));
            transaction.setMerchant_cit_id(request.getParameter("merchantId"));
        } else {
            int employee_id = Integer.valueOf(request.getParameter("employeeId"));
            String company_id = EmployeeDB.getEmployee(employee_id).getCompany_id();

            transaction.setCompany_id(company_id);
            transaction.setEmployee_id(String.valueOf(employee_id));
            transaction.setMerchant_comp_id(request.getParameter("merchantId"));

        }
        transaction.setPay_type(request.getParameter("payType"));
        transaction.setTransaction_type(request.getParameter("transactionType"));
        transaction.setDate(request.getParameter("date"));
        transaction.setAmount(request.getParameter("amount"));

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
