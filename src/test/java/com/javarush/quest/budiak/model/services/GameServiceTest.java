package com.javarush.quest.budiak.model.services;

import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.services.game.EndingService;
import com.javarush.quest.budiak.model.dto.DialogueDto;
import com.javarush.quest.budiak.model.dto.LocationDto;
import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.services.game.DialoguesService;
import com.javarush.quest.budiak.model.services.game.LocationsService;
import com.javarush.quest.budiak.model.services.game.QuestService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameServiceTest {

    @Test
    public void setQuestsContainer_whenCalled_shouldSetDefaultQuestContainer() {
        QuestService mockQuestService = mock(QuestService.class);
        GameService gameService = new GameService(null, null, null, mockQuestService);

        gameService.setQuestsContainer();

        verify(mockQuestService).setRepository(1);
    }

    @Test
    public void getAllQuestsDto_whenCalled_shouldReturnListOfQuestDtos() {
        List<QuestDto> expectedQuests = Arrays.asList(new QuestDto(), new QuestDto());
        QuestService mockQuestService = mock(QuestService.class);
        when(mockQuestService.getAllQuestsDto()).thenReturn(expectedQuests);
        GameService gameService = new GameService(null, null, null, mockQuestService);

        List<QuestDto> actualQuests = gameService.getAllQuestsDto();

        assertEquals(expectedQuests, actualQuests);
    }

    @Test
    public void setEndingContainerById_whenCalledWithId_shouldSetEndingContainer() {
        int endingContainerId = 5;
        EndingService mockEndingService = mock(EndingService.class);
        GameService gameService = new GameService(null, null, mockEndingService, null);

        gameService.setEndingContainerById(endingContainerId);

        verify(mockEndingService).setRepository(endingContainerId);
    }

    @Test
    public void setDialoguesContainer_whenCalledWithId_shouldSetDialoguesContainer() {
        int dialoguesContainerId = 3;
        DialoguesService mockDialoguesService = mock(DialoguesService.class);
        GameService gameService = new GameService(mockDialoguesService, null, null, null);

        gameService.setDialoguesContainer(dialoguesContainerId);

        verify(mockDialoguesService).setRepository(dialoguesContainerId);
    }

    @Test
    public void getLocationsContainerIdByQuestDtoId_whenCalledWithId_shouldReturnContainerId() {
        int questDtoId = 2;
        int expectedContainerId = 10;
        QuestService mockQuestService = mock(QuestService.class);
        when(mockQuestService.getLocationsContainerIdByQuestDtoId(questDtoId)).thenReturn(expectedContainerId);
        GameService gameService = new GameService(null, null, null, mockQuestService);

        int actualContainerId = gameService.getLocationsContainerIdByQuestDtoId(questDtoId);

        assertEquals(expectedContainerId, actualContainerId);
    }

    @Test
    public void getEndingsContainerIdByQuestDtoId_whenCalledWithId_shouldReturnContainerId() {
        int questDtoId = 4;
        int expectedContainerId = 7;
        QuestService mockQuestService = mock(QuestService.class);
        when(mockQuestService.getEndingContainerIdByQuestDtoId(questDtoId)).thenReturn(expectedContainerId);
        GameService gameService = new GameService(null, null, null, mockQuestService);

        int actualContainerId = gameService.getEndingsContainerIdByQuestDtoId(questDtoId);

        assertEquals(expectedContainerId, actualContainerId);
    }

    @Test
    public void getDialoguesContainerIdByLocationDtoId_whenCalledWithId_shouldReturnContainerId() {
        int locationDtoId = 3;
        int expectedContainerId = 9;
        LocationsService mockLocationsService = mock(LocationsService.class);
        when(mockLocationsService.getDialoguesContainerByLocationDtoId(locationDtoId)).thenReturn(expectedContainerId);
        GameService gameService = new GameService(null, mockLocationsService, null, null);

        int actualContainerId = gameService.getDialoguesContainerIdByLocationDtoId(locationDtoId);

        assertEquals(expectedContainerId, actualContainerId);
    }

    @Test
    public void getFirstDialogueDto_whenCalledWithId_shouldReturnFirstDialogueDto() {
        int dialoguesContainerId = 5;
        DialogueDto expectedDialogue = new DialogueDto();
        DialoguesService mockDialoguesService = mock(DialoguesService.class);
        when(mockDialoguesService.getFirstDialogue(dialoguesContainerId)).thenReturn(expectedDialogue);
        GameService gameService = new GameService(mockDialoguesService, null, null, null);

        DialogueDto actualDialogue = gameService.getFirstDialogueDto(dialoguesContainerId);

        assertEquals(expectedDialogue, actualDialogue);
    }

    @Test
    public void getNextDialogueId_whenCalledWithDialogueDtoAndOptionId_shouldReturnNextDialogueId() {
        DialogueDto dialogueDto = new DialogueDto();
        int dialogueOptionId = 2;
        int expectedNextId = 6;
        DialoguesService mockDialoguesService = mock(DialoguesService.class);
        when(mockDialoguesService.getNextDialogueId(dialogueDto, dialogueOptionId)).thenReturn(expectedNextId);
        GameService gameService = new GameService(mockDialoguesService, null, null, null);

        int actualNextId = gameService.getNextDialogueId(dialogueDto, dialogueOptionId);

        assertEquals(expectedNextId, actualNextId);
    }

    @Test
    public void getDialogueDtoById_whenCalledWithId_shouldReturnDialogueDto() {
        int dialogueId = 8;
        DialogueDto expectedDialogue = new DialogueDto();
        DialoguesService mockDialoguesService = mock(DialoguesService.class);
        when(mockDialoguesService.getDto(dialogueId)).thenReturn(expectedDialogue);
        GameService gameService = new GameService(mockDialoguesService, null, null, null);

        DialogueDto actualDialogue = gameService.getDialogueDtoById(dialogueId);

        assertEquals(expectedDialogue, actualDialogue);
    }

    @Test
    public void getFirstLocationDto_whenCalledWithId_shouldReturnFirstLocationDto() {
        int locationsContainerId = 4;
        LocationDto expectedLocation = new LocationDto();
        LocationsService mockLocationsService = mock(LocationsService.class);
        when(mockLocationsService.getFirstLocation(locationsContainerId)).thenReturn(expectedLocation);
        GameService gameService = new GameService(null, mockLocationsService, null, null);

        LocationDto actualLocation = gameService.getFirstLocationDto(locationsContainerId);

        assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void getNextLocationDto_whenCalledWithCurrentLocationId_shouldReturnNextLocationDto() {
        int currentLocationId = 5;
        Optional<LocationDto> expectedNextLocation = Optional.of(new LocationDto());
        LocationsService mockLocationsService = mock(LocationsService.class);
        when(mockLocationsService.getNextLocationDto(currentLocationId)).thenReturn(expectedNextLocation);
        GameService gameService = new GameService(null, mockLocationsService, null, null);

        Optional<LocationDto> actualNextLocation = gameService.getNextLocationDto(currentLocationId);

        assertEquals(expectedNextLocation, actualNextLocation);
    }

    @Test
    public void getScoreForOption_whenCalledWithDialogueDtoAndOptionId_shouldReturnScore() {
        DialogueDto dialogueDto = new DialogueDto();
        int dialogueOptionId = 3;
        int expectedScore = 10;
        DialoguesService mockDialoguesService = mock(DialoguesService.class);
        when(mockDialoguesService.getScoreForSelectedOption(dialogueDto, dialogueOptionId)).thenReturn(expectedScore);
        GameService gameService = new GameService(mockDialoguesService, null, null, null);

        int actualScore = gameService.getScoreForOption(dialogueDto, dialogueOptionId);

        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void updateScore_whenCalledWithScore_shouldUpdateScore() {
        int score = 20;
        EndingService mockEndingService = mock(EndingService.class);
        GameService gameService = new GameService(null, null, mockEndingService, null);

        gameService.updateScore(score);

        verify(mockEndingService).updateScore(score);
    }

    @Test
    public void getEndingDto_whenCalled_shouldReturnEndingDto() {
        EndingDto expectedEnding = new EndingDto();
        EndingService mockEndingService = mock(EndingService.class);
        when(mockEndingService.findAppropriateEnding()).thenReturn(expectedEnding);
        GameService gameService = new GameService(null, null, mockEndingService, null);

        EndingDto actualEnding = gameService.getEndingDto();

        assertEquals(expectedEnding, actualEnding);
    }

    @Test
    public void getQuestById_whenCalledWithId_shouldReturnQuest() {
        int questId = 7;
        Quest expectedQuest = new Quest();
        QuestService mockQuestService = mock(QuestService.class);
        when(mockQuestService.getEntityById(questId)).thenReturn(expectedQuest);
        GameService gameService = new GameService(null, null, null, mockQuestService);

        Quest actualQuest = gameService.getQuestById(questId);

        assertEquals(expectedQuest, actualQuest);
    }

    @Test
    public void getListLocationsByContainerId_whenCalledWithId_shouldReturnListOfLocations() {
        int locationsContainerId = 9;
        List<Location> expectedLocations = Arrays.asList(new Location(), new Location());
        LocationsService mockLocationsService = mock(LocationsService.class);
        when(mockLocationsService.getAllLocations(locationsContainerId)).thenReturn(expectedLocations);
        GameService gameService = new GameService(null, mockLocationsService, null, null);

        List<Location> actualLocations = gameService.getListLocationsByContainerId(locationsContainerId);

        assertEquals(expectedLocations, actualLocations);
    }

}