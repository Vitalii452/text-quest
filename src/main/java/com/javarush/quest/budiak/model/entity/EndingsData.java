package com.javarush.quest.budiak.model.entity;

import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import java.util.List;

public class EndingsData {
    private List<Ending> endings;

    public EndingsData() {
    }

    public List<Ending> getEndings() {
        return endings;
    }

    public void setEndings(List<Ending> endings) {
        this.endings = endings;
    }
}
