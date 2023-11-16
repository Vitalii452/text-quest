package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuestMapper {
    private static final Logger LOGGER = LogManager.getLogger(QuestMapper.class);

    private QuestMapper() {
    }

    public static QuestDto mapQuest(Quest quest) {
        if (quest == null) {
            LOGGER.warn("Attempted to map null Quest object");
            return null;
        }

        LOGGER.debug("Mapping Quest to QuestDto: {}", quest);
        QuestDto questDto = new QuestDto();

        questDto.setId(quest.getId());
        questDto.setName(quest.getName());

        LOGGER.debug("Mapped QuestDto: {}", questDto);
        return questDto;
    }
}
