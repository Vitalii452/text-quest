package com.javarush.quest.budiak.model.entity.containers;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.List;

public interface Container<T extends AbstractEntity> {

    List<T> getElements();

    void setElements(List<T> elements);
}
