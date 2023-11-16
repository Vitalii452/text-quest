package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.LocationDto;
import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.mappers.LocationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LocationsService extends AbstractEntityService<LocationDto, Location> {

    private static final Logger LOGGER = LogManager.getLogger(LocationsService.class);

    public LocationsService(RepositoryFactory<Location> repositoryFactory) {
        super(repositoryFactory);
        LOGGER.info("LocationsService initialized");
    }

    @Override
    public LocationDto getDto(int locationId) {
        return LocationMapper.mapLocation(getEntityById(locationId));
    }

    public LocationDto getFirstLocation(int locationsContainerId) {
        setRepository(locationsContainerId);
        LocationDto dto = getDto(getMinLocationId());
        LOGGER.debug("Received first location DTO: {}", dto);
        return dto;
    }

    private int getMinLocationId() {
        int minLocationId = Collections.min(repository.getAll().keySet());
        LOGGER.debug("Determined minimum location ID: {}", minLocationId);
        return minLocationId;
    }

    public Optional<LocationDto> getNextLocationDto(int currentLocationId) {
        LOGGER.info("Getting next location DTO for current location ID: {}", currentLocationId);
        int nextLocationId = currentLocationId + 1;
        Optional<LocationDto> dto = Optional.ofNullable(getDto(nextLocationId));
        LOGGER.debug("Next location DTO: {}", dto);
        return dto;
    }

    public int getDialoguesContainerByLocationDtoId(int locationDtoId) {
        int dialoguesContainerId = getEntityById(locationDtoId).getDialoguesContainerId();
        LOGGER.debug("Received dialogues container ID: {}", dialoguesContainerId);
        return dialoguesContainerId;
    }

    public List<Location> getAllLocations(int locationsContainerId) {
        LOGGER.info("Receiving all locations for container ID: {}", locationsContainerId);
        setRepository(locationsContainerId);
        List<Location> locations = new ArrayList<>(repository.getAll().values());
        LOGGER.debug("Received all locations: {}", locations);
        return locations;
    }
}
