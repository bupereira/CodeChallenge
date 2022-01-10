package com.wit.rest.service;




import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class MDCFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String requestId = UUID.randomUUID().toString();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("UNIQUE_ID", requestId);

        try {
            MDC.put("UNIQUE_ID", requestId);

            filterChain.doFilter(servletRequest, servletResponse);
        }
        finally {
            MDC.remove("UNIQUE_ID");
        }
    }

    @Override
    public void destroy() {

    }
}