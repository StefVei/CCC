/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Register;

import Utils_db.UtilitiesDB;
import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.UserDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.User;
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
public class OpenCitizenAccount extends HttpServlet {

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
            out.println("<title>Servlet OpenCitizenAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OpenCitizenAccount at " + request.getContextPath() + "</h1>");
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

        Citizen cit = new Citizen();
        User user = new User();

        LocalDate date = java.time.LocalDate.now();
        date = date.plusYears(5);
        String acc_number = UtilitiesDB.getNewAccountNumber();

        cit.setAccount_number(acc_number);
        cit.setAccount_due_date(date.toString());
        cit.setUserName(request.getParameter("username"));
        cit.setPassword(request.getParameter("password"));
        cit.setPhone(request.getParameter("phone"));
        cit.setEmail(request.getParameter("email"));
        cit.setAddress(request.getParameter("address"));
        cit.setFirst_name(request.getParameter("firstname"));
        cit.setAmount_due("0");
        cit.setCredit_balance(request.getParameter("creditBalance"));
        cit.setCredit_limit("1000");
        cit.setLast_name(request.getParameter("lastname"));
        cit.setAmka(request.getParameter("amka"));
        cit.setVat(request.getParameter("vat"));
        cit.setBirth_date(request.getParameter("birthDate"));

        String gender = "male".equals(request.getParameter("gender")) ? "M"
                : "female".equals(request.getParameter("gender")) ? "F"
                : "O";

        cit.setGender(gender);

        user = (User) cit;
        user.setUser_type("I");

        UserDB.addUser(user);
        CitizenDB.addCitizen(cit);

        response.setStatus(200);
        str = gson.toJson(cit);
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
