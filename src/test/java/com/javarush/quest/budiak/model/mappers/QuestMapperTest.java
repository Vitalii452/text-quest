package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.entity.containers.quests.Quest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestMapperTest {
    @Test
    public void mapQuest_withNullQuest_shouldLogWarningAndReturnNull() {
        QuestDto result = QuestMapper.mapQuest(null);

        assertNull(result);
    }

    @Test
    public void mapQuest_withValidQuest_shouldMapToQuestDto() {
        Quest quest = new Quest();
        quest.setId(1);
        quest.setName("Epic Quest");

        QuestDto result = QuestMapper.mapQuest(quest);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Epic Quest", result.getName());
    }

}