package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import com.javarush.quest.budiak.model.entity.containers.endings.EndingsContainer;
import com.javarush.quest.budiak.model.repositories.EndingsRepository;
import com.javarush.quest.budiak.utils.Loader;

import jakarta.servlet.ServletContext;
import java.util.Map;

public class EndingsRepositoryFactory extends AbstractRepositoryFactory <Ending, EndingsContainer, EndingsRepository>{

    public EndingsRepositoryFactory(Loader<EndingsContainer> containerLoader, ServletContext servletContext) {
        super(containerLoader, servletContext);
    }

    @Override
    protected EndingsRepository createSpecificRepository(Map<Integer, Ending> map) {
        return new EndingsRepository(map);
    }
}
