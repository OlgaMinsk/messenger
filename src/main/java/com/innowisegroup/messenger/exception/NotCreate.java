package com.innowisegroup.messenger.exception;

public class NotCreate extends RuntimeException{

    public NotCreate() {
        super();
    }

    public NotCreate(String message) {
        super(message);
    }

    public NotCreate(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCreate(Throwable cause) {
        super(cause);
    }
}