package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndingMapperTest {

    @Test
    public void mapEnding_withNullEnding_shouldLogWarningAndReturnNull() {
        EndingDto result = EndingMapper.mapEnding(null);

        assertNull(result);
    }

    @Test
    public void mapEnding_withValidEnding_shouldMapToEndingDto() {
        Ending ending = new Ending();
        ending.setText("Ending Text");

        EndingDto result = EndingMapper.mapEnding(ending);

        assertNotNull(result);
        assertEquals("Ending Text", result.getText());
    }

}