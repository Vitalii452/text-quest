package com.textquest.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.textquest.model.entity.Location;
import com.textquest.utils.exception.DataLoaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LocationDataLoader extends AbstractDataLoader<List<Location>> {
    public static final Logger LOGGER = LogManager.getLogger(LocationDataLoader.class);

    public List<Location> loadLocations() {
        return load("locations.json", new TypeReference<>() {
        }).orElse(Collections.emptyList());
    }

    @Override
    protected void handleMissingData(String fileName) {
        String errorMessage = String.format("The list of loaded locations from the file \"%s\" is missing.", fileName);
        LOGGER.fatal(errorMessage);
        throw new DataLoaderException(errorMessage);
    }

    @Override
    protected void handleError(String fileName, IOException e) {
        String errorMessage = String.format("Error occurred while loading locations from file \"%s\": %s", fileName, e.getMessage());
        LOGGER.fatal(errorMessage, e);
        throw new DataLoaderException(errorMessage, e);
    }
}

