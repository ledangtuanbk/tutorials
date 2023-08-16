package com.example.springrediscache.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomNotFoundException extends Exception {
    private String message;

    public CustomNotFoundException(Object key) {
        this.message = "Not found with " + key;
    }
}
