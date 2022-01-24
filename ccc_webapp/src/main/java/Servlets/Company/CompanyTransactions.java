/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Company;

import Utils_db.CompanyTransaction;
import Utils_db.UtilitiesDB;
import com.google.gson.Gson;
import hy360.ccc.db.BoughtProductDB;
import hy360.ccc.db.CompanyTradesDB;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.ProductDB;
import hy360.ccc.db.TransactionDB;
import hy360.ccc.model.BoughtProduct;
import hy360.ccc.model.CM_Traffics;
import hy360.ccc.model.Employee;
import hy360.ccc.model.Merchant;
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
public class CompanyTransactions extends HttpServlet {

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
            out.println("<title>Servlet CompanyTransactions</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CompanyTransactions at " + request.getContextPath() + "</h1>");
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
        List<CompanyTransaction> list = new ArrayList<>();
        String company_id = request.getParameter("userId");
        String min_date = request.getParameter("fromDate");
        String max_date = request.getParameter("toDate");
        String min_amount = request.getParameter("fromAmount");
        String max_amount = request.getParameter("toAmount");
        String employee_list = request.getParameter("employeesList");


        if (min_date != null && !min_date.equals("null")
                && max_date != null && !max_date.equals("null")) {
            tr_list = TransactionDB.getTransactionsByDates(min_date, max_date);
        }
        int kk = 0;

        List<CM_Traffics> traffics = CompanyTradesDB.getTrades("COMPANY_USERID", company_id);

        for (CM_Traffics trade : traffics) {
            Transaction tr;
            kk++;
            if (tr_list == null) {
                tr = TransactionDB.getTransaction(trade.getTransaction_id());
                System.out.println("Including " + kk);
            } else {
                if (UtilitiesDB.containsId(tr_list, trade.getTransaction_id())) {
                    tr = TransactionDB.getTransaction(trade.getTransaction_id());
                    System.out.println("Including "
                            + kk);
                } else {
                    System.out.println("Exluding" + kk);
                    continue;
                }

            }

            if (employee_list != null && !employee_list.equals("null")) {
                if (!employee_list.contains(trade.getEmployee_id())) {
                    System.out.println("Exlcuding employee" + kk);
                    continue;
                }
            }
            String amount = tr.getAmount();

            if (min_amount != null && !min_amount.equals("null")
                    && max_amount != null && !max_amount.equals("null")) {

                double am = Double.valueOf(amount);
                double min = Double.valueOf(min_amount);
                double max = Double.valueOf(max_amount);

                if (am < min || am > max) {
                    System.out.println("Exlcuding amount " + kk);
                    continue;
                }

            }

            Merchant mer = MerchantDB.getMerchant("USERID",
                    trade.getMerchant_id());
            BoughtProduct br = BoughtProductDB.getBoughtProduct(Integer.valueOf(tr.getTransaction_id()));
            Product pr = ProductDB.getProduct(br.getProduct_id());
            Employee em = EmployeeDB.getEmployee(trade.getEmployee_id());
            String employee_name = em.getFirst_name() + " " + em.getLast_name();
            String merchant_name = mer.getFirst_name() + " " + mer.getLast_name();
            String date = tr.getDate();
            String type = tr.getTransaction_type();
            String product_name = pr.getName();
            double quantity = br.getTotal();
            CompanyTransaction comp_tr = new CompanyTransaction(merchant_name,
                    date, (int) quantity, Double.valueOf(amount), product_name, employee_name, type);
            comp_tr.setTransaction_id(tr.getTransaction_id());
            list.add(comp_tr);

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
