package com.anyarusova;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;


@Path("/hello-world")
@Slf4j
public class HelloResource {

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}