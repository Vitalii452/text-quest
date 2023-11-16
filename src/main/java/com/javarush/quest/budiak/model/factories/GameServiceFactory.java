package com.javarush.quest.budiak.model.factories;

import com.javarush.quest.budiak.model.services.GameService;
import com.javarush.quest.budiak.model.services.game.EndingService;
import com.javarush.quest.budiak.utils.DialoguesContainerLoader;
import com.javarush.quest.budiak.utils.EndingsContainerLoader;
import com.javarush.quest.budiak.utils.LocationsContainerLoader;
import com.javarush.quest.budiak.model.services.game.DialoguesService;
import com.javarush.quest.budiak.model.services.game.LocationsService;
import com.javarush.quest.budiak.model.services.game.QuestService;
import com.javarush.quest.budiak.utils.QuestsContainerLoader;

import jakarta.servlet.ServletContext;

public class GameServiceFactory {

    public static GameService createGameService(ServletContext servletContext) {
        DialoguesService dialoguesService = new DialoguesService(new DialoguesRepositoryFactory(new DialoguesContainerLoader(), servletContext));
        LocationsService locationsService = new LocationsService(new LocationsRepositoryFactory(new LocationsContainerLoader(), servletContext));
        EndingService endingService = new EndingService(new EndingsRepositoryFactory(new EndingsContainerLoader(), servletContext));
        QuestService questService = new QuestService(new QuestsRepositoryFactory(new QuestsContainerLoader(), servletContext));

        return new GameService(dialoguesService, locationsService, endingService, questService);
    }
}
