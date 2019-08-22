package com.inloopx.customerevidence.resource;

import com.inloopx.userservice.dto.UserDto;
import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Login and register endpoints for user")
public class AuthResource {

    @EJB
    RestClient restClient;

    @POST
    @Path("/login")
    public Response login(@Valid UserDto userDto) {

        return restClient.callOtherModule("http://localhost:8080/userservice/api/users", "login", userDto);

    }

    @POST
    @Path("/register")
    public Response register(@Valid UserDto userDto) {

        return restClient.callOtherModule("http://localhost:8080/userservice/api/users", "register", userDto);

    }

}