package com.javarush.quest.budiak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.entity.AbstractEntity;
import com.javarush.quest.budiak.model.entity.containers.Container;
import com.javarush.quest.budiak.utils.exceptions.DataLoaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public abstract class AbstractDataLoader<R extends AbstractEntity, T extends Container<R>> implements Loader<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractDataLoader.class);
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    protected final Optional<T> load(String fileName, TypeReference<T> typeRef, ServletContext servletContext) {
        try {
            String realPath = servletContext.getRealPath("/data/" + fileName);
            File file = new File(realPath);
            if (!file.exists()) {
                handleMissingData(fileName);
                return Optional.empty();
            } else {
                return Optional.ofNullable(OBJECT_MAPPER.readValue(file, typeRef));
            }
        } catch (IOException e) {
            handleError(fileName, e);
            return Optional.empty();
        }
    }

    protected void handleMissingData(String fileName) {
        LOGGER.fatal("The file {} is missing.", fileName);
        throw new DataLoaderException(String.format("The file \"%s\" is missing.", fileName));
    }

    protected void handleError(String fileName, IOException e) {
        LOGGER.fatal("Error occurred while loading data from file {}: {}", fileName, e.getMessage());
        throw new DataLoaderException(String.format("Error occurred while loading data from file \"%s\": %s", fileName, e.getMessage()), e);
    }

    protected abstract String buildFileName(String containerId);
}

