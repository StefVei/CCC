/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Register;

import Utils_db.UtilitiesDB;
import com.google.gson.Gson;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.UserDB;
import hy360.ccc.model.Company;
import hy360.ccc.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panos
 */
@WebServlet(name = "OpenCompanyAccount", urlPatterns = {"/OpenCompanyAccount"})
public class OpenCompanyAccount extends HttpServlet {

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
                
        
        try (PrintWriter out = response.getWriter()) {

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

        Company comp = new Company();
        User user = comp;
                
        String acc_number = UtilitiesDB.getNewAccountNumber();
        LocalDate date = java.time.LocalDate.now();
        date = date.plusYears(5);

        comp.setAccount_due_date(date.toString());
        comp.setPhone(request.getParameter("phone"));
        comp.setEmail(request.getParameter("email"));
        comp.setAddress(request.getParameter("address"));
        comp.setName(request.getParameter("name"));
        comp.setAmount_due(0);
        comp.setCredit_balance(5000);
        comp.setCredit_limit(5000);
        comp.setEstablishment_date(date.minusYears(5).toString());
        
        user.setUser_type("C");
        user.setUserName(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setAccount_number(acc_number);

        UserDB.addUser(user);
        CompanyDB.addCompany(comp);

        response.setStatus(200);
        str = gson.toJson(comp);
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
