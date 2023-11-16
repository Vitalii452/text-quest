package com.javarush.quest.budiak.model.services.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.entity.EndingsData;
import com.javarush.quest.budiak.model.entity.containers.dialogues.DialogueOption;
import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.entity.containers.locations.LocationsContainer;
import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.entity.QuestData;
import com.javarush.quest.budiak.model.entity.containers.dialogues.Dialogue;
import com.javarush.quest.budiak.model.entity.containers.dialogues.DialoguesContainer;
import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import com.javarush.quest.budiak.model.entity.containers.endings.EndingsContainer;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import com.javarush.quest.budiak.model.entity.containers.quests.QuestsContainer;
import com.javarush.quest.budiak.model.services.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestCreationService {

    private static final Logger LOGGER = LogManager.getLogger(QuestCreationService.class);

    private GameService gameService;
    private ObjectMapper objectMapper;
    private ServletContext servletContext;

    private Quest quest = new Quest();
    private LocationsContainer locationsContainer = new LocationsContainer();
    private EndingsContainer endingsContainer = new EndingsContainer();
    private List<DialoguesContainer> bufferedDialoguesContainers = new ArrayList<>();

    private int maxDialogueContainerId;


    public QuestCreationService(GameService gameService, ObjectMapper objectMapper, ServletContext servletContext) {
        this.gameService = gameService;
        this.objectMapper = objectMapper;
        this.servletContext = servletContext;
        this.maxDialogueContainerId = getNextContainerId() - 1;
        LOGGER.info("QuestCreationService initialized");
    }

    public void addQuestName(String name) {
        LOGGER.debug("Adding quest name: {}", name);
        int newQuestId = getNextQuestId();
        quest.setName(name);
        quest.setId(newQuestId);
        quest.setLocationsContainerId(newQuestId);
        quest.setEndingsContainerId(newQuestId);
        locationsContainer.setId(newQuestId);
        endingsContainer.setId(newQuestId);
        LOGGER.info("Quest name added: {}", quest.getName());
    }

    public int getNextQuestId() {
        int maxId = gameService.getAllQuestsDto().stream()
                .mapToInt(QuestDto::getId)
                .max()
                .orElse(0);
        return maxId + 1;
    }

    public void parseLocationAndDialoguesData(QuestData questData) {
        try {
            LOGGER.debug("Parsing quest data for location and dialogues");

            Location location = createLocation(questData);
            DialoguesContainer dialoguesContainer = createDialoguesContainer(questData, location);

            locationsContainer.getElements().add(location);
            bufferedDialoguesContainers.add(dialoguesContainer);

        } catch (Exception e) {
            LOGGER.error("Error parsing quest data", e);
        }
    }

    public void createEndingsAndSaveAllData(EndingsData endingsData) {
        try {
            List<Ending> endings = endingsData.getEndings();
            int endingId = 1;
            if (endingsContainer.getElements() == null) {
                endingsContainer.setElements(new ArrayList<>());
            }

            for (Ending ending : endings) {
                ending.setId(endingId++);
                endingsContainer.getElements().add(ending);
            }

            saveLocationContainerToJson();
            saveDialoguesContainersToJson();
            saveEndingsContainerToJson();
            updateQuestsContainerFile(quest);

            bufferedDialoguesContainers.clear();
        } catch (Exception e) {
            LOGGER.error("Error saving data and creating endings", e);
        }
    }

    private void saveEndingsContainerToJson() {
        String realPath = servletContext.getRealPath("/data/endingsContainers/EndingsContainer_" + endingsContainer.getId() + ".json");
        saveObjectToJson(endingsContainer, realPath);
    }

    private Location createLocation(QuestData questData) {
        Location location = new Location();

        if (locationsContainer.getElements() == null) {
            locationsContainer.setElements(new ArrayList<>());
        }

        location.setId(locationsContainer.getElements().size() + 1);
        location.setName(questData.getLocationName());
        location.setDescription(questData.getDescriptionParagraphs());
        location.setDialoguesContainerId(++maxDialogueContainerId);

        return location;
    }

    private DialoguesContainer createDialoguesContainer(QuestData questData, Location location) {
        DialoguesContainer dialoguesContainer = new DialoguesContainer();
        dialoguesContainer.setId(location.getDialoguesContainerId());

        int dialogueId = 1;
        int dialogueOptionId = 1;

        List<Dialogue> dialogues = new ArrayList<>();

        for (Dialogue dialogueData : questData.getDialogues()) {
            dialogues.add(createDialogue(dialogueData, dialogueId++, dialogueOptionId));
            dialogueOptionId += dialogueData.getDialogueOptions().size();
        }

        dialoguesContainer.setElements(dialogues);
        return dialoguesContainer;
    }

    private Dialogue createDialogue(Dialogue dialogueData, int dialogueId, int dialogueOptionId) {
        Dialogue newDialogue = new Dialogue();
        newDialogue.setId(dialogueId);
        newDialogue.setText(dialogueData.getText());


        List<DialogueOption> options = new ArrayList<>();

        for (DialogueOption optionData : dialogueData.getDialogueOptions()) {
            options.add(createDialogueOption(optionData, dialogueOptionId++));
        }

        newDialogue.setDialogueOptions(options);
        return newDialogue;
    }

    private DialogueOption createDialogueOption(DialogueOption optionData, int dialogueOptionId) {
        DialogueOption newOption = new DialogueOption();
        newOption.setId(dialogueOptionId);
        newOption.setText(optionData.getText());
        newOption.setNextDialogueId(optionData.getNextDialogueId());
        newOption.setScore(optionData.getScore());
        return newOption;
    }

    private void saveLocationContainerToJson() {
        String realPath = servletContext.getRealPath("/data/locationsContainers/LocationsContainer_" + locationsContainer.getId() + ".json");
        saveObjectToJson(locationsContainer, realPath);
    }

    private void saveDialoguesContainersToJson() {
        for (DialoguesContainer container : bufferedDialoguesContainers) {
            String realPath = servletContext.getRealPath("/data/dialoguesContainers/DialoguesContainer_" + container.getId() + ".json");
            saveObjectToJson(container, realPath);
        }
    }

    private void saveObjectToJson(Object obj, String filePath) {
        try {
            String json = objectMapper.writeValueAsString(obj);

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(json);
            }

            LOGGER.info("Object saved to JSON: {}", filePath);
        } catch (IOException e) {
            LOGGER.error("Error saving object to JSON: " + filePath, e);
        }
    }

    public int getNextContainerId() {
        String realPath = servletContext.getRealPath("/data/dialoguesContainers");
        File folder = new File(realPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null) {
            LOGGER.debug("No files in directory: {}", realPath);
            return 1;
        }

        int maxId = 0;
        Pattern pattern = Pattern.compile("DialoguesContainer_(\\d+)\\.json");
        LOGGER.debug("Checking files in directory: {}", realPath);

        for (File file : listOfFiles) {
            if (file.isFile()) {
                Matcher matcher = pattern.matcher(file.getName());
                if (matcher.find()) {
                    int id = Integer.parseInt(matcher.group(1));
                    maxId = Math.max(id, maxId);
                }
            }
        }

        return maxId + 1;
    }

    private void updateQuestsContainerFile(Quest newQuest) {
        String filePath = servletContext.getRealPath("/data/quests/QuestsContainer_1.json");
        File file = new File(filePath);

        try {
            QuestsContainer questsContainer = objectMapper.readValue(file, QuestsContainer.class);
            questsContainer.getElements().add(newQuest);
            objectMapper.writeValue(file, questsContainer);
        } catch (IOException e) {
            LOGGER.error("Error accessing QuestsContainer file at " + filePath, e);
        }
    }

    public Quest getQuest() {
        return quest;
    }
}
