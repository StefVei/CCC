/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Register;

import com.google.gson.Gson;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.model.Company;
import hy360.ccc.model.Employee;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.UserDB;
import hy360.ccc.model.Merchant;
import hy360.ccc.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tetan
 */
public class Login extends HttpServlet {

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
        
        User user = UserDB.getUser("USERNAME", request.getParameter("username"));

        if(user != null){
            if( !user.getPassword().equals(request.getParameter("password"))){
                response.setStatus(200);
                response.getWriter().print("Wrong Credentials");
            } else {
                response.setStatus(200);
                str = gson.toJson(user);
                response.getWriter().print(str);
            }
        }else{
            response.setStatus(200);
            response.getWriter().print("Wrong Credentials");
        }
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
