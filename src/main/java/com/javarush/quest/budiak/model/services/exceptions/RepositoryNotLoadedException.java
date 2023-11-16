package com.javarush.quest.budiak.model.services.exceptions;

public class RepositoryNotLoadedException extends RuntimeException{

    public RepositoryNotLoadedException(String message) {
        super(message);
    }
}
