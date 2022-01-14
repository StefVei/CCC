/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Register;


import Utils_db.UtilitiesDB;
import com.google.gson.Gson;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.model.Merchant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panagiotisk
 */
public class OpenMerchantAccount extends HttpServlet {

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
            out.println("<title>Servlet OpenMerchantAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OpenMerchantAccount at " + request.getContextPath() + "</h1>");
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

        Merchant merchant = new Merchant();
        
        String acc_number = UtilitiesDB.getNewAccountNumber();
        
        merchant.setAccount_number(acc_number);
        merchant.setFirst_name(request.getParameter("firstname"));
        merchant.setLast_name(request.getParameter("lastname"));
        merchant.setBirth_date(request.getParameter("birthdate"));
        merchant.setUserName(request.getParameter("username"));
        merchant.setPassword(request.getParameter("password"));
        merchant.setPhone(request.getParameter("phone"));
        merchant.setEmail(request.getParameter("email"));
        merchant.setAddress(request.getParameter("address"));
        merchant.setSupply(request.getParameter("0.15"));
        merchant.setAmount_due("0");
        merchant.setGain(request.getParameter("0"));
        merchant.setPurchases_total("0");
        String gender = "male".equals(request.getParameter("gender")) ? "M"
                : "female".equals(request.getParameter("gender")) ? "F"
                : "O";

        merchant.setGender(gender);
        
        MerchantDB.addMerchant(merchant);
        response.setStatus(200);
        str = gson.toJson(merchant);
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
