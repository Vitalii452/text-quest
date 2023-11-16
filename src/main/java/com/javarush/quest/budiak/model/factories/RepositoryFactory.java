package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.entity.AbstractEntity;
import com.javarush.quest.budiak.model.repositories.Repository;

public interface RepositoryFactory<T extends AbstractEntity> {

    Repository<T> createRepository(int containerId);
}
