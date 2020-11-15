package com.emailing.box.ressources;


import com.emailing.box.business.User.IUserService;
import com.emailing.box.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@RestController
@Api( tags = "Users")
public class UserRessources {

    @Autowired
    IUserService userService;



    @ApiOperation(value = "This method is used to get the users.")
    @GetMapping(value = "/users")
    public Response getUsers(){
        return Response.status(Status.OK).entity(userService.getAll()).build();
    }
}
