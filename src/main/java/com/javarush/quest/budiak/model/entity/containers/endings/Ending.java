package com.javarush.quest.budiak.model.entity.containers.endings;

import com.javarush.quest.budiak.model.entity.AbstractEntity;

public class Ending extends AbstractEntity {

    private int id;
    private int minScore;
    private int maxScore;
    private String text;

    public Ending() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
