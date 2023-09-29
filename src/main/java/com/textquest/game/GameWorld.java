package com.textquest.game;

import com.textquest.utils.LocationLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWorld {
    private List<Location> locations = new ArrayList<>();
    private Character character;

    public GameWorld(String playerName) {
        initializeLocations();
        this.character = new Character(playerName, locations.get(0));
    }

    private void initializeLocations() {
        locations = new LocationLoader().loadLocations();
    }

    public Character getCharacter() {
        return character;
    }
    public List<Location> getLocations() {
        return locations;
    }

}
