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
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
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
        List<Merchant> mers = MerchantDB.getBadMerchants();
        List<Company> cos = CompanyDB.getBadCompanies();
        List<Citizen> cits = CitizenDB.getBadCitizens();
        ListIterator<Merchant> mer = mers.listIterator();
        
        System.out.println("Merchants:"+mer);
        while(mer.hasNext()){
            BadUser m = new BadUser( 
                mer.next().getAmount_due(),
                mer.next().getPhone(), 
                mer.next().getEmail(),
                mer.next().getFirst_name(),
                mer.next().getLast_name(),
                "",
                "Merchant");
            mylist.add(m);
        }
        ListIterator<Citizen> ci = cits.listIterator();
        while(ci.hasNext()){
            BadUser m = new BadUser( 
                ci.next().getAmount_due(),
                ci.next().getPhone(), 
                ci.next().getEmail(),
                ci.next().getFirst_name(),
                ci.next().getLast_name(),
                "",
                "Citizen");
                        mylist.add(m);

        }
        ListIterator<Company> coy = cos.listIterator();
        while(coy.hasNext()){
            BadUser m = new BadUser( 
                coy.next().getAmount_due(),
                coy.next().getPhone(), 
                coy.next().getEmail(),
                "",
                "",
                coy.next().getName(),
                "Company");
                        mylist.add(m);

        }
        Collections.sort(mylist, new Comparator<BadUser>() {
            @Override
            public int compare(BadUser u1, BadUser u2) {
                return Double.compare(u1.getAmount_due(), u2.getAmount_due());
            }
        });
        
        
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
