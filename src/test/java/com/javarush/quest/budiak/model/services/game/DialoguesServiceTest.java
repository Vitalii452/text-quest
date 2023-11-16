package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.DialogueDto;
import com.javarush.quest.budiak.model.dto.DialogueOptionDto;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.mappers.DialogueMapper;
import com.javarush.quest.budiak.model.repositories.Repository;
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

class DialoguesServiceTest {

    @Mock
    private RepositoryFactory<Dialogue> repositoryFactory;

    @Mock
    private Repository<Dialogue> dialogueRepository;

    private DialoguesService dialoguesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(repositoryFactory.createRepository(anyInt())).thenReturn(dialogueRepository);
        dialoguesService = new DialoguesService(repositoryFactory);
        dialoguesService.setRepository(anyInt());
    }

    @Test
    void getDto_ValidEntityId_ShouldReturnMappedDto() {
        int dialogueId = 1;
        Dialogue dialogue = mock(Dialogue.class);
        when(dialogueRepository.getById(dialogueId)).thenReturn(dialogue);

        try (MockedStatic<DialogueMapper> mockedStatic = Mockito.mockStatic(DialogueMapper.class)) {
            DialogueDto expectedDto = new DialogueDto();
            mockedStatic.when(() -> DialogueMapper.mapDialogue(dialogue)).thenReturn(expectedDto);

            DialogueDto result = dialoguesService.getDto(dialogueId);

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void getFirstDialogue_ValidContainerId_ShouldReturnFirstDialogueDto() {
        int dialoguesContainerId = 1;
        int firstDialogueId = 1;
        Dialogue dialogue = mock(Dialogue.class);
        Map<Integer, Dialogue> dialoguesMap = new HashMap<>();
        dialoguesMap.put(firstDialogueId, dialogue);
        when(dialogueRepository.getAll()).thenReturn(dialoguesMap);

        try (MockedStatic<DialogueMapper> mockedStatic = Mockito.mockStatic(DialogueMapper.class)) {
            DialogueDto expectedDto = new DialogueDto();
            mockedStatic.when(() -> DialogueMapper.mapDialogue(any())).thenReturn(expectedDto);

            DialogueDto result = dialoguesService.getFirstDialogue(dialoguesContainerId);

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void getScoreForSelectedOption_ValidOption_ShouldReturnScore() {
        DialogueDto dialogueDto = new DialogueDto();
        DialogueOptionDto option = new DialogueOptionDto();
        option.setId(1);
        option.setScore(10);
        dialogueDto.setDialogueOptions(List.of(option));
        int optionId = 1;

        int score = dialoguesService.getScoreForSelectedOption(dialogueDto, optionId);

        assertEquals(10, score);
    }
    @Test
    void getNextDialogueId_ValidOption_ShouldReturnNextDialogueId() {
        DialogueDto dialogueDto = new DialogueDto();
        DialogueOptionDto option = new DialogueOptionDto();
        option.setId(1);
        option.setNextDialogueId(2);
        dialogueDto.setDialogueOptions(List.of(option));
        int optionId = 1;

        int nextDialogueId = dialoguesService.getNextDialogueId(dialogueDto, optionId);

        assertEquals(2, nextDialogueId);
    }

}
