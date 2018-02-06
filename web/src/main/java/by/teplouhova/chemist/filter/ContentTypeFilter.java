package by.teplouhova.chemist.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;


@WebFilter(urlPatterns = {"/ajax"},
        initParams = {
                @WebInitParam(name = "contentType", value = "application/json")
        })
public class ContentTypeFilter implements Filter {
    private String contentType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        contentType = filterConfig.getInitParameter("contentType");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String contentType = response.getContentType();
        if (contentType!= null && !contentType.equalsIgnoreCase(contentType)) {
            response.setContentType(contentType);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        contentType = null;
    }
}
