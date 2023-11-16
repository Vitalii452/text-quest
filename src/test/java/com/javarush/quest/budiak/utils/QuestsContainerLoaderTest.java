package com.javarush.quest.budiak.utils;

import com.javarush.quest.budiak.model.entity.containers.quests.QuestsContainer;
import com.javarush.quest.budiak.utils.exceptions.DataLoaderException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.ServletContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuestsContainerLoaderTest {
    @Test
    void loadContainer_ExistingFile_ShouldReturnContainer() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        String testFilePath = "src/test/resources/data/quests/QuestsContainer_1.json";

        Mockito.when(servletContext.getRealPath("/data/quests/QuestsContainer_1.json")).thenReturn(testFilePath);

        Loader<QuestsContainer> loader = new QuestsContainerLoader();
        Optional<QuestsContainer> result = loader.loadContainer("1", servletContext);

        assertTrue(result.isPresent());
    }

    @Test
    void loadContainer_MissingFile_ShouldThrowException() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);

        Mockito.when(servletContext.getRealPath("/data/quests/QuestsContainer_missing.json"))
                .thenReturn("src/test/resources/data/quests/QuestsContainer_missing.json");

        Loader<QuestsContainer> loader = new QuestsContainerLoader();

        assertThrows(DataLoaderException.class, () -> loader.loadContainer("missing", servletContext));
    }

    @Test
    void loadContainer_IOException_ShouldThrowException() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);

        Mockito.when(servletContext.getRealPath("/data/quests/QuestsContainer_IOException.json"))
                .thenReturn("src/test/resources/data/quests/QuestsContainer_IOException.json");

        Loader<QuestsContainer> loader = new QuestsContainerLoader();

        assertThrows(DataLoaderException.class, () -> loader.loadContainer("IOException", servletContext));
    }

}