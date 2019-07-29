package com.inloopx.customerevidence.exceptionmapper;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(Exception exception) {

        LOG.log(Level.SEVERE, exception.toString(), exception);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Internal server error!")
                .build();
    }
}
