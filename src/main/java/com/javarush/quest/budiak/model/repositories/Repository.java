package com.javarush.quest.budiak.model.repositories;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.Map;

public interface Repository<T extends AbstractEntity> {

    Map<Integer, T> getAll();

    T getById(int id);
}
