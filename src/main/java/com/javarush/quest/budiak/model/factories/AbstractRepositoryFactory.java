package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.entity.containers.Container;
import com.javarush.quest.budiak.model.factories.exceptions.NoSuchContainerException;
import com.javarush.quest.budiak.utils.Loader;
import com.javarush.quest.budiak.model.entity.AbstractEntity;
import com.javarush.quest.budiak.model.repositories.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepositoryFactory<T extends AbstractEntity, R extends Container<T>, P extends Repository<T>>
        implements RepositoryFactory<T> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractRepositoryFactory.class);
    protected final Loader<R> containerLoader;
    protected final ServletContext servletContext;

    public AbstractRepositoryFactory(Loader<R> containerLoader, ServletContext servletContext) {
        this.containerLoader = containerLoader;
        this.servletContext = servletContext;
    }

    public P createRepository(int containerId) {
        LOGGER.info("Creating repository for container ID: {}", containerId);
        R container = containerLoader.loadContainer(String.valueOf(containerId), servletContext)
                .orElseThrow(() -> new NoSuchContainerException("No container found for ID: " + containerId));

        LOGGER.debug("Container loaded: {}", container);
        return createSpecificRepository(convertContainerToMap(container));
    }

    protected Map<Integer, T> convertContainerToMap(Container<T> container) {
        LOGGER.debug("Converting container to map");
        Map<Integer, T> map = new HashMap<>();

        for (T item : container.getElements()) {
            map.put(item.getId(), item);
        }
        LOGGER.debug("Container converted to map: {}", map);
        return map;
    }

    protected abstract P createSpecificRepository(Map<Integer, T> map);
}
