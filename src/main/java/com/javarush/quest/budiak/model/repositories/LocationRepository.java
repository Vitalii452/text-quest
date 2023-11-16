package com.javarush.quest.budiak.model.repositories;

import com.javarush.quest.budiak.model.entity.containers.locations.Location;

import java.util.Map;

public class LocationRepository extends AbstractRepository<Location> {

    public LocationRepository(Map<Integer, Location> map) {
        super(map);
    }

    @Override
    public Location getById(int locationId) {
        return getAll().get(locationId);
    }
}
