package com.innowisegroup.messenger.exception;

public class DuplicateUniqueValueException extends RuntimeException {
    public DuplicateUniqueValueException() {
    }

    public DuplicateUniqueValueException(String message) {
        super(message);
    }

    public DuplicateUniqueValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUniqueValueException(Throwable cause) {
        super(cause);
    }

}
