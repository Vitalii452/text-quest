package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.mappers.QuestMapper;
import com.javarush.quest.budiak.model.repositories.Repository;
import com.javarush.quest.budiak.model.services.exceptions.RepositoryNotLoadedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class QuestServiceTest {

    @Mock
    private RepositoryFactory<Quest> repositoryFactory;

    @Mock
    private Repository<Quest> questRepository;

    private QuestService questService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(repositoryFactory.createRepository(anyInt())).thenReturn(questRepository);
        questService = new QuestService(repositoryFactory);
        questService.setRepository(anyInt());
    }

    @Test
    void getDto_ValidEntityId_ShouldReturnMappedDto() {
        int questId = 1;
        Quest quest = mock(Quest.class);
        when(questService.repository.getById(questId)).thenReturn(quest);

        try (MockedStatic<QuestMapper> mockedStatic = Mockito.mockStatic(QuestMapper.class)) {
            QuestDto expectedDto = new QuestDto();

            mockedStatic.when(() -> QuestMapper.mapQuest(quest)).thenReturn(expectedDto);
            QuestDto result = questService.getDto(questId);

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void getDto_RepositoryNotInitialized_ShouldThrowRepositoryNotLoadedException() {
        int questId = 1;
        questService.repository = null;

        assertThrows(RepositoryNotLoadedException.class, () -> questService.getDto(questId));
    }

    @Test
    void getAllQuestsDto_WhenCalled_ShouldReturnListOfDtos() {
        Map<Integer, Quest> questsMap = new HashMap<>();
        questsMap.put(1, mock(Quest.class));
        questsMap.put(2, mock(Quest.class));

        when(questService.repository.getAll()).thenReturn(questsMap);

        try (MockedStatic<QuestMapper> mockedStatic = Mockito.mockStatic(QuestMapper.class)) {
            QuestDto mockedDto = new QuestDto();

            mockedStatic.when(() -> QuestMapper.mapQuest(any(Quest.class))).thenReturn(mockedDto);

            List<QuestDto> result = questService.getAllQuestsDto();

            assertEquals(2, result.size());
        }
    }

    @Test
    void getLocationsContainerIdByQuestDtoId_ValidQuestDtoId_ShouldReturnLocationsContainerId() {
        int questId = 1;
        Quest quest = mock(Quest.class);
        when(questService.repository.getById(questId)).thenReturn(quest);
        int expectedId = 10;
        when(quest.getLocationsContainerId()).thenReturn(expectedId);

        int result = questService.getLocationsContainerIdByQuestDtoId(questId);

        assertEquals(expectedId, result);
    }
    @Test
    void getEndingContainerIdByQuestDtoId_ValidQuestDtoId_ShouldReturnEndingsContainerId() {
        int questId = 1;
        Quest quest = mock(Quest.class);
        when(questService.repository.getById(questId)).thenReturn(quest);
        int expectedId = 20;
        when(quest.getEndingsContainerId()).thenReturn(expectedId);

        int result = questService.getEndingContainerIdByQuestDtoId(questId);

        assertEquals(expectedId, result);
    }
}
