package com.javarush.quest.budiak.model.services;

import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.dto.DialogueDto;
import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.dto.LocationDto;
import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.services.game.DialoguesService;
import com.javarush.quest.budiak.model.services.game.EndingService;
import com.javarush.quest.budiak.model.services.game.LocationsService;
import com.javarush.quest.budiak.model.services.game.QuestService;

import java.util.List;
import java.util.Optional;

public class GameService {

    private static final int DEFAULT_QUEST_CONTAINER_ID = 1;
    private final DialoguesService dialoguesService;
    private final LocationsService locationsService;
    private final EndingService endingService;
    private final QuestService questService;

    public GameService(DialoguesService dialoguesService, LocationsService locationsService, EndingService endingService, QuestService questService) {
        this.dialoguesService = dialoguesService;
        this.locationsService = locationsService;
        this.endingService = endingService;
        this.questService = questService;
    }

    public void setQuestsContainer() {
        questService.setRepository(DEFAULT_QUEST_CONTAINER_ID);
    }

    public List<QuestDto> getAllQuestsDto() {
        return questService.getAllQuestsDto();
    }

    public void setEndingContainerById(int endingContainerId) {
        endingService.setRepository(endingContainerId);
    }

    public void setDialoguesContainer(int dialoguesContainer) {
        dialoguesService.setRepository(dialoguesContainer);
    }

    public int getLocationsContainerIdByQuestDtoId(int questDtoId) {
        return questService.getLocationsContainerIdByQuestDtoId(questDtoId);
    }

    public int getEndingsContainerIdByQuestDtoId(int questDtoId) {
        return questService.getEndingContainerIdByQuestDtoId(questDtoId);
    }

    public int getDialoguesContainerIdByLocationDtoId(int locationDtoId) {
        return locationsService.getDialoguesContainerByLocationDtoId(locationDtoId);
    }

    public DialogueDto getFirstDialogueDto(int dialoguesContainerId) {
        return dialoguesService.getFirstDialogue(dialoguesContainerId);
    }

    public int getNextDialogueId(DialogueDto dialogueDto, int dialogueOptionId) {
        return dialoguesService.getNextDialogueId(dialogueDto, dialogueOptionId);
    }

    public DialogueDto getDialogueDtoById(int dialogueId) {
        return dialoguesService.getDto(dialogueId);
    }

    public LocationDto getFirstLocationDto(int locationsContainerId) {
        return locationsService.getFirstLocation(locationsContainerId);
    }

    public Optional<LocationDto> getNextLocationDto(int currentLocationId) {
        return locationsService.getNextLocationDto(currentLocationId);
    }

    public int getScoreForOption(DialogueDto dialogueDto, int dialogueOptionId) {
        return dialoguesService.getScoreForSelectedOption(dialogueDto, dialogueOptionId);
    }

    public void updateScore(int score) {
        endingService.updateScore(score);
    }

    public EndingDto getEndingDto() {
        return endingService.findAppropriateEnding();
    }

    public Quest getQuestById(int questId) {
        return questService.getEntityById(questId);
    }

    public List<Location> getListLocationsByContainerId(int locationsContainerId) {
        return locationsService.getAllLocations(locationsContainerId);
    }
}
