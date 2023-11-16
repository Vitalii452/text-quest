package com.javarush.quest.budiak.model.entity.containers.endings;

import com.javarush.quest.budiak.model.entity.containers.Container;

import java.util.List;

public class EndingsContainer implements Container<Ending> {

    private int id;
    private List<Ending> elements;

    public EndingsContainer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Ending> getElements() {
        return elements;
    }

    @Override
    public void setElements(List<Ending> elements) {
        this.elements = elements;
    }
}
