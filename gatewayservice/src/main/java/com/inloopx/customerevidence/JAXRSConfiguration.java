package com.inloopx.customerevidence;

import com.inloopx.customerevidence.resource.*;
import org.eclipse.microprofile.auth.LoginConfig;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resource in your application.
 *
 * @author inloopx.com
 */
@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/api")
@DeclareRoles({"user", "admin"})
public class JAXRSConfiguration extends ResourceConfig {

    public JAXRSConfiguration() {
        packages("com.inloopx.customerevidence").register(MultiPartFeature.class);
        packages("com.inloopx.customerevidence").register(CustomerResource.class);
        packages("com.inloopx.customerevidence").register(OrderResource.class);
        packages("com.inloopx.customerevidence").register(PhotoResource.class);
        packages("com.inloopx.customerevidence").register(ProductResource.class);
    }



}
