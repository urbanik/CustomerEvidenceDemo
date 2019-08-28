package com.inloopx.customerevidence;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resource in your application.
 *
 * @author inloopx.com
 */
@LoginConfig(authMethod = "MP-JWT")
@ApplicationPath("/api")
@DeclareRoles({"user", "admin"})
public class JAXRSConfiguration extends Application {
}
