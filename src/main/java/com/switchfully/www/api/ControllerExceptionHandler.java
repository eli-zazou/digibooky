package com.switchfully.www.api;

import com.switchfully.www.exceptions.NotFoundException;
import com.switchfully.www.exceptions.UnauthorizatedException;
import com.switchfully.www.exceptions.WrongPasswordException;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import org.jboss.logging.Logger;

public class ControllerExceptionHandler {

    private static final Logger LOG = Logger.getLogger(ControllerExceptionHandler.class);

    @ServerExceptionMapper(value = {IllegalArgumentException.class, NotFoundException.class})
    protected Response runtimeException(RuntimeException runtimeException) {
        LOG.info(runtimeException.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(runtimeException.getMessage()).build();
    }

    @ServerExceptionMapper(UnauthorizatedException.class)
    protected Response unauthorizatedException(UnauthorizatedException unauthorizatedException) {
        LOG.info(unauthorizatedException.getMessage());
        return Response.status(Response.Status.FORBIDDEN).entity(unauthorizatedException.getMessage()).build();
    }

    @ServerExceptionMapper(WrongPasswordException.class)
    protected Response wrongPasswordException(WrongPasswordException wrongPasswordException) {
        LOG.info(wrongPasswordException.getMessage());
        return Response.status(Response.Status.FORBIDDEN).entity(wrongPasswordException.getMessage()).build();
    }

    @ServerExceptionMapper(NotFoundException.class)
    protected Response notFoundException(NotFoundException notFoundException) {
        LOG.info(notFoundException.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(notFoundException.getMessage()).build();
    }

}
