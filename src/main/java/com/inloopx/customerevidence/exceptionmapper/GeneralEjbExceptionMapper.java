package com.inloopx.customerevidence.exceptionmapper;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GeneralEjbExceptionMapper implements ExceptionMapper<EJBException> {

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(EJBException exception) {

        LOG.log(Level.SEVERE, exception.toString(), exception);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("EJBException error!")
                .build();
    }
}
