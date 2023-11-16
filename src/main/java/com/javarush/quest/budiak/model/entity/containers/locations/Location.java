package com.javarush.quest.budiak.model.entity.containers.locations;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.List;

public class Location extends AbstractEntity {

    private int id;
    private String name;
    private List<String> descriptionParagraphs;
    private int dialoguesContainerId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public Location() {
    }

    public String getName() {
        return name;
    }

    public List<String> getDescriptionParagraphs() {
        return descriptionParagraphs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(List<String> descriptionParagraphs) {
        this.descriptionParagraphs = descriptionParagraphs;
    }

    public int getDialoguesContainerId() {
        return dialoguesContainerId;
    }

    public void setDialoguesContainerId(int dialoguesContainerId) {
        this.dialoguesContainerId = dialoguesContainerId;
    }
}
