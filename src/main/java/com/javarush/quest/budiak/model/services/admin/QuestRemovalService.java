package com.javarush.quest.budiak.model.services.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.entity.containers.quests.QuestsContainer;
import com.javarush.quest.budiak.model.services.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuestRemovalService {

    private static final Logger LOGGER = LogManager.getLogger(QuestCreationService.class);

    private GameService gameService;
    private ObjectMapper objectMapper;
    private ServletContext servletContext;

    public QuestRemovalService(GameService gameService, ObjectMapper objectMapper, ServletContext servletContext) {
        this.gameService = gameService;
        this.objectMapper = objectMapper;
        this.servletContext = servletContext;
        LOGGER.info("QuestRemovalService initialized");
    }

    public void deleteQuestById(int questId) {
        Quest quest = gameService.getQuestById(questId);
        if (quest == null) {
            LOGGER.error("Quest with ID {} not found", questId);
            return;
        }

        List<Location> locations = gameService.getListLocationsByContainerId(quest.getLocationsContainerId());

        for (Location location : locations) {
            deleteContainer("/data/dialoguesContainers/DialoguesContainer_" + location.getDialoguesContainerId() + ".json");
        }

        deleteContainer("/data/locationsContainers/LocationsContainer_" + quest.getLocationsContainerId() + ".json");
        deleteContainer("/data/endingsContainers/EndingsContainer_" + quest.getEndingsContainerId() + ".json");
        updateQuestsContainerFile(quest);

        LOGGER.info("Quest with ID {} and all related containers have been deleted", questId);
    }

    private void deleteContainer(String filePath) {
        File file = new File(servletContext.getRealPath(filePath));
        if (file.exists()) {
            if (file.delete()) {
                LOGGER.info("Deleted file: {}", filePath);
            } else {
                LOGGER.error("Failed to delete file: {}", filePath);
            }
        } else {
            LOGGER.warn("File not found: {}", filePath);
        }
    }

    private void updateQuestsContainerFile(Quest newQuest) {
        String filePath = servletContext.getRealPath("/data/quests/QuestsContainer_1.json");
        File file = new File(filePath);

        try {
            QuestsContainer questsContainer = objectMapper.readValue(file, QuestsContainer.class);
            questsContainer.getElements().remove(newQuest);
            objectMapper.writeValue(file, questsContainer);
        } catch (IOException e) {
            LOGGER.error("Error accessing QuestsContainer file at " + filePath, e);
        }
    }

}
