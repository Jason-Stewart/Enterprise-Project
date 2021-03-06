/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Claim;
import model.JdbcQry;
/**
 *
 * @author wl2-lam
 */
public class Claim_servlet extends HttpServlet {

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
            float amount = Float.parseFloat(request.getParameter("amount"));        
            String reason = request.getParameter("reason");        
            login l = new login();        
            String memID= l.getUsername();                
                Claim c = new Claim();        
            c.setAmount(amount);        
            c.setRationale(reason);        
            c.setMemID(memID);        
            JdbcQry j = new JdbcQry((Connection) request.getServletContext().getAttribute("connection"));
            
            if ("".equals(reason))       
            { PrintWriter out = response.getWriter();            
                out.print("No reason given for claim.");             
                    out.println("<input type=\"button\" name =go back onclick=\"document.location.href = 'NewClaim.jsp'\" />");       
            }else if(amount <= 0)       
            { PrintWriter out = response.getWriter();            
                out.print("Invalid number.");             
                     out.println("<input type=\"button\" name =go back onclick=\"document.location.href = 'NewClaim.jsp'\" />");                  
            }else{                          
                j.submitClaim(c);                
                    response.sendRedirect("home.jsp");
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
        processRequest(request, response);
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
