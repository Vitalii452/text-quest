package com.javarush.quest.budiak.model.repositories;

import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;

import java.util.Map;

public class DialoguesRepository extends AbstractRepository<Dialogue> {

    public DialoguesRepository(Map<Integer, Dialogue> map) {
        super(map);
    }

    @Override
    public Dialogue getById(int dialogueId) {
        return getAll().get(dialogueId);
    }
}
