package com.javarush.quest.budiak.model.repositories;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.Map;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T> {

    private Map<Integer, T> map;

    @Override
    public Map<Integer, T> getAll() {
        return map;
    }

    public AbstractRepository(Map<Integer, T> map) {
        this.map = map;
    }
}
