package com.javarush.quest.budiak.controller.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.services.GameService;
import com.javarush.quest.budiak.model.dto.DialogueDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/getNextDialogue")
public class NextDialogueServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(NextDialogueServlet.class);
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        GameService gameService = (GameService) httpSession.getAttribute("gameService");

        if (gameService == null) {
            LOGGER.error("GameService not found in session");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            return;
        }

        try {
            DialogueDto currentDialogueDto = (DialogueDto) httpSession.getAttribute("dialogueDto");
            if (currentDialogueDto == null) {
                LOGGER.warn("Current DialogueDto not found in session");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Current dialogue not set");
                return;
            }

            int dialogueOptionId = Integer.parseInt(req.getParameter("dialogueOptionId"));
            int nextDialogueId = gameService.getNextDialogueId(currentDialogueDto, dialogueOptionId);
            int score = gameService.getScoreForOption(currentDialogueDto, dialogueOptionId);

            DialogueDto nextDialogueDto = gameService.getDialogueDtoById(nextDialogueId);
            gameService.updateScore(score);

            httpSession.setAttribute("dialogueDto", nextDialogueDto);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            objectMapper.writeValue(resp.getWriter(), nextDialogueDto);
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid dialogue option ID", e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid dialogue option ID");
        } catch (Exception e) {
            LOGGER.error("Error occurred in NextDialogueServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}

