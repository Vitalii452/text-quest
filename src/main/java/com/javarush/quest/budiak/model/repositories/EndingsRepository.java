package com.javarush.quest.budiak.model.repositories;

import com.javarush.quest.budiak.model.entity.containers.endings.Ending;

import java.util.Map;

public class EndingsRepository extends AbstractRepository<Ending>{

    public EndingsRepository(Map<Integer, Ending> map) {
        super(map);
    }

    @Override
    public Ending getById(int endingId) {
        return getAll().get(endingId);
    }
}
