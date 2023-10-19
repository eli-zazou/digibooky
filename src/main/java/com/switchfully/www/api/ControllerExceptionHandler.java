package com.switchfully.www.api;

import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import org.jboss.logging.Logger;

public class ControllerExceptionHandler {

    private static final Logger LOG = Logger.getLogger(ControllerExceptionHandler.class);

    @ServerExceptionMapper(IllegalArgumentException.class)
    protected Response illegalArgumentException(IllegalArgumentException illegalArgumentException) {
        LOG.info(illegalArgumentException.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(illegalArgumentException.getMessage()).build();
    }

}
