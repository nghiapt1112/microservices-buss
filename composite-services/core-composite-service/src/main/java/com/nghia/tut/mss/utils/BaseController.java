package com.nghia.tut.mss.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    public <T> ResponseEntity<T> createResponseWithStatus(T body, HttpStatus status) {
        return new ResponseEntity<T>(body, status);
    }

    public <T> ResponseEntity<T> createResponseWithOK(T body) {
        return new ResponseEntity<T>(body, HttpStatus.OK);
    }

}
