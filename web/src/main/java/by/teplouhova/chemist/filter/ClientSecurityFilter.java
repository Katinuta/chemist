package by.teplouhova.chemist.filter;

import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.teplouhova.chemist.command.PageConstant.PAGE_DOCTOR_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MAIN;


@WebFilter(urlPatterns = {"/client","/jsp/client/*"})
public class ClientSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        User user= (User) session.getAttribute("user");
        RoleType role=user.getRole();
        switch(role){
            case DOCTOR:{
                response.sendRedirect(request.getContextPath()+PAGE_DOCTOR_MAIN);
                return;
            }
            case PHARMACIST:{
                response.sendRedirect(request.getContextPath()+ PAGE_PHARMACIST_MAIN);
                return;
            }
        }


        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
