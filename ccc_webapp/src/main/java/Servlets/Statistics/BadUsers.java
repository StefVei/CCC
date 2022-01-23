/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Statistics;

import Utils_db.BadUser;
import com.google.gson.Gson;
import hy360.ccc.db.CitizenDB;
import hy360.ccc.db.CompanyDB;
import hy360.ccc.db.MerchantDB;
import hy360.ccc.model.Citizen;
import hy360.ccc.model.Company;
import hy360.ccc.model.Merchant;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tetan
 */
public class BadUsers extends HttpServlet {

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
            out.println("<title>Servlet BadUsers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BadUsers at " + request.getContextPath() + "</h1>");
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
    
       
        List<BadUser> mylist = new ArrayList<>();
        List<Merchant> merchants = MerchantDB.getBadMerchants();
        List<Company> companies = CompanyDB.getBadCompanies();
        List<Citizen> citizens = CitizenDB.getBadCitizens();

        int i = 0;
        while (i < merchants.size()) {
            Merchant mer111 = merchants.get(i);

            BadUser merr = new BadUser(
                    mer111.getAmount_due(),
                    mer111.getPhone(),
                    mer111.getEmail(),
                    mer111.getFirst_name(),
                    mer111.getLast_name(),
                    "", "Merchant");
            mylist.add(merr);
            i++;
        }

        int j = 0;
        while (j < citizens.size()) {
            Citizen cit = citizens.get(j);

            BadUser citt = new BadUser(
                    cit.getAmount_due(),
                    cit.getPhone(),
                    cit.getEmail(),
                    cit.getFirst_name(),
                    cit.getLast_name(),
                    "",
                "Citizen");
            mylist.add(citt);
            j++;


        }

        int k = 0;

        while (k < companies.size()) {

            Company comp = companies.get(k);
            BadUser compp = new BadUser(
                    comp.getAmount_due(),
                    comp.getPhone(),
                    comp.getEmail(),
                    "",
                "",
                    comp.getName(),
                    "Company");

            mylist.add(compp);
            k++;

        }
        Collections.sort(mylist, (BadUser u1, BadUser u2)
                -> Double.compare(u1.getAmount_due(), u2.getAmount_due()));
        
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        str = gson.toJson(mylist);
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
