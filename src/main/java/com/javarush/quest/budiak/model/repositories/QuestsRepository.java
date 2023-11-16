package com.javarush.quest.budiak.model.repositories;

import com.javarush.quest.budiak.model.entity.containers.quests.Quest;

import java.util.Map;

public class QuestsRepository extends AbstractRepository<Quest> {

    public QuestsRepository(Map<Integer, Quest> map) {
        super(map);
    }

    @Override
    public Quest getById(int questId) {
        return getAll().get(questId);
    }
}
