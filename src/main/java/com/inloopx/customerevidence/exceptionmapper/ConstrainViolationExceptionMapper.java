package com.inloopx.customerevidence.exceptionmapper;


import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class ConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        String errorMessages = prepareMessage(exception);

        LOG.info(errorMessages);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorMessages)
                .build();
    }

    private String prepareMessage(ConstraintViolationException exception) {

        StringBuilder message = new StringBuilder();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            message.append(cv.getPropertyPath() + " " + cv.getMessage() + "\n");
        }
        return message.toString();
    }
}
