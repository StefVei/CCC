/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servlets.Transactions;

import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.MerchantDB;
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
        String pending = request.getParameter("paymentAmount");
        String user = request.getParameter("userType");
        String myamount_due, mycredit_balance;
        double newcb, newamount;
        
        if (user.equals("I")) {
            citizen_id = request.getParameter("citizenId");
            Citizen cit = CitizenDB.getCitizen("USERID", citizen_id);
            myamount_due = CitizenDB.getCitizen("USERID", citizen_id).getAmount_due();
            mycredit_balance = CitizenDB.getCitizen("USERID", citizen_id).getCredit_balance();
            if(Double.valueOf(pending)>Double.valueOf(myamount_due)){
                newcb = Double.valueOf(mycredit_balance)+(Double.valueOf(pending)-Double.valueOf(myamount_due));
                cit.setCredit_balance(String.valueOf(newcb));
                cit.setAmount_due("0");
            }
            else if (Integer.valueOf(pending)<Integer.valueOf(myamount_due)){
                newcb = Double.valueOf(mycredit_balance)+Double.valueOf(pending);
                cit.setCredit_balance(String.valueOf(newcb));
                newamount = Double.valueOf(myamount_due)-Double.valueOf(pending);
                cit.setAmount_due(String.valueOf(newamount));
            }
            else{
                newcb = Double.valueOf(mycredit_balance)+Double.valueOf(pending);
                cit.setCredit_balance(String.valueOf(newcb));
                cit.setAmount_due("0");
            }
            CitizenDB.updateCitizen(cit);
        }	
	else if (user.equals("C")){
            company_id = request.getParameter("companyId");
            Company comp = CompanyDB.getCompany("USERID", company_id);
            myamount_due = CompanyDB.getCompany("USERID", company_id).getAmount_due();
            mycredit_balance = CompanyDB.getCompany("USERID", company_id).getCredit_balance();
            if(Double.valueOf(pending)>Double.valueOf(myamount_due)){
                newcb = Double.valueOf(mycredit_balance)+(Double.valueOf(pending)-Double.valueOf(myamount_due));
                comp.setCredit_balance(String.valueOf(newcb));
                comp.setAmount_due("0");
            }
            else if (Integer.valueOf(pending)<Integer.valueOf(myamount_due)){
                newcb = Double.valueOf(mycredit_balance)+Double.valueOf(pending);
                comp.setCredit_balance(String.valueOf(newcb));
                newamount = Double.valueOf(myamount_due)-Double.valueOf(pending);
                comp.setAmount_due(String.valueOf(newamount));
            }
            else{
                newcb = Double.valueOf(mycredit_balance)+Double.valueOf(pending);
                comp.setCredit_balance(String.valueOf(newcb));
                comp.setAmount_due("0");
            }
            CompanyDB.updateCompany(comp);
        }
        else if (user.equals("M")){
            merchant_id = request.getParameter("merchantId");
            Merchant mer = MerchantDB.getMerchant("USERID", merchant_id);
            myamount_due = MerchantDB.getMerchant("USERID", merchant_id).getAmount_due();
            if(Double.valueOf(pending)>Double.valueOf(myamount_due)){
                //error message
            }
            else if (Integer.valueOf(pending)<Integer.valueOf(myamount_due)){
                
                newamount = Double.valueOf(myamount_due)-Double.valueOf(pending);
                mer.setAmount_due(String.valueOf(newamount));
            }
            else{
                mer.setAmount_due("0");
            }
            MerchantDB.updateMerchant(mer);
	}
        
    }
}