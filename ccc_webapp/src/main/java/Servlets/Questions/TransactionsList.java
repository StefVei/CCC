/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Questions;

import com.google.gson.Gson;
import hy360.ccc.db.CitizenTradesDB;
import hy360.ccc.db.CompanyTradesDB;
import hy360.ccc.db.TransactionDB;
import hy360.ccc.db.UserDB;
import hy360.ccc.model.CM_Trades;
import hy360.ccc.model.CM_Traffics;
import hy360.ccc.model.Transaction;
import hy360.ccc.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author sckou
 */
public class TransactionsList extends HttpServlet {

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
            out.println("<title>Servlet TransactionsList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TransactionsList at " + request.getContextPath() + "</h1>");
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
    
        String start = request.getParameter("From");
        String end = request.getParameter("To");
        User myuser = UserDB.getUser("USERID", request.getParameter("userId"));
        List<Transaction> tr_baseDates = TransactionDB.getTransactionsByDates(start, end);       
        if(myuser.getUser_type().equals("I"))
        {
            List<CM_Trades> trade_baseUser = CitizenTradesDB.getTrades("CITIZEN_USERID",myuser.getUser_id());
        }
        else if(myuser.getUser_type().equals("M"))
        {
            List<CM_Trades> trade_baseUser = CitizenTradesDB.getTrades("MERCHANT_USERID",myuser.getUser_id());
            List<CM_Traffics> traffic_baseUser = CompanyTradesDB.getTrades("MERCHANT_USERID",myuser.getUser_id());
        }
        else if(myuser.getUser_type().equals("C"))
        {
            List<CM_Traffics> traffic_baseUser = CompanyTradesDB.getTrades("COMPANY_USERID",myuser.getUser_id());
        }
    
    }
}