package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.dto.LocationDto;
import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocationMapper {

    private static final Logger LOGGER = LogManager.getLogger(LocationMapper.class);


    private LocationMapper() {
    }

    public static LocationDto mapLocation(Location location) {
        if (location == null) {
            LOGGER.warn("Attempted to map null Location object");
            return null;
        }

        LOGGER.debug("Mapping Location to LocationDto: {}", location);
        LocationDto locationDto = new LocationDto();

        locationDto.setId(location.getId());
        locationDto.setName(location.getName());
        locationDto.setDescriptionParagraph(location.getDescriptionParagraphs());

        LOGGER.debug("Mapped LocationDto: {}", locationDto);
        return locationDto;
    }
}
