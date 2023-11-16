package com.javarush.quest.budiak.utils;

import com.javarush.quest.budiak.model.entity.containers.locations.LocationsContainer;
import com.javarush.quest.budiak.utils.exceptions.DataLoaderException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.ServletContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LocationsContainerLoaderTest {
    @Test
    void loadContainer_ExistingFile_ShouldReturnContainer() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);
        String testFilePath = "src/test/resources/data/locationsContainers/LocationsContainer_1.json";

        Mockito.when(servletContext.getRealPath("/data/locationsContainers/LocationsContainer_1.json")).thenReturn(testFilePath);

        Loader<LocationsContainer> loader = new LocationsContainerLoader();
        Optional<LocationsContainer> result = loader.loadContainer("1", servletContext);

        assertTrue(result.isPresent());
    }

    @Test
    void loadContainer_MissingFile_ShouldThrowException() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);

        Mockito.when(servletContext.getRealPath("/data/locationsContainers/LocationsContainer_missing.json"))
                .thenReturn("src/test/resources/data/locationsContainers/LocationsContainer_missing.json");

        Loader<LocationsContainer> loader = new LocationsContainerLoader();

        assertThrows(DataLoaderException.class, () -> loader.loadContainer("missing", servletContext));
    }

    @Test
    void loadContainer_IOException_ShouldThrowException() {
        ServletContext servletContext = Mockito.mock(ServletContext.class);

        Mockito.when(servletContext.getRealPath("/data/locationsContainers/LocationsContainer_IOException.json"))
                .thenReturn("src/test/resources/data/locationsContainers/LocationsContainer_IOException.json");

        Loader<LocationsContainer> loader = new LocationsContainerLoader();

        assertThrows(DataLoaderException.class, () -> loader.loadContainer("IOException", servletContext));
    }

}