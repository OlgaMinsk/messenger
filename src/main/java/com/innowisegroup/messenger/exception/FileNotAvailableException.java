package com.innowisegroup.messenger.exception;

public class FileNotAvailableException extends RuntimeException{

    public FileNotAvailableException() {
        super();
    }

    public FileNotAvailableException(String message) {
        super(message);
    }

    public FileNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNotAvailableException(Throwable cause) {
        super(cause);
    }
}