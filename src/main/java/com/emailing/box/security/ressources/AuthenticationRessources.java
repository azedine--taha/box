package com.emailing.box.security.ressources;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;


@RestController
public interface AuthenticationRessources {


    @PostMapping (path = "/token")
    public Response getToken(@RequestParam("login") String login,
                             @RequestParam("password") String password) ;
}
