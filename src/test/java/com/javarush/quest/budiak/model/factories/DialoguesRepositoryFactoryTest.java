package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.entity.containers.dialogues.DialoguesContainer;
import com.javarush.quest.budiak.model.factories.exceptions.NoSuchContainerException;
import com.javarush.quest.budiak.model.repositories.DialoguesRepository;
import com.javarush.quest.budiak.utils.DialoguesContainerLoader;
import com.javarush.quest.budiak.utils.Loader;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.ServletContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DialoguesRepositoryFactoryTest {
    @Test
    public void createRepository_withValidContainerId_shouldCreateDialoguesRepository() {
        int containerId = 1;
        Loader<DialoguesContainer> mockLoader = Mockito.mock(DialoguesContainerLoader.class);
        ServletContext mockServletContext = mock(ServletContext.class);
        DialoguesContainer dialoguesContainer = new DialoguesContainer();
        dialoguesContainer.setElements(List.of(new Dialogue()));

        when(mockLoader.loadContainer(String.valueOf(containerId), mockServletContext))
                .thenReturn(Optional.of(dialoguesContainer));

        DialoguesRepositoryFactory factory = new DialoguesRepositoryFactory(mockLoader, mockServletContext);

        DialoguesRepository repository = factory.createRepository(containerId);

        assertNotNull(repository);
    }

    @Test
    public void createRepository_withInvalidContainerId_shouldThrowNoSuchContainerException() {
        int containerId = 99;
        Loader<DialoguesContainer> mockLoader = mock(Loader.class);
        ServletContext mockServletContext = mock(ServletContext.class);
        when(mockLoader.loadContainer(String.valueOf(containerId), mockServletContext))
                .thenReturn(Optional.empty());

        DialoguesRepositoryFactory factory = new DialoguesRepositoryFactory(mockLoader, mockServletContext);

        assertThrows(NoSuchContainerException.class, () -> factory.createRepository(containerId));
    }

}