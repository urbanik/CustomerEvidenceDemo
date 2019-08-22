package com.inloopx.customerevidence.exceptionmapper;

import com.inloopx.customerevidence.exception.ErrorResponse;
import com.inloopx.customerevidence.exception.ViolatedBusinessRule;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class ViolatedBusinessRuleExceptionMapper implements ExceptionMapper<ViolatedBusinessRule> {

    private static final String DEFAULT_ERROR_MESSAGE = "Violated business rule.";

    @Inject
    Logger LOG;

    @Override
    public Response toResponse(ViolatedBusinessRule exception) {

        LOG.info(exception.getMessage());

        return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse(DEFAULT_ERROR_MESSAGE, exception.getMessage()))
                .build();
    }

}
