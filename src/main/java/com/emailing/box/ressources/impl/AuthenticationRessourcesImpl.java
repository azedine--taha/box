package com.emailing.box.ressources.impl;

import com.emailing.box.business.user.IUserService;
import com.emailing.box.entities.User;
import com.emailing.box.ressources.AuthenticationRessources;
import com.emailing.box.security.token.JwtUtil;
import com.emailing.box.security.token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;


@Service
public class AuthenticationRessourcesImpl implements AuthenticationRessources {

    @Autowired
    JwtUtil tokenService;

    @Autowired
    IUserService userService;

    @Override
    public Response getToken(String authHeader) throws Exception {
        LoginHolder loginHolder = new LoginHolder(authHeader);

        //Check if user exist in database
        User user = checkUserinDb(loginHolder);
        if(user == null){
            throw new Exception (" Invalid credentials ");
        }
        Token token =  tokenService.generateToken(loginHolder.getLogin());
        return Response.status(OK).entity(token).build();

    }

    private User checkUserinDb(LoginHolder loginHolder) {
        User user = userService.findByEmail(loginHolder.getLogin());
        if (user != null && user.getPassword().equals(loginHolder.getPassword())) {
            return user;
        }
        return  null;

    }
}
