package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.repositories.QuestsRepository;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.entity.containers.quests.QuestsContainer;
import com.javarush.quest.budiak.utils.Loader;

import jakarta.servlet.ServletContext;
import java.util.Map;

public class QuestsRepositoryFactory extends AbstractRepositoryFactory<Quest, QuestsContainer, QuestsRepository>{

    public QuestsRepositoryFactory(Loader<QuestsContainer> containerLoader, ServletContext servletContext) {
        super(containerLoader, servletContext);
    }

    @Override
    protected QuestsRepository createSpecificRepository(Map<Integer, Quest> map) {
        return new QuestsRepository(map);
    }
}
