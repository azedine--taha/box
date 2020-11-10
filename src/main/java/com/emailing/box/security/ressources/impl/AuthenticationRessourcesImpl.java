package com.emailing.box.security.ressources.impl;

import com.emailing.box.security.ressources.AuthenticationRessources;
import com.emailing.box.security.token.Token;
import com.emailing.box.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;


@Component
public class AuthenticationRessourcesImpl implements AuthenticationRessources {

    @Autowired
    TokenService tokenService;

    @Override
    public Response getToken(String login, String password) {
        Token token =  tokenService.getJwtToken(login,password);
        return Response.status(OK).entity(token).build();

    }
}
