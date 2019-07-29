package com.inloopx.customerevidence;

import org.flywaydb.core.Flyway;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resource in your application.
 *
 * @author inloopx.com
 */
@ApplicationPath("/api")
public class JAXRSConfiguration extends Application {
}
