package com.inloopx.customerevidence.resource;


import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class RestClient<E> {

    private Client client;

    @PostConstruct
    public void init(){
        ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );

    }

    public E callOtherModule(){

        WebTarget webTarget = client.target("http://localhost:8080/JerseyDemos/rest").path("employees");

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);

        Response response = invocationBuilder.post(Entity.entity(getClass(), MediaType.APPLICATION_JSON));

//        System.out.println(response.getStatus());
//        return response.readEntity(Token.class);
        return null;

    }


}
