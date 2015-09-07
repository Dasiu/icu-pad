package com.icupad.nurse.config;

import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

@Component
public class SimpleCORSFilter implements Filter {
    
    public SimpleCORSFilter() {
        System.err.println("asdf!");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
