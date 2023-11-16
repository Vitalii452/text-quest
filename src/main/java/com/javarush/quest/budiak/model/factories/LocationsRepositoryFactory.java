package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.entity.containers.locations.LocationsContainer;
import com.javarush.quest.budiak.model.repositories.LocationRepository;
import com.javarush.quest.budiak.utils.Loader;

import jakarta.servlet.ServletContext;
import java.util.Map;

public class LocationsRepositoryFactory extends AbstractRepositoryFactory<Location, LocationsContainer, LocationRepository> {

    public LocationsRepositoryFactory(Loader<LocationsContainer> containerLoader, ServletContext servletContext) {
        super(containerLoader, servletContext);
    }

    @Override
    protected LocationRepository createSpecificRepository(Map<Integer, Location> map) {
        return new LocationRepository(map);
    }
}
