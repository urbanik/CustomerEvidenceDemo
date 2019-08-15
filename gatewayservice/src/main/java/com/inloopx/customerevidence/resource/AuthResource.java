package com.inloopx.customerevidence.resource;

import io.swagger.annotations.Api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    public Response login() {
//        restClient.callOtherModule(HttpMethod.POST,body,url,Class.class,queryParams);

        return null;

    }

    @POST
    @Path("/register")
    public Response register() {

    return null;

    }

}
