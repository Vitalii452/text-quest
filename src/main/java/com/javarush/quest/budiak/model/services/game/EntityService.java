package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.DtoMarker;

public interface EntityService<T extends DtoMarker> {

    T getDto(int entityId);

    void setRepository(int entityContainerId);
}
