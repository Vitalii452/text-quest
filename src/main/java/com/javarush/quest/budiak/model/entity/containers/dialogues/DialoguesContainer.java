package com.javarush.quest.budiak.model.entity.containers.dialogues;

import com.javarush.quest.budiak.model.entity.containers.Container;
import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.List;

public class DialoguesContainer extends AbstractEntity implements Container<Dialogue> {

    private int id;
    private List<Dialogue> elements;

    public DialoguesContainer() {
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
    public List<Dialogue> getElements() {
        return elements;
    }

    @Override
    public void setElements(List<Dialogue> elements) {
        this.elements = elements;
    }
}
