package com.javarush.quest.budiak.model.dto;

public class DialogueOptionDto implements DtoMarker {

    private int id;
    private String text;
    private int nextDialogueId;
    private int score;

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

    public int getNextDialogueId() {
        return nextDialogueId;
    }

    public void setNextDialogueId(int nextDialogueId) {
        this.nextDialogueId = nextDialogueId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
