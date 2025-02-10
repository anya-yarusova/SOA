package com.anyarusova.interceptor;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class ExtendedException extends Exception {

    private final Response.Status status;

    public ExtendedException(Response.Status status, String message) {
        super(message);
        this.status = status;
    }
}
