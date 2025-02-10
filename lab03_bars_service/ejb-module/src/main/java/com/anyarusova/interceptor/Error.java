package com.anyarusova.interceptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Error {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;

    public Error(String message, int statusCode) {
        this(message, statusCode, LocalDateTime.now());
    }
}
