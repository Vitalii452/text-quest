package com.javarush.quest.budiak.model.dto;

import java.util.List;

public class DialogueDto implements DtoMarker {

    private String text;
    private List<DialogueOptionDto> dialogueOptions;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DialogueOptionDto> getDialogueOptions() {
        return dialogueOptions;
    }

    public void setDialogueOptions(List<DialogueOptionDto> dialogueOptions) {
        this.dialogueOptions = dialogueOptions;
    }
}
