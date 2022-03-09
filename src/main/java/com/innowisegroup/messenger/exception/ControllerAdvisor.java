package com.innowisegroup.messenger.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NotFoundException.class)
    public ResponseStatusException handleNotFoundException(NotFoundException exception) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(FileNotAvailableException.class)
    public ResponseStatusException handleFileNotAvailableException(FileNotAvailableException exception) {
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(EmptyFileException.class)
    public ResponseStatusException handleEmptyFileException(EmptyFileException exception) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AccessRightsException.class)
    public ResponseStatusException handleEmptyFileException(AccessRightsException exception) {
        return new ResponseStatusException(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseStatusException handleBadCredentialsException(BadCredentialsException exception) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseStatusException handleAuthenticationException(AuthenticationException exception) {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

//    @ExceptionHandler(DuplicateUniqueValueException.class)
//    public ResponseStatusException handleDuplicateUniqueValueException(DuplicateUniqueValueException exception) {
//        return new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
//    }

}
