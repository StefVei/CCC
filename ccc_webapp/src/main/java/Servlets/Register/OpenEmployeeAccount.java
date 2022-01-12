/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets.Register;

import com.google.gson.Gson;
import hy360.ccc.db.EmployeeDB;
import hy360.ccc.model.Employee;
import hy360.ccc.model.Employee.Gender;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panagiotisk
 */
@WebServlet(name = "OpenEmployeeAccount", urlPatterns = {"/OpenEmployeeAccount"})
public class OpenEmployeeAccount extends HttpServlet {

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
            out.println("<title>Servlet OpenEmployeeAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OpenEmployeeAccount at " + request.getContextPath() + "</h1>");
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

        Employee employee = new Employee();

        LocalDate date = java.time.LocalDate.now();
        date = date.plusYears(5);

        employee.setAddress(request.getParameter("address"));
        employee.setBirth_date("birthDate");
        employee.setEmail("email");
        employee.setFirst_name("firstName");
        employee.setLast_name("lastName");

        Gender gender = "male".equals(request.getParameter("gender")) ? Gender.MALE
                : "female".equals(request.getParameter("gender")) ? Gender.FEMALE
                : Gender.UNKNOWN;

        employee.setGender(gender);

        EmployeeDB.addEmployee(employee);
        response.setStatus(200);
        str = gson.toJson(employee);
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
