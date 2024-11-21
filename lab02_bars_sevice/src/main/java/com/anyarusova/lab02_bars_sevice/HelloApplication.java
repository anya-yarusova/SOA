package com.anyarusova.lab02_bars_sevice;

import com.anyarusova.lab02_bars_sevice.controller.BarsController;
import com.anyarusova.lab02_bars_sevice.interceptor.ExtendedExceptionMapper;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api/v1")
public class HelloApplication extends Application {
    private final Set<Class<?>> classes = new HashSet<>();

    public HelloApplication() {
        classes.add(HelloResource.class);
        classes.add(BarsController.class);
        classes.add(ExtendedExceptionMapper.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}