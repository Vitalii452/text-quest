package com.textquest.servlets;

import com.textquest.game.GameEngine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "WelcomeServlet", value = "/start")
public class WelcomeServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(WelcomeServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/start.jsp");
        dispatcher.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String playerName = request.getParameter("playerName");
        GameEngine gameEngine = new GameEngine(playerName);
        session.setAttribute("gameEngine", gameEngine);
        session.setAttribute("playerName", playerName);

        LOGGER.info("doPost was called. GameEngine : " + ((gameEngine != null) ? "exists" : "doesnt exist"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/game.jsp");
        dispatcher.forward(request, response);
    }
}

