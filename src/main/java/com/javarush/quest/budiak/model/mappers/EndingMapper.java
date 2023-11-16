package com.javarush.quest.budiak.model.mappers;

import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndingMapper {
    private static final Logger LOGGER = LogManager.getLogger(EndingMapper.class);


    private EndingMapper() {
    }

    public static EndingDto mapEnding(Ending ending) {
        if (ending == null) {
            LOGGER.warn("Attempted to map null Ending object");
            return null;
        }
        LOGGER.debug("Mapping Ending to EndingDto: {}", ending);

        EndingDto endingDto = new EndingDto();
        endingDto.setText(ending.getText());

        LOGGER.debug("Mapped EndingDto: {}", endingDto);
        return endingDto;
    }
}
