package com.javarush.quest.budiak.model.entity.containers.dialogues;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

public class DialogueOption extends AbstractEntity {

    private int id;
    private String text;
    private int nextDialogueId;
    private int score;

    public DialogueOption() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNextDialogueId() {
        return nextDialogueId;
    }

    public void setNextDialogueId(int nextDialogueId) {
        this.nextDialogueId = nextDialogueId;
    }
}
