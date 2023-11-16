package com.javarush.quest.budiak.model.entity.containers.dialogues;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.List;

public class Dialogue extends AbstractEntity {

    private int id;
    private String text;
    private List<DialogueOption> dialogueOptions;

    public Dialogue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DialogueOption> getDialogueOptions() {
        return dialogueOptions;
    }

    public void setDialogueOptions(List<DialogueOption> dialogueOptions) {
        this.dialogueOptions = dialogueOptions;
    }
}
