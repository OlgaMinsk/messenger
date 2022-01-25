package com.innowisegroup.messenger.exception;

import java.io.IOException;

public class DuplicateUniqueValueException extends IOException {
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
