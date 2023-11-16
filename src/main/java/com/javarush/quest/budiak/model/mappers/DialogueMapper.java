package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.dto.DialogueDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DialogueMapper {

    private static final Logger LOGGER = LogManager.getLogger(DialogueMapper.class);

    private DialogueMapper() {
    }

    public static DialogueDto mapDialogue(Dialogue dialogue) {
        if (dialogue == null) {
            LOGGER.warn("Attempted to map null Dialogue object");
            return null;
        }

        LOGGER.debug("Mapping Dialogue to DialogueDto: {}", dialogue);
        DialogueDto dialogueDto = new DialogueDto();

        dialogueDto.setText(dialogue.getText());
        dialogueDto.setDialogueOptions(DialogueOptionMapper.mapDialogueOptions(dialogue.getDialogueOptions()));

        LOGGER.debug("Mapped DialogueDto: {}", dialogueDto);
        return dialogueDto;
    }
}
