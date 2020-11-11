package com.emailing.box.security.ressources;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;


@RestController
public interface AuthenticationRessources {


    @PostMapping (path = "/token")
    public Response getToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) throws Exception;
}
