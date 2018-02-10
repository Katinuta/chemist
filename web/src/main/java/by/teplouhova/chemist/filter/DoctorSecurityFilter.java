package by.teplouhova.chemist.filter;

import by.teplouhova.chemist.entity.impl.RoleType;
import by.teplouhova.chemist.entity.impl.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.teplouhova.chemist.command.PageConstant.PAGE_CLIENT_MAIN;
import static by.teplouhova.chemist.command.PageConstant.PAGE_PHARMACIST_MAIN;

/**
 * The Class DoctorSecurityFilter.
 */
@WebFilter(urlPatterns = {"/doctor", "/jsp/doctor/*"})
public class DoctorSecurityFilter implements Filter {
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
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @param filterChain     the filter chain
     * @throws IOException      Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        RoleType role = user.getRole();
        switch (role) {
            case PHARMACIST: {
                response.sendRedirect(request.getContextPath() + PAGE_PHARMACIST_MAIN);
                return;
            }
            case CLIENT: {
                response.sendRedirect(request.getContextPath() + PAGE_CLIENT_MAIN);
                return;
            }
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {

    }
}
