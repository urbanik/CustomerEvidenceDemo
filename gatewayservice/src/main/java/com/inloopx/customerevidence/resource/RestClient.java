package com.inloopx.customerevidence.resource;


import com.inloopx.userservice.dto.UserDto;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
public class RestClient {

    private Client client;

    public RestClient() {
    }

    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );

    }

    public Response callOtherModule(String url, String path, UserDto userDto){

        WebTarget webTarget = client.target(url).path(path);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);

        return invocationBuilder.post(Entity.entity(userDto, MediaType.APPLICATION_JSON));

    }


}
