package com.javarush.quest.budiak.utils;

import jakarta.servlet.ServletContext;
import java.util.Optional;

public interface Loader <T> {
    Optional<T> loadContainer(String containerId, ServletContext servletContext);
}
