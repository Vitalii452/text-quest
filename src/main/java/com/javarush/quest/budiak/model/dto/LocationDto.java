package com.javarush.quest.budiak.model.dto;

import java.util.List;

public class LocationDto implements DtoMarker {

    private int id;
    private String name;
    private List<String> descriptionParagraphs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescriptionParagraph() {
        return descriptionParagraphs;
    }

    public void setDescriptionParagraph(List<String> descriptionParagraph) {
        this.descriptionParagraphs = descriptionParagraph;
    }
}
