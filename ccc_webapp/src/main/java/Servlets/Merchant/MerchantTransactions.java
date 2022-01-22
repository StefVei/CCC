/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Merchant;

import Utils_db.MerchantTransaction;
import Utils_db.UtilitiesDB;
import com.google.gson.Gson;
import hy360.ccc.db.BoughtProductDB;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CitizenTradesDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.CompanyTradesDB;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.db.ProductDB;
import hy360.ccc.db.TransactionDB;
import hy360.ccc.model.BoughtProduct;
import hy360.ccc.model.CM_Trades;
import hy360.ccc.model.CM_Traffics;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.Company;
import hy360.ccc.model.Employee;
import hy360.ccc.model.Product;
import hy360.ccc.model.Transaction;
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
 * @author panagiotisk
 */
public class MerchantTransactions extends HttpServlet {

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
            out.println("<title>Servlet MerchantTransactions</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MerchantTransactions at " + request.getContextPath() + "</h1>");
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
        List<Transaction> tr_list = null;
        List<MerchantTransaction> list = null;
        String user_id = request.getParameter("userId");
        String min_date = request.getParameter("from");
        String max_date = request.getParameter("to");

        if (min_date != null) {
            tr_list = new ArrayList<>();
            tr_list = TransactionDB.getTransactionsByDates(min_date, max_date);
        }

        List<CM_Trades> trades;
        list = new ArrayList<>();
        trades = CitizenTradesDB.getTrades("MERCHANT_USERID", user_id);
        for (CM_Trades trade : trades) {
            Citizen cit = CitizenDB.getCitizen("USERID",
                    trade.getCitizen_id());

            Transaction tr;

            if (tr_list == null) {
                tr = TransactionDB.getTransaction(trade.getTransaction_id());
            } else {
                if (UtilitiesDB.containsId(tr_list, trade.getTransaction_id())) {
                    tr = TransactionDB.getTransaction(trade.getTransaction_id());
                } else {
                    continue;
                }
            }
            BoughtProduct br = BoughtProductDB.getBoughtProduct(Integer.valueOf(tr.getTransaction_id()));

            Product pr = ProductDB.getProduct(br.getProduct_id());
            String citizen_name = cit.getFirst_name() + " " + cit.getLast_name();
            String date = tr.getDate();
            String amount = tr.getAmount();
            String type = tr.getTransaction_type();
            String product_name = pr.getName();
            double quantity = br.getTotal();
            MerchantTransaction mer_t = new MerchantTransaction(
                    product_name, quantity, amount, date, citizen_name, type);
            list.add(mer_t);

        }

        List<CM_Traffics> c_trades;
        c_trades = CompanyTradesDB.getTrades("MERCHANT_USERID", user_id);
        for (CM_Traffics trade : c_trades) {
            Company comp = CompanyDB.getCompany("USERID",
                    trade.getCompany_id());
            Employee employee = EmployeeDB.getEmployee(trade.getEmployee_id());


            Transaction tr;

            if (tr_list == null) {
                tr = TransactionDB.getTransaction(trade.getTransaction_id());
            } else {
                if (UtilitiesDB.containsId(tr_list, trade.getTransaction_id())) {
                    tr = TransactionDB.getTransaction(trade.getTransaction_id());
                } else {
                    continue;
                }
            }
            BoughtProduct br = BoughtProductDB.getBoughtProduct(Integer.valueOf(tr.getTransaction_id()));

            Product pr = ProductDB.getProduct(br.getProduct_id());
            String comp_name = comp.getName();
            String employee_name = employee.getFirst_name() + " " + employee.getLast_name();
            String date = tr.getDate();
            String amount = tr.getAmount();
            String type = tr.getTransaction_type();
            String product_name = pr.getName();
            double quantity = br.getTotal();
            MerchantTransaction mer_t = new MerchantTransaction(employee_name,
                    product_name, quantity, amount, date, comp_name, type);
            list.add(mer_t);

        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        str = gson.toJson(list);
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
