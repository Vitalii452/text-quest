package com.javarush.quest.budiak.utils.exceptions;

public class DataLoaderException extends RuntimeException {

    public DataLoaderException(String message) {
        super(message);
    }

    public DataLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
