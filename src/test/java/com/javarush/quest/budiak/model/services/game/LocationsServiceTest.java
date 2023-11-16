package com.javarush.quest.budiak.model.services.game;

import com.javarush.quest.budiak.model.entity.containers.locations.Location;
import com.javarush.quest.budiak.model.dto.LocationDto;
import com.javarush.quest.budiak.model.factories.RepositoryFactory;
import com.javarush.quest.budiak.model.mappers.LocationMapper;
import com.javarush.quest.budiak.model.repositories.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LocationsServiceTest {

    @Mock
    private RepositoryFactory<Location> repositoryFactory;

    @Mock
    private Repository<Location> locationRepository;

    private LocationsService locationsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(repositoryFactory.createRepository(anyInt())).thenReturn(locationRepository);
        locationsService = new LocationsService(repositoryFactory);
        locationsService.setRepository(anyInt());
    }

    @Test
    void getDto_ValidEntityId_ShouldReturnMappedDto() {
        int locationId = 1;
        Location location = mock(Location.class);
        when(locationRepository.getById(locationId)).thenReturn(location);

        try (MockedStatic<LocationMapper> mockedStatic = Mockito.mockStatic(LocationMapper.class)) {
            LocationDto expectedDto = new LocationDto();
            mockedStatic.when(() -> LocationMapper.mapLocation(location)).thenReturn(expectedDto);

            LocationDto result = locationsService.getDto(locationId);

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void getFirstLocation_ValidContainerId_ShouldReturnFirstLocationDto() {
        int locationsContainerId = 1;
        locationsService.setRepository(locationsContainerId);

        Map<Integer, Location> locationsMap = new HashMap<>();
        Location location = mock(Location.class);
        locationsMap.put(1, location);
        when(locationRepository.getAll()).thenReturn(locationsMap);

        try (MockedStatic<LocationMapper> mockedStatic = Mockito.mockStatic(LocationMapper.class)) {
            LocationDto expectedDto = new LocationDto();
            mockedStatic.when(() -> LocationMapper.mapLocation(any())).thenReturn(expectedDto);

            LocationDto result = locationsService.getFirstLocation(locationsContainerId);

            assertEquals(expectedDto, result);
        }
    }

    @Test
    void getNextLocationDto_ValidCurrentLocationId_ShouldReturnNextLocationDto() {
        int currentLocationId = 1;
        Location nextLocation = mock(Location.class);
        when(locationRepository.getById(currentLocationId + 1)).thenReturn(nextLocation);

        try (MockedStatic<LocationMapper> mockedStatic = Mockito.mockStatic(LocationMapper.class)) {
            LocationDto expectedDto = new LocationDto();
            mockedStatic.when(() -> LocationMapper.mapLocation(nextLocation)).thenReturn(expectedDto);

            Optional<LocationDto> result = locationsService.getNextLocationDto(currentLocationId);

            assertTrue(result.isPresent());
            assertEquals(expectedDto, result.get());
        }
    }

    @Test
    void getDialoguesContainerByLocationDtoId_ValidLocationDtoId_ShouldReturnDialoguesContainerId() {
        int locationDtoId = 1;
        Location location = mock(Location.class);
        int expectedDialoguesContainerId = 10;
        when(location.getDialoguesContainerId()).thenReturn(expectedDialoguesContainerId);
        when(locationRepository.getById(locationDtoId)).thenReturn(location);

        int result = locationsService.getDialoguesContainerByLocationDtoId(locationDtoId);

        assertEquals(expectedDialoguesContainerId, result);
    }

    @Test
    void getAllLocations_ValidContainerId_ShouldReturnListOfLocations() {
        int locationsContainerId = 1;
        locationsService.setRepository(locationsContainerId);

        Map<Integer, Location> locationsMap = new HashMap<>();
        locationsMap.put(1, mock(Location.class));
        locationsMap.put(2, mock(Location.class));
        when(locationRepository.getAll()).thenReturn(locationsMap);

        List<Location> result = locationsService.getAllLocations(locationsContainerId);

        assertEquals(2, result.size());
    }


}
