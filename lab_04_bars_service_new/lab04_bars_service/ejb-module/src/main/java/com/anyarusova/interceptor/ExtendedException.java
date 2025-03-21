package com.anyarusova.interceptor;

import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebFault;
import lombok.Getter;

@WebFault(name = "ExtendedException")
public class ExtendedException extends Exception {
    private final Response.Status status;

    public ExtendedException(Response.Status status, String message) {
        super(message);
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }

    // Для SOAP-сериализации
    public String getFaultInfo() {
        return this.getMessage();
    }
}
