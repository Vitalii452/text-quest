package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.entity.containers.endings.Ending;
import com.javarush.quest.budiak.model.mappers.EndingMapper;
import com.javarush.quest.budiak.model.repositories.Repository;
import com.javarush.quest.budiak.model.services.exceptions.NoEndingForThisScoreRangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EndingServiceTest {

    @Mock
    private RepositoryFactory<Ending> repositoryFactory;

    @Mock
    private Repository<Ending> endingRepository;

    private EndingService endingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(repositoryFactory.createRepository(anyInt())).thenReturn(endingRepository);
        endingService = new EndingService(repositoryFactory);
        endingService.setRepository(anyInt());
    }

    @Test
    void getDto_ValidEntityId_ShouldReturnMappedDto() {
        int endingId = 1;
        Ending ending = mock(Ending.class);
        when(endingService.repository.getById(endingId)).thenReturn(ending);

        try (MockedStatic<EndingMapper> mockedStatic = Mockito.mockStatic(EndingMapper.class)) {
            EndingDto expectedDto = new EndingDto();
            mockedStatic.when(() -> EndingMapper.mapEnding(ending)).thenReturn(expectedDto);

            EndingDto result = endingService.getDto(endingId);

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void findAppropriateEnding_ValidScore_ShouldReturnEnding() {
        int score = 50;
        endingService.updateScore(score);

        Ending ending1 = mock(Ending.class);
        when(ending1.getMinScore()).thenReturn(0);
        when(ending1.getMaxScore()).thenReturn(40);

        Ending ending2 = mock(Ending.class);
        when(ending2.getMinScore()).thenReturn(40);
        when(ending2.getMaxScore()).thenReturn(66);
        when(ending2.getId()).thenReturn(2);

        Map<Integer, Ending> endingsMap = new HashMap<>();
        endingsMap.put(1, ending1);
        endingsMap.put(2, ending2);
        when(endingService.repository.getAll()).thenReturn(endingsMap);

        try (MockedStatic<EndingMapper> mockedStatic = Mockito.mockStatic(EndingMapper.class)) {
            EndingDto expectedDto = new EndingDto();

            when(endingService.getEntityById(2)).thenReturn(ending2);
            mockedStatic.when(() -> EndingMapper.mapEnding(ending2)).thenReturn(expectedDto);

            EndingDto result = endingService.findAppropriateEnding();

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void findAppropriateEnding_NoValidEnding_ShouldThrowException() {
        int score = 100;
        endingService.updateScore(score);

        Map<Integer, Ending> endingsMap = new HashMap<>();
        when(endingService.repository.getAll()).thenReturn(endingsMap);

        assertThrows(NoEndingForThisScoreRangeException.class, () -> endingService.findAppropriateEnding());
    }
}
