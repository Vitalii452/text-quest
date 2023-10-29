package com.textquest.utils;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Optional;

public interface DataLoader<T> {
    Optional<T> load(String fileName, TypeReference<T> typeRef);
}
