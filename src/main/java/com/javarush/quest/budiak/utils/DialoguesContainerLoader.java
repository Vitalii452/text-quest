package com.javarush.quest.budiak.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.entity.containers.dialogues.DialoguesContainer;

import jakarta.servlet.ServletContext;
import java.util.Optional;

public class DialoguesContainerLoader extends AbstractDataLoader<Dialogue, DialoguesContainer> {

    @Override
    public Optional<DialoguesContainer> loadContainer(String dialoguesContainerId, ServletContext servletContext) {
        String locationContainerName = buildFileName(dialoguesContainerId);
        return load(locationContainerName, new TypeReference<>() {}, servletContext);
    }

    @Override
    protected String buildFileName(String containerId) {
        return "dialoguesContainers/DialoguesContainer_" + containerId + ".json";
    }
}

