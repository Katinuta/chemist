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

//@WebFilter(urlPatterns = {"/jsp/*"},
@WebFilter(urlPatterns = {"/index.jsp","/jsp/common/*","/user"})
public class PageRedirectSecurityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
//        if(session==null||session.getAttribute("user")==null){
       if(session!=null){
//            response.sendRedirect(request.getContextPath() + indexPath);
//            return;
//        }else{
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

    @Override
    public void destroy() {

    }
}
