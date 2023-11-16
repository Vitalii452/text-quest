package com.javarush.quest.budiak.controller.game;

import com.javarush.quest.budiak.model.dto.EndingDto;
import com.javarush.quest.budiak.model.services.GameService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/endGame")
public class EndGameServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(EndGameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GameService gameService = getGameServiceFromSession(req.getSession());

        if (gameService != null) {
            EndingDto endingDto = gameService.getEndingDto();
            req.setAttribute("endingDto", endingDto);
            LOGGER.info("EndingDto set for the game ending page");
        } else {
            LOGGER.warn("GameService not found in session on game ending page");
        }

        req.getRequestDispatcher("/WEB-INF/views/endGame.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeGameServiceFromSession(req.getSession());
        LOGGER.info("GameService removed from session and redirecting to quests page");
        resp.sendRedirect(req.getContextPath() + "/quests");
    }

    private GameService getGameServiceFromSession(HttpSession session) {
        return (GameService) session.getAttribute("gameService");
    }

    private void removeGameServiceFromSession(HttpSession session) {
        if (session.getAttribute("gameService") != null) {
            session.removeAttribute("gameService");
            LOGGER.debug("GameService removed from session");
        }
    }
}


