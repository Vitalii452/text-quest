package com.javarush.quest.budiak.utils;

import com.javarush.quest.budiak.model.entity.containers.endings.EndingsContainer;
import com.javarush.quest.budiak.utils.exceptions.DataLoaderException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.ServletContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EndingsContainerLoaderTest {
    @Test
    void loadContainer_ExistingFile_ShouldReturnContainer() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        String testFilePath = "src/test/resources/data/endingsContainers/EndingsContainer_1.json";

        Mockito.when(servletContext.getRealPath("/data/endingsContainers/EndingsContainer_1.json")).thenReturn(testFilePath);

        Loader<EndingsContainer> loader = new EndingsContainerLoader();
        Optional<EndingsContainer> result = loader.loadContainer("1", servletContext);

        assertTrue(result.isPresent());
    }

    @Test
    void loadContainer_MissingFile_ShouldThrowException() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);

        Mockito.when(servletContext.getRealPath("/data/endingsContainers/EndingsContainer_missing.json"))
                .thenReturn("src/test/resources/data/endingsContainers/EndingsContainer_missing.json");

        Loader<EndingsContainer> loader = new EndingsContainerLoader();

        assertThrows(DataLoaderException.class, () -> loader.loadContainer("missing", servletContext));
    }

    @Test
    void loadContainer_IOException_ShouldThrowException() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);

        Mockito.when(servletContext.getRealPath("/data/endingsContainers/EndingsContainer_IOException.json"))
                .thenReturn("src/test/resources/data/endingsContainers/EndingsContainer_IOException.json");

        Loader<EndingsContainer> loader = new EndingsContainerLoader();

        assertThrows(DataLoaderException.class, () -> loader.loadContainer("IOException", servletContext));
    }

}