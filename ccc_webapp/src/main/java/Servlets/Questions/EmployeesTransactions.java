/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Questions;

import com.google.gson.Gson;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.CompanyTradesDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.TransactionDB;
import hy360.ccc.model.CM_Traffics;
import hy360.ccc.model.Merchant;
import hy360.ccc.model.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author sckou
 */
public class EmployeesTransactions extends HttpServlet {
    
    
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
            out.println("<title>Servlet EmployeeTransactions</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeTransactions at " + request.getContextPath() + "</h1>");
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

        Map<String, String> map = new HashMap<>();
        int index = 1;
        String employee_list = request.getParameter("employeesList");
        List<CM_Traffics> comp_trades_by_employees = CompanyTradesDB.
                getTradesByEmployees(employee_list);

        for (CM_Traffics trade : comp_trades_by_employees) {
            Transaction tr = TransactionDB.getTransaction(trade.getTransaction_id());
            String company_name = CompanyDB.getCompany("USERID",
                    trade.getCompany_id()).getName();
            Merchant mer = MerchantDB.getMerchant("MERCHANT_USERID",
                    trade.getMerchant_id());
            String merchant_name = mer.getFirst_name() + mer.getLast_name();
            String date = tr.getDate();
            String amount = tr.getAmount();
            String type = tr.getTransaction_type();
            map.put(String.valueOf(index), " { company_name: " + company_name
                    + "merchant_name: " + merchant_name + "date: " + date
                    + "amount: " + amount + "type:" + type + "}");
            index++;

        }

    
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        str = gson.toJson(map);
        response.getWriter().print(str);

    
    }

}