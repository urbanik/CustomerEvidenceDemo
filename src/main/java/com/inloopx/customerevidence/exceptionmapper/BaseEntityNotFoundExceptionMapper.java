package com.inloopx.customerevidence.exceptionmapper;

import com.inloopx.customerevidence.exception.EntityNotFound;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Logger;

public class BaseEntityNotFoundExceptionMapper<E> implements ExceptionMapper<EntityNotFound> {

    @Inject
    Logger LOG;

    private Class<E> clazz;

    public BaseEntityNotFoundExceptionMapper(Class<E> clazz) {
        this.clazz = clazz;
    }

    public BaseEntityNotFoundExceptionMapper() {
    }

    @Override
    public Response toResponse(EntityNotFound exception) {

        LOG.info(clazz.getName() + " not found!");

        return Response.status(Response.Status.NOT_FOUND)
                .entity(clazz.getName() + " not found!")
                .build();
    }

}
