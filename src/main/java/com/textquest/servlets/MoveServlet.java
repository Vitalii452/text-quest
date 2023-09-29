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

@WebServlet(name = "MoveServlet", value = "/move")
public class MoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        GameEngine gameEngine = (GameEngine) session.getAttribute("gameEngine");

        String direction = req.getParameter("direction");

        switch (direction) {
            case "Следующая локация": {
                gameEngine.moveToNextLocation();
                break;
            }
            case "Предыдущая локация": {
                gameEngine.moveToPreviousLocation();
                break;
            }
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/game.jsp");
        dispatcher.forward(req, resp);

    }
}
