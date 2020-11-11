package com.emailing.box.security.ressources.impl;

import com.emailing.box.business.User.IUserService;
import com.emailing.box.commons.exception.InvalidCredentialsException;
import com.emailing.box.entities.User;
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

    @Autowired
    IUserService userService;

    @Override
    public Response getToken(String header) throws InvalidCredentialsException {

            LoginHolder loginHolder = new LoginHolder(header);
            //check if user exist in database
            User user = userService.findByUsername(loginHolder.getLogin());

            if(user == null ) {
                System.out.println("------------------null-------"+loginHolder.getLogin());
            }
            if(user!=null && !loginHolder.getPassword().equals(user.getPassword())) {
                throw InvalidCredentialsException.builder().withError("identifiant not correct ....").build();
            }
            Token token = tokenService.getJwtToken(loginHolder.getLogin(), loginHolder.getPassword());

            return Response.status(OK).entity(token).build();
    }
}
