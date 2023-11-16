package com.javarush.quest.budiak.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;

import java.util.List;

public class QuestData {

    @JsonProperty("locationName")
    private String locationName;

    @JsonProperty("descriptionParagraphs")
    private List<String> descriptionParagraphs;

    @JsonProperty("dialogues")
    private List<Dialogue> dialogues;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<String> getDescriptionParagraphs() {
        return descriptionParagraphs;
    }

    public void setDescriptionParagraphs(List<String> descriptionParagraphs) {
        this.descriptionParagraphs = descriptionParagraphs;
    }

    public List<Dialogue> getDialogues() {
        return dialogues;
    }

    public void setDialogues(List<Dialogue> dialogues) {
        this.dialogues = dialogues;
    }
}
