package st.gov.financas.impostosApi.cors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import st.gov.financas.impostosApi.config.property.ImpostosApiProperty;

/**
 *
 * @author Impostos
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

    @Autowired
    private ImpostosApiProperty impostosApiProperty; 

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        response.setHeader("Access-Control-Allow-Origin", impostosApiProperty.getOrigenPermitida());
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(request.getMethod()) && impostosApiProperty.getOrigenPermitida().equals(request.getHeader("Origin"))) {
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
            response.setHeader("Access-Control-Max-Age", "3600");

            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
