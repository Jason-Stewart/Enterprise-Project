/*
 author j23-stewart
*/
package Filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.JdbcQry;
import model.Member;


public class LoginFilter implements Filter {
    
    private FilterConfig fc;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc = filterConfig;
    }
    
    
    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hReq = (HttpServletRequest) request;
        HttpServletResponse hResp = (HttpServletResponse) response;
        String username = hReq.getParameter("username");
        String password = hReq.getParameter("password");
                     
        JdbcQry db = new JdbcQry((Connection) request.getServletContext().getAttribute("connection"));
        HttpSession session = hReq.getSession();
        
         try {
            boolean isMember = db.idcheck(username, password);
            if (session.getAttribute("status") == "ADMIN"){
                session.setAttribute("admin-authenticated", true);
                RequestDispatcher view = request.getRequestDispatcher("admin/admin-dashboard.jsp");
                view.forward(hReq, hResp);
            }
            else if (isMember == false) {
                hResp.sendRedirect("login.jsp");
            }
            else {
                session.setAttribute("user-authenticated", true);
                chain.doFilter(request, response);
            }
    } catch (SQLException ex) {
            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void destroy() {
        
    }
    
}
