package com.inloopx.customerevidence.exceptionmapper;

import com.inloopx.customerevidence.exception.EntityNotFound;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Optional;
import java.util.logging.Logger;

@Provider
public class EntityNotFoundExceptionMapper implements ExceptionMapper<EntityNotFound> {

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(EntityNotFound exception) {

        LOG.info("Entity not found!");

        String message = "Entity not found!";
        Optional<Class> maybeClass = exception.getClazz();

        if (maybeClass.isPresent()) {
            Class aClass = maybeClass.get();
            message = "Entity " + aClass.getName() + " not exists";
        }

        return Response.status(Response.Status.NOT_FOUND)
                .entity(message)
                .build();
    }

}
