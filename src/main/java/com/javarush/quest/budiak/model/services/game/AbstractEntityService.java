package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.DtoMarker;
import com.javarush.quest.budiak.model.entity.AbstractEntity;
import com.javarush.quest.budiak.model.repositories.Repository;
import com.javarush.quest.budiak.model.services.exceptions.RepositoryNotLoadedException;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractEntityService<T extends DtoMarker, R extends AbstractEntity> implements EntityService<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractEntityService.class);
    private final RepositoryFactory<R> repositoryFactory;
    protected Repository<R> repository;

    public AbstractEntityService(RepositoryFactory<R> repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
        LOGGER.info("AbstractEntityService initialized");
    }

    public abstract T getDto(int entityId);

    public R getEntityById(int entityId) {
        LOGGER.info("Receiving by ID: {}", entityId);
        if (repository == null) {
            LOGGER.error("Attempted to receiving entity without initializing the repository");
            throw new RepositoryNotLoadedException("Repository was not initialized. Set the repository before using the service.");
        }
        R entity = repository.getById(entityId);
        LOGGER.debug("Received entity: {}", entity);
        return entity;
    }

    @Override
    public void setRepository(int entityContainerId) {
        LOGGER.info("Setting repository for entity container ID: {}", entityContainerId);
        this.repository = repositoryFactory.createRepository(entityContainerId);
        LOGGER.debug("Repository set: {}", this.repository.getClass().getSimpleName());
    }
}
