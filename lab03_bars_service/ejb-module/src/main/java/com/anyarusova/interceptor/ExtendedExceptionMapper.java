package com.anyarusova.interceptor;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtendedExceptionMapper implements ExceptionMapper<ExtendedException> {
    @Override
    public Response toResponse(ExtendedException e) {
        log.error("Exception occurred: {}", e.getMessage());

        return Response
                .status(e.getStatus())
                .entity(new Error(e.getMessage(), e.getStatus().getStatusCode()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
