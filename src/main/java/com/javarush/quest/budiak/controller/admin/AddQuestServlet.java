package com.javarush.quest.budiak.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.services.GameService;
import com.javarush.quest.budiak.model.services.admin.QuestCreationService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/addQuest")
public class AddQuestServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AddQuestServlet.class);
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        GameService gameService = getGameServiceFromSession(httpSession);

        if (gameService == null) {
            LOGGER.error("Game service not found in session");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Game service not available");
            return;
        }

        String questName = req.getParameter("questName");
        if (questName == null || questName.trim().isEmpty()) {
            LOGGER.warn("Empty or null quest name received");
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quest name is required");
            return;
        }

        QuestCreationService questCreationService = createQuestService(gameService);
        questCreationService.addQuestName(questName);

        LOGGER.info("Quest name '{}' added to CreateQuestService", questName);

        httpSession.setAttribute("createQuestService", questCreationService);
        req.getRequestDispatcher("createLocationAndDialogues.html").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Forwarding to createQuest.html");
        req.getRequestDispatcher("createQuest.html").forward(req, resp);
    }

    private GameService getGameServiceFromSession(HttpSession session) {
        return (GameService) session.getAttribute("gameService");
    }

    private QuestCreationService createQuestService(GameService gameService) {
        return new QuestCreationService(gameService, objectMapper, getServletContext());
    }
}


