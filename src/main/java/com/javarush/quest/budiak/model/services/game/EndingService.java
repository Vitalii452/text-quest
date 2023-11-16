package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.mappers.EndingMapper;
import com.javarush.quest.budiak.model.services.exceptions.NoEndingForThisScoreRangeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndingService extends AbstractEntityService<EndingDto, Ending> {

    private static final Logger LOGGER = LogManager.getLogger(EndingService.class);

    private int score = 0;

    public EndingService(RepositoryFactory<Ending> repositoryFactory) {
        super(repositoryFactory);
        LOGGER.info("EndingService initialized");
    }

    @Override
    public EndingDto getDto(int entityId) {
        return EndingMapper.mapEnding(getEntityById(entityId));
    }

    public EndingDto findAppropriateEnding() {
        LOGGER.info("Finding appropriate ending for score: {}", score);
        for (var ending : repository.getAll().values()) {
            if (score >= ending.getMinScore() && score <= ending.getMaxScore()) {
                LOGGER.debug("Appropriate ending found for score: {}", score);
                return getDto(ending.getId());
            }
        }

        LOGGER.error("No ending found for score: {}", score);
        throw new NoEndingForThisScoreRangeException("No ending available for score: " + score);
    }

    public void updateScore(int score) {
        this.score += score;
        LOGGER.info("Score updated. Current score: {}", this.score);
    }
}
