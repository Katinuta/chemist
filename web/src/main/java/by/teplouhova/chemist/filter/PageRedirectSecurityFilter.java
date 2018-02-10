package by.teplouhova.chemist.filter;

import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.teplouhova.chemist.command.PageConstant.*;

/**
 * The Class PageRedirectSecurityFilter.
 */
@WebFilter(urlPatterns = {"/index.jsp","/jsp/common/*","/user"})
public class PageRedirectSecurityFilter implements Filter {

    /**
     * Inits the.
     *
     * @param filterConfig the filter config
     * @throws ServletException the servlet exception
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }


    /**
     * Do filter.
     *
     * @param servletRequest the servlet request
     * @param servletResponse the servlet response
     * @param filterChain the filter chain
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
       if(session!=null){
           User user= (User) session.getAttribute("user");
           if(session.getAttribute("user")!=null){
               String page=null;
               RoleType role= user.getRole();
               switch (role){
                   case DOCTOR:{
                       page=PAGE_DOCTOR_MAIN;
                       break;
                   }
                   case CLIENT:{
                       page=PAGE_CLIENT_MAIN;
                       break;
                   }
                   case PHARMACIST:{
                       page=PAGE_PHARMACIST_MAIN;
                       break;
                   }
               }
               response.sendRedirect(request.getContextPath() + page);
               return;
           }

       }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {

    }
}
