package com.javarush.quest.budiak.model.dto;

public class EndingDto implements DtoMarker {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
