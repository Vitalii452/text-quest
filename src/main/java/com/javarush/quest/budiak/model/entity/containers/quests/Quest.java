package com.javarush.quest.budiak.model.entity.containers.quests;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

import java.util.Objects;

public class Quest extends AbstractEntity {

    private int id;
    private String name;
    private int locationsContainerId;
    private int endingsContainerId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationsContainerId() {
        return locationsContainerId;
    }

    public void setLocationsContainerId(int locationsContainerId) {
        this.locationsContainerId = locationsContainerId;
    }

    public int getEndingsContainerId() {
        return endingsContainerId;
    }

    public void setEndingsContainerId(int endingsContainerId) {
        this.endingsContainerId = endingsContainerId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Quest quest = (Quest) obj;
        return this.id == quest.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
