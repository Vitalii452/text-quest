package com.javarush.quest.budiak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.entity.containers.locations.LocationsContainer;

import jakarta.servlet.ServletContext;
import java.util.Optional;

public class LocationsContainerLoader extends AbstractDataLoader<Location, LocationsContainer> {

    @Override
    public Optional<LocationsContainer> loadContainer(String locationsContainerId, ServletContext servletContext) {
        String locationContainerName = buildFileName(locationsContainerId);
        return load(locationContainerName, new TypeReference<>() {}, servletContext);
    }

    @Override
    protected String buildFileName(String locationsContainerId) {
        return "locationsContainers/LocationsContainer_" + locationsContainerId + ".json";
    }
}

