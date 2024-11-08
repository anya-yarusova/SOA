package com.anyarusova.lab02_bars_sevice;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class HelloApplication extends Application {
    private Set<Class<?>> empty =
            new HashSet<Class<?>>();

    public HelloApplication() {
        empty.add(HelloResource.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }
}