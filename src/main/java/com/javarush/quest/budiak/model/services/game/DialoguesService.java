package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.DialogueDto;
import com.javarush.quest.budiak.model.dto.DialogueOptionDto;
import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.mappers.DialogueMapper;
import com.javarush.quest.budiak.model.services.exceptions.InvalidDialogueOptionStateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Optional;

public class DialoguesService extends AbstractEntityService<DialogueDto, Dialogue> {

    private static final Logger LOGGER = LogManager.getLogger(DialoguesService.class);
    private static final int NO_NEXT_DIALOGUE_ID = 1;

    public DialoguesService(RepositoryFactory<Dialogue> repositoryFactory) {
        super(repositoryFactory);
        LOGGER.info("DialoguesService initialized");
    }

    @Override
    public DialogueDto getDto(int entityId) {
        return DialogueMapper.mapDialogue(getEntityById(entityId));
    }

    public DialogueDto getFirstDialogue(int dialoguesContainerId) {
        setRepository(dialoguesContainerId);
        DialogueDto dto = getDto(getFirstDialogueId());
        LOGGER.debug("Received first dialogue DTO: {}", dto);
        return dto;
    }

    public int getScoreForSelectedOption(DialogueDto dialogueDto, int dialogueOptionId) {
        LOGGER.info("Getting score for selected option in dialogue: {} with option ID: {}", dialogueDto, dialogueOptionId);
        int score = findOptionById(dialogueDto, dialogueOptionId)
                .map(DialogueOptionDto::getScore).
                orElseThrow(() -> new InvalidDialogueOptionStateException(dialogueOptionId));
        LOGGER.debug("Score for selected option: {}", score);
        return score;
    }

    public int getNextDialogueId(DialogueDto dialogueDto, int dialogueOptionId) {
        LOGGER.info("Determining next dialogue ID for dialogue: {} with selected option ID: {}", dialogueDto, dialogueOptionId);
        int nextDialogueId = findOptionById(dialogueDto, dialogueOptionId)
                .map(DialogueOptionDto::getNextDialogueId)
                .filter(nextId -> nextId != 0)
                .orElse(NO_NEXT_DIALOGUE_ID);
        LOGGER.debug("Next dialogue ID: {}", nextDialogueId);
        return nextDialogueId;
    }

    private int getFirstDialogueId() {
        int firstDialogueId = Collections.min(repository.getAll().keySet());
        LOGGER.debug("First dialogue ID determined as: {}", firstDialogueId);
        return firstDialogueId;
    }

    private Optional<DialogueOptionDto> findOptionById(DialogueDto dialogueDto, int dialogueOptionId) {
        LOGGER.debug("Searching for dialogue option by ID: {} in dialogue: {}", dialogueOptionId, dialogueDto);
        return dialogueDto.getDialogueOptions().stream()
                .filter(optionDto -> optionDto.getId() == dialogueOptionId)
                .findFirst();
    }
}
