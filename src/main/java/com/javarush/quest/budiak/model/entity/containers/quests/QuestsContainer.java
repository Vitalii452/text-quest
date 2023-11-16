package com.javarush.quest.budiak.model.entity.containers.quests;

import com.javarush.quest.budiak.model.entity.AbstractEntity;
import com.javarush.quest.budiak.model.entity.containers.Container;

import java.util.List;

public class QuestsContainer extends AbstractEntity implements Container<Quest> {

    private int id;
    private List<Quest> elements;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public List<Quest> getElements() {
        return elements;
    }

    @Override
    public void setElements(List<Quest> elements) {
        this.elements = elements;
    }
}
