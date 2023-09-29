package com.textquest.game;

import java.util.logging.Logger;

public class Character {
    private final String name;
    private Location currentLocation;
    private final static Logger LOGGER = Logger.getLogger(Character.class.getName());

    public Character(String name, Location startLocation) {
        this.name = name;
        this.currentLocation = startLocation;
    }

    public void moveTo(Location newLocation) {
        LOGGER.info("The character has changed location");
        this.currentLocation = newLocation;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

}

