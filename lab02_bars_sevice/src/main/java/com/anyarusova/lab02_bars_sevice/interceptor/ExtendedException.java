package com.anyarusova.lab02_bars_sevice.interceptor;

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
