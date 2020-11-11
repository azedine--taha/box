package com.emailing.box.filter;


import com.emailing.box.security.token.impl.RequestHeaderValidaor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Component
public class AuthentificationFilter extends GenericFilter implements Filter  {

    private static final Logger  LOG = LoggerFactory.getLogger(AuthentificationFilter.class);

    @Autowired
    private RequestHeaderValidaor validaor ;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

       // SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if(!req.getRequestURI().contains("token")) {

            LOG.info("Header of request  : {}",req.getHeader(HttpHeaders.AUTHORIZATION));
            String jwtToken = req.getHeader(HttpHeaders.AUTHORIZATION);

            if(!this.validaor.isValidToken((jwtToken))) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            filterChain.doFilter(request,response);
        }else  {
            filterChain.doFilter(request,response);

        }

    }
}
