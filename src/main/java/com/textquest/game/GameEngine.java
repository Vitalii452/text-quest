package com.textquest.game;

import java.util.logging.Logger;

public class GameEngine {
    private final static Logger LOGGER = Logger.getLogger(GameEngine.class.getName());
    private GameWorld gameWorld;

    public GameEngine(String playerName) {
        gameWorld = new GameWorld(playerName);
    }

    public void moveToNextLocation() {
        Location currentLocation = gameWorld.getCharacter().getCurrentLocation();
        int currentIndex = gameWorld.getLocations().indexOf(currentLocation);
        int nextIndex = currentIndex + 1;
        if (nextIndex < gameWorld.getLocations().size()) {
            Location nextLocation = gameWorld.getLocations().get(nextIndex);
            gameWorld.getCharacter().moveTo(nextLocation);
        } else {
            LOGGER.info("This is the last location");
        }
    }

    public void moveToPreviousLocation() {
        Location currentLocation = gameWorld.getCharacter().getCurrentLocation();
        int currentIndex = gameWorld.getLocations().indexOf(currentLocation);
        int prevIndex = currentIndex - 1;
        if (prevIndex >= 0) {
            Location prevLocation = gameWorld.getLocations().get(prevIndex);
            gameWorld.getCharacter().moveTo(prevLocation);
        } else {
            LOGGER.info("This is the first location");
        }
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

}


