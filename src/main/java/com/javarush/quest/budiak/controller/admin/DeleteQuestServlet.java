package com.javarush.quest.budiak.controller.admin;

import com.javarush.quest.budiak.model.services.admin.QuestRemovalService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/deleteQuest")
public class DeleteQuestServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(DeleteQuestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        QuestRemovalService questRemovalService = getDeleteQuestServiceFromSession(httpSession);

        if (questRemovalService == null) {
            LOGGER.error("DeleteQuestService not found in session");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Service not available");
            return;
        }

        try {
            int questId = parseQuestId(req.getParameter("questId"));
            questRemovalService.deleteQuestById(questId);
            LOGGER.info("Quest with ID {} has been deleted", questId);
            resp.sendRedirect("quests");
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error processing quest deletion: {}", e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    private QuestRemovalService getDeleteQuestServiceFromSession(HttpSession session) {
        return (QuestRemovalService) session.getAttribute("deleteQuestService");
    }

    private int parseQuestId(String questIdString) {
        if (questIdString == null || questIdString.isEmpty()) {
            throw new IllegalArgumentException("Quest ID is required");
        }
        try {
            return Integer.parseInt(questIdString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid quest ID");
        }
    }
}


