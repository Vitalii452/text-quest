package com.javarush.quest.budiak.model.services.exceptions;

public class InvalidDialogueOptionStateException extends RuntimeException {

    public InvalidDialogueOptionStateException(int dialogueOptionId) {
        super("Invalid state for dialogue option with ID " + dialogueOptionId + ": missing score");
    }
}