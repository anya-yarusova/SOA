package com.anyarusova.lab02_bars_sevice.interceptor;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtendedExceptionMapper implements ExceptionMapper<ExtendedException> {
    @Override
    public Response toResponse(ExtendedException e) {
        log.warn("Вызвано исключение {}:\n\tstatus={}\n\tmessage={}", e.getExtendedClass(), e.getStatus(), e.getMessage());

        return Response
                .status(e.getStatus())
                .entity(new Error(e.getMessage()))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
