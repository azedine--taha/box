package com.emailing.box.ressources.user;


import com.emailing.box.business.user.IUserService;
import com.emailing.box.commons.aspect.BusinessLoggable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@RestController
@Api( tags = "Users")
public class UserRessources {

    @Autowired
    IUserService userService;



    @ApiOperation(value = "This method is used to get the users.")
    @GetMapping(value = "/users")
    @BusinessLoggable
    public Response getUsers(){
        return Response.status(Status.OK).entity(userService.getAll()).build();
    }
}
