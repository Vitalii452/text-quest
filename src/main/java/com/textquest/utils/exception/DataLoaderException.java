package com.textquest.utils.exception;

public class DataLoaderException extends RuntimeException {
    public DataLoaderException() {
    }

    public DataLoaderException(String message) {
        super(message);
    }

    public DataLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLoaderException(Throwable cause) {
        super(cause);
    }
}
