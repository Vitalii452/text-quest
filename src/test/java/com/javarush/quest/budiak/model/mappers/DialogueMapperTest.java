package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.dto.DialogueDto;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class DialogueMapperTest {
    @Test
    public void mapDialogue_withNullDialogue_shouldLogWarningAndReturnNull() {
        DialogueDto result = DialogueMapper.mapDialogue(null);

        assertNull(result);
    }

    @Test
    public void mapDialogue_withValidDialogue_shouldMapToDialogueDto() {
        Dialogue dialogue = new Dialogue();
        dialogue.setText("Some text");
        dialogue.setDialogueOptions(Collections.emptyList());

        DialogueDto result = DialogueMapper.mapDialogue(dialogue);

        assertNotNull(result);
        assertEquals("Some text", result.getText());
        assertTrue(result.getDialogueOptions().isEmpty());
    }
}