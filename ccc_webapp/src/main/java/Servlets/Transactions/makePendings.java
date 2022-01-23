/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.db.UserDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.Company;
import hy360.ccc.model.Merchant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sckou
 */
public class makePendings extends HttpServlet {

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
            out.println("<title>Servlet makePendings</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet makePendings at " + request.getContextPath() + "</h1>");
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
        
        
        String company_id, citizen_id, merchant_id;
        String userId = request.getParameter("userId");
        String user_type = UserDB.getUser("USERID", userId).getUser_type();

        double myamount_due, mycredit_balance;
        double newcb, newamount;
        double pmntAmount = Double.valueOf(request.getParameter("paymentAmount"));

        if (user_type.equals("I")) {

            Citizen cit = CitizenDB.getCitizen("USERID", userId);
            myamount_due = cit.getAmount_due();
            mycredit_balance = CitizenDB.getCitizen("USERID", userId).getCredit_balance();

            newcb = mycredit_balance + pmntAmount;
            cit.setCredit_balance(newcb);

            newamount = myamount_due - pmntAmount;
            cit.setAmount_due(newamount);
            CitizenDB.updateCitizen(cit);

        }	
        else if (user_type.equals("C")) {
            Company comp = CompanyDB.getCompany("USERID", userId);
            myamount_due = CompanyDB.getCompany("USERID", userId).getAmount_due();
            mycredit_balance = CompanyDB.getCompany("USERID", userId).getCredit_balance();

            newcb = mycredit_balance + pmntAmount;

            comp.setCredit_balance(newcb);
            newamount = myamount_due - pmntAmount;
            comp.setAmount_due(newamount);

            CompanyDB.updateCompany(comp);
        }
        else if (user_type.equals("M")) {
            Merchant mer = MerchantDB.getMerchant("USERID", userId);
            myamount_due = MerchantDB.getMerchant("USERID", userId).getAmount_due();
            if (Double.valueOf(pmntAmount) > Double.valueOf(myamount_due)) {
                //error message
            }
            else if (pmntAmount < myamount_due) {
                
                newamount = myamount_due - pmntAmount;
                mer.setAmount_due(newamount);
            }
            else{
                mer.setAmount_due(0);
            }
            MerchantDB.updateMerchant(mer);
	}
        
    }
}