package com.emailing.box.filter;


import com.emailing.box.commons.context.UserContext;
import com.emailing.box.security.CustomUserDetailsService;
import com.emailing.box.security.token.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class AuthentificationFilter extends OncePerRequestFilter {
    private static final Logger  LOG = LoggerFactory.getLogger(AuthentificationFilter.class);

    private JwtUtil jwtUtil ;


    private CustomUserDetailsService customUserDetailsService;

    public AuthentificationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("Header of request  : {}",request.getHeader(HttpHeaders.AUTHORIZATION));
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        String requestURI = request.getRequestURI();
        if (requestURI.contains("swagger")) {
            // Ne pas faire le test d'authentification quand on est sur l'interface d'accueil de SWAGGER
        } else if (request != null && "OPTIONS".equals(request.getMethod()) ){
            // Laisser passer les appels de type OPTIONS
            response.setStatus(HttpServletResponse.SC_OK);
        } else if(!request.getRequestURI().contains("token")) {

            String username = "azedinetaha@gmail.com";
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);


            if(!this.jwtUtil.validateToken(jwtToken,username)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,userDetails.getPassword(),userDetails.getAuthorities()
            );
            // authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);

            UserContext.setUserLogin(username);
        }
        filterChain.doFilter(request,response);
    }
}
