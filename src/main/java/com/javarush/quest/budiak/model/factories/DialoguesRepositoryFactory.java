package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.repositories.DialoguesRepository;
import com.javarush.quest.budiak.model.entity.containers.dialogues.DialoguesContainer;
import com.javarush.quest.budiak.utils.Loader;

import jakarta.servlet.ServletContext;
import java.util.Map;

public class DialoguesRepositoryFactory extends AbstractRepositoryFactory<Dialogue, DialoguesContainer, DialoguesRepository> {

    public DialoguesRepositoryFactory(Loader<DialoguesContainer> containerLoader, ServletContext servletContext) {
        super(containerLoader, servletContext);
    }

    @Override
    public DialoguesRepository createSpecificRepository(Map<Integer, Dialogue> map) {
        return new DialoguesRepository(map);
    }
}
