package com.textquest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.textquest.game.Location;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class LocationLoader {
    public List<Location> loadLocations() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("locations.json")){
            ObjectMapper objectMapper = new ObjectMapper();
            Location[] locationsArray = objectMapper.readValue(inputStream, Location[].class);
            return Arrays.asList(locationsArray);
        } catch (IOException e) {
            throw new RuntimeException("Error loading locations from locations.json file", e);
        }
    }
}

