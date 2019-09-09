package com.inloopx.userservice;

import com.inloopx.userservice.resource.UserResource;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resource in your application.
 *
 * @author inloopx.com
 */
@ApplicationPath("/api")
public class JAXRSConfiguration extends Application {


    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> superClasses = super.getClasses();

        Set<Class<?>> classes = new HashSet<>();
        classes.add(JacksonFeature.class);

        classes.add(UserResource.class);

        return classes;
    }
}
