package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.dto.LocationDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LocationMapperTest {
    @Test
    public void mapLocation_withNullLocation_shouldLogWarningAndReturnNull() {
        LocationDto result = LocationMapper.mapLocation(null);

        assertNull(result);
    }
    @Test
    public void mapLocation_withValidLocation_shouldMapToLocationDto() {
        Location location = new Location();
        location.setId(1);
        location.setName("Test Location");
        location.setDescription(Arrays.asList("Description 1", "Description 2"));

        LocationDto result = LocationMapper.mapLocation(location);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Location", result.getName());
        assertEquals(Arrays.asList("Description 1", "Description 2"), result.getDescriptionParagraph());
    }

}