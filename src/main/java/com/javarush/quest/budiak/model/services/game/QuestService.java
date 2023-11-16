package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.mappers.QuestMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class QuestService extends AbstractEntityService<QuestDto, Quest> {

    private static final Logger LOGGER = LogManager.getLogger(QuestService.class);


    public QuestService(RepositoryFactory<Quest> repositoryFactory) {
        super(repositoryFactory);
        LOGGER.info("QuestService initialized");
    }

    @Override
    public QuestDto getDto(int entityId) {
        return QuestMapper.mapQuest(getEntityById(entityId));
    }

    public List<QuestDto> getAllQuestsDto() {
        return repository.getAll().values().stream().map(QuestMapper::mapQuest).collect(Collectors.toList());
    }

    public int getLocationsContainerIdByQuestDtoId(int questDtoId) {
        int locationsContainerId = getEntityById(questDtoId).getLocationsContainerId();
        LOGGER.debug("Received locations container ID: {}", locationsContainerId);
        return locationsContainerId;
    }

    public int getEndingContainerIdByQuestDtoId(int questDtoId) {
        int endingsContainerId = getEntityById(questDtoId).getEndingsContainerId();
        LOGGER.debug("Received endings container ID: {}", endingsContainerId);
        return endingsContainerId;
    }
}
