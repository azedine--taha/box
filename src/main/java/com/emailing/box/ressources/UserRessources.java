package com.emailing.box.ressources;


import com.emailing.box.business.User.IUserService;
import com.emailing.box.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@RestController
public class UserRessources {

    @Autowired
    IUserService userService;

    @GetMapping(value = "/users")
    public Response getUsers(){
        return Response.status(Status.OK).entity(userService.getAll()).build();
    }
}
