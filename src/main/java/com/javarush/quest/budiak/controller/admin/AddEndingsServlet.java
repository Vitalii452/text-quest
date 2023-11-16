package com.javarush.quest.budiak.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.entity.EndingsData;
import com.javarush.quest.budiak.model.services.admin.QuestCreationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addEndings")
public class AddEndingsServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AddEndingsServlet.class);
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("createEndings.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        QuestCreationService questCreationService = (QuestCreationService) httpSession.getAttribute("createQuestService");

        if (questCreationService == null) {
            LOGGER.error("CreateQuestService not found in session");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Service error occurred");
            return;
        }

        try {
            EndingsData endingsData = objectMapper.readValue(req.getInputStream(), EndingsData.class);
            questCreationService.createEndingsAndSaveAllData(endingsData);
            httpSession.removeAttribute("createQuestService");
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"redirect\":\"/quests\"}");
        } catch (IOException e) {
            LOGGER.error("Error parsing endings data", e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid endings data");
        }
    }
}

