package com.javarush.quest.budiak.controller.game;

import com.javarush.quest.budiak.model.services.GameService;
import com.javarush.quest.budiak.model.dto.DialogueDto;
import com.javarush.quest.budiak.model.dto.LocationDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/game")
public class StartGameServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(StartGameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        GameService gameService = (GameService) httpSession.getAttribute("gameService");

        if (gameService == null) {
            LOGGER.error("GameService not found in session");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Service error occurred");
            return;
        }

        try {
            int questId = Integer.parseInt(req.getParameter("questId"));
            int locationsContainerId = gameService.getLocationsContainerIdByQuestDtoId(questId);
            int endingsContainerId = gameService.getEndingsContainerIdByQuestDtoId(questId);

            gameService.setEndingContainerById(endingsContainerId);
            LocationDto locationDto = gameService.getFirstLocationDto(locationsContainerId);
            int dialoguesContainerId = gameService.getDialoguesContainerIdByLocationDtoId(locationDto.getId());
            DialogueDto dialogueDto = gameService.getFirstDialogueDto(dialoguesContainerId);

            httpSession.setAttribute("questId", questId);
            httpSession.setAttribute("locationDto", locationDto);
            httpSession.setAttribute("dialogueDto", dialogueDto);

            req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid quest ID format", e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid quest ID");
        } catch (Exception e) {
            LOGGER.error("Error processing game start", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error starting game");
        }
    }
}

