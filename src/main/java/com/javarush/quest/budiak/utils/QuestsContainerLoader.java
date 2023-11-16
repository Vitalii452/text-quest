package com.javarush.quest.budiak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.javarush.quest.budiak.model.entity.containers.quests.QuestsContainer;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;

import jakarta.servlet.ServletContext;
import java.util.Optional;

public class QuestsContainerLoader extends AbstractDataLoader<Quest, QuestsContainer> {

    @Override
    public Optional<QuestsContainer> loadContainer(String questsContainerId, ServletContext servletContext) {
        String questsContainerName = buildFileName(questsContainerId);
        return load(questsContainerName, new TypeReference<>() {}, servletContext);
    }

    @Override
    protected String buildFileName(String questsContainerId) {
        return "quests/QuestsContainer_" + questsContainerId + ".json";
    }
}
