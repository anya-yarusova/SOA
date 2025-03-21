package com.anyarusova.interceptor;

import com.anyarusova.dto.Response;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Provider
public class ExtendedExceptionMapper implements ExceptionMapper<ExtendedException> {
    @Override
    public jakarta.ws.rs.core.Response toResponse(ExtendedException e) {
        log.error("Exception occurred: {}", e.getMessage());

        com.anyarusova.dto.Response customResponse = new Response(
                e.getStatus().getStatusCode(),
                e.getMessage()
        );

        return jakarta.ws.rs.core.Response
                .status(e.getStatus())
                .entity(customResponse)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
