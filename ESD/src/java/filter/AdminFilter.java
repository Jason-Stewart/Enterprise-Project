/*
 author j23-stewart
*/

package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminFilter implements Filter {
    
    private FilterConfig fc;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.fc = filterConfig;
    }
    
    
    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hReq = (HttpServletRequest) request;
        HttpServletResponse hResp = (HttpServletResponse) response;
        
        HttpSession session = hReq.getSession(false);
        
        if (session.getAttribute("status") != "ADMIN") {
            hResp.sendRedirect("login.jsp");
        }
        else {
            chain.doFilter(request, response); 
        }
    }
    
    
    @Override
    public void destroy() {
        
    }
    
}
