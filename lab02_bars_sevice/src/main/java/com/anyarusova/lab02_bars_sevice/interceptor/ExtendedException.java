package com.anyarusova.lab02_bars_sevice.interceptor;

import jakarta.ws.rs.core.Response;
import lombok.Getter;

@Getter
public class ExtendedException extends Exception {

    private final Response.Status status;
    private final String extendedClass;

    public ExtendedException(Response.Status status, String message) {
        super(message);
        this.status = status;
        this.extendedClass = ExtendedException.class.getName();
    }

    public ExtendedException(Response.Status status, String message, String extendedClass) {
        super(message);
        this.status = status;
        this.extendedClass = extendedClass;
    }
}
