package com.javarush.quest.budiak.model.services.exceptions;

public class NoEndingForThisScoreRangeException extends RuntimeException {

    public NoEndingForThisScoreRangeException(String message) {
        super(message);
    }
}
