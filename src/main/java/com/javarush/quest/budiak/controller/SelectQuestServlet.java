package com.javarush.quest.budiak.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.dto.QuestDto;
import com.javarush.quest.budiak.model.factories.GameServiceFactory;
import com.javarush.quest.budiak.model.services.GameService;
import com.javarush.quest.budiak.model.services.admin.QuestRemovalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/quests")
public class SelectQuestServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(SelectQuestServlet.class);
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession httpSession = req.getSession();
            GameService gameService = GameServiceFactory.createGameService(getServletContext());
            gameService.setQuestsContainer();
            QuestRemovalService questRemovalService = new QuestRemovalService(gameService, objectMapper, getServletContext());


            List<QuestDto> quests = gameService.getAllQuestsDto();
            httpSession.setAttribute("gameService", gameService);
            httpSession.setAttribute("deleteQuestService", questRemovalService);
            req.setAttribute("quests", quests);

            req.getRequestDispatcher("/WEB-INF/views/quests.jsp").forward(req, resp);
        } catch (Exception e) {
            LOGGER.error("Error occurred in SelectQuestServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}

