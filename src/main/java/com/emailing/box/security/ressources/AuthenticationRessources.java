package com.emailing.box.security.ressources;


import com.emailing.box.commons.exception.InvalidCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


@RestController
public interface AuthenticationRessources {


    @GetMapping(path = "/token")
    public Response getToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) throws InvalidCredentialsException;
}
