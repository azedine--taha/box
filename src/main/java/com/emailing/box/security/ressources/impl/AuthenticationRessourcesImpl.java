package com.emailing.box.security.ressources.impl;

import com.emailing.box.business.User.IUserService;
import com.emailing.box.entities.User;
import com.emailing.box.security.ressources.AuthenticationRessources;
import com.emailing.box.security.token.Token;
import com.emailing.box.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;


@Service
public class AuthenticationRessourcesImpl implements AuthenticationRessources {

    @Autowired
    TokenService tokenService;

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
        Token token =  tokenService.getJwtToken(loginHolder.getLogin(),user.getRoles().stream().findFirst().get().getRoleName());
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
