package com.textquest.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.textquest.model.entity.Dialogue;
import com.textquest.utils.exception.DataLoaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class DialogueDataLoader extends AbstractDataLoader<Dialogue> {
    private static final Logger LOGGER = LogManager.getLogger(DialogueDataLoader.class);

    public Optional<Dialogue> loadDialogue(String locationName) {
        String locationJsonName = "dialogues/" + locationName + ".json";
        return load(locationJsonName, new TypeReference<>() {
        });
    }

    @Override
    protected void handleMissingData(String fileName) {
        String infoMessage = String.format("The dialogue loaded from the file \"%s\" is missing.", fileName);
        LOGGER.info(infoMessage);
    }

    @Override
    protected void handleError(String fileName, IOException e) {
        String errorMessage = String.format("Error occurred while loading dialogue from file \"%s\": %s", fileName, e.getMessage());
        LOGGER.fatal(errorMessage, e);
        throw new DataLoaderException(errorMessage, e);
    }
}
