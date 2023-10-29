package com.textquest.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public abstract class AbstractDataLoader<T> implements DataLoader<T> {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public final Optional<T> load(String fileName, TypeReference<T> typeRef) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                handleMissingData(fileName);
                return Optional.empty();
            } else {
                return Optional.ofNullable(OBJECT_MAPPER.readValue(inputStream, typeRef));
            }
        } catch (IOException e) {
            handleError(fileName, e);
            return Optional.empty();
        }
    }

    protected abstract void handleMissingData(String fileName);

    protected abstract void handleError(String fileName, IOException e);
}
