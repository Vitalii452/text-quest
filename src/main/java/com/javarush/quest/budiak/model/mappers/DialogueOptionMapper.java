package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.entity.containers.dialogues.DialogueOption;
import com.javarush.quest.budiak.model.dto.DialogueOptionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class DialogueOptionMapper {
    private static final Logger LOGGER = LogManager.getLogger(DialogueOptionMapper.class);

    private DialogueOptionMapper() {
    }

    private static DialogueOptionDto mapDialogueOption(DialogueOption dialogueOption) {
        if (dialogueOption == null) {
            LOGGER.warn("Attempted to map null DialogueOption object");
            return null;
        }

        LOGGER.debug("Mapping DialogueOption to DialogueOptionDto: {}", dialogueOption);
        DialogueOptionDto dialogueOptionDto = new DialogueOptionDto();

        dialogueOptionDto.setId(dialogueOption.getId());
        dialogueOptionDto.setText(dialogueOption.getText());
        dialogueOptionDto.setNextDialogueId(dialogueOption.getNextDialogueId());
        dialogueOptionDto.setScore(dialogueOption.getScore());

        LOGGER.debug("Mapped DialogueOptionDto: {}", dialogueOptionDto);
        return dialogueOptionDto;
    }

    public static List<DialogueOptionDto> mapDialogueOptions(List<DialogueOption> dialogueOptions) {
        return dialogueOptions.stream().map(DialogueOptionMapper::mapDialogueOption).collect(Collectors.toList());
    }
}
