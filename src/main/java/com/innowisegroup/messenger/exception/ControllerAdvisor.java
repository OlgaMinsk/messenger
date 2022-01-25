package com.innowisegroup.messenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NotFoundException.class)
    public ResponseStatusException handleNotFoundException(NotFoundException exception) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }

//    @ExceptionHandler(DuplicateUniqueValueException.class)
//    public ResponseStatusException handleDuplicateUniqueValueException(DuplicateUniqueValueException exception) {
//        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
//    }
}
