package com.bah.mcc.authenticator.filter;

import com.bah.mcc.authenticator.util.JWTHelper;
import com.bah.mcc.authenticator.util.JWTUtil;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    JWTUtil jwtUtil = new JWTHelper();

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final String uri = req.getRequestURI();
        if (uri.startsWith("/api/customer")) {
            // continue on to get-token endpoint
            chain.doFilter(request, response);
        } else {
            // check JWT token
            String authheader = req.getHeader("authorization");
            if (authheader != null && authheader.length() > 7
                    && authheader.startsWith("Bearer")) {
                String jwt_token = authheader.substring(7, authheader.length());
                if (jwtUtil.verifyToken(jwt_token)) {
                        chain.doFilter(request, response);
                    }
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
