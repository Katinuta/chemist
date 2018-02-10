package by.teplouhova.chemist.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * The Class ContentTypeFilter.
 */
@WebFilter(urlPatterns = {"/ajax"},
        initParams = {
                @WebInitParam(name = "contentType", value = "application/json")
        })
public class ContentTypeFilter implements Filter {

    /**
     * The content type.
     */
    private String contentType;

    /**
     * Inits the.
     *
     * @param filterConfig the filter config
     * @throws ServletException the servlet exception
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contentType = filterConfig.getInitParameter("contentType");

    }

    /**
     * Do filter.
     *
     * @param request  the request
     * @param response the response
     * @param chain    the chain
     * @throws IOException      Signals that an I/O exception has occurred.
     * @throws ServletException the servlet exception
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String contentType = response.getContentType();
        if (contentType != null && !contentType.equalsIgnoreCase(contentType)) {
            response.setContentType(contentType);
        }
        chain.doFilter(request, response);
    }

    /**
     * Destroy.
     */
    @Override
    public void destroy() {
        contentType = null;
    }
}
