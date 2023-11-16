package com.javarush.quest.budiak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import com.javarush.quest.budiak.model.entity.containers.endings.EndingsContainer;

import jakarta.servlet.ServletContext;
import java.util.Optional;

public class EndingsContainerLoader extends AbstractDataLoader<Ending, EndingsContainer> {

    @Override
    public Optional<EndingsContainer> loadContainer(String endingsContainerId, ServletContext servletContext) {
        String endingsContainerName = buildFileName(endingsContainerId);
        return load(endingsContainerName, new TypeReference<>() {}, servletContext);
    }

    @Override
    protected String buildFileName(String containerId) {
        return "endingsContainers/EndingsContainer_" + containerId + ".json";
    }
}
