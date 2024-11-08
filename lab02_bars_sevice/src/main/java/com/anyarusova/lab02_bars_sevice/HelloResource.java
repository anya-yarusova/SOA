package com.anyarusova.lab02_bars_sevice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;


@Path("/hello-world")
@Slf4j
public class HelloResource {
    public HelloResource() {
        log.error("sfjbdvfnd fasvm casf dvhjwerasdvf whjefasd");
    }

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}