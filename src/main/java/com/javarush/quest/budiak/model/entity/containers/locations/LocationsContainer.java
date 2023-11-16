package com.javarush.quest.budiak.model.entity.containers.locations;

import com.javarush.quest.budiak.model.entity.AbstractEntity;
import com.javarush.quest.budiak.model.entity.containers.Container;

import java.util.List;

public class LocationsContainer extends AbstractEntity implements Container<Location> {

    private int id;
    private List<Location> elements;

    public LocationsContainer() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Location> getElements() {
        return elements;
    }

    @Override
    public void setElements(List<Location> elements) {
        this.elements = elements;
    }
}
