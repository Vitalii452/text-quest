package com.javarush.quest.budiak.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.entity.QuestData;
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

@WebServlet("/createLocationAndDialogues")
public class AddLocationsAndDialoguesServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AddLocationsAndDialoguesServlet.class);
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
        LOGGER.info("Servlet initialized with ObjectMapper");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        QuestCreationService questCreationService = (QuestCreationService) httpSession.getAttribute("createQuestService");

        try {
            QuestData questData = objectMapper.readValue(req.getInputStream(), QuestData.class);
            questCreationService.parseLocationAndDialoguesData(questData);
            httpSession.setAttribute("createQuestService", questCreationService);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("{\"redirect\":\"/createLocationAndDialogues.html\"}");
            LOGGER.info("Location and dialogues data processed successfully for quest {}", questData);
        } catch (IOException e) {
            LOGGER.error("Error processing location and dialogues data", e);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Некорректные данные квеста");
        }
    }
}


