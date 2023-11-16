package com.javarush.quest.budiak.controller;

import com.javarush.quest.budiak.config.AdminConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Redirecting to login page");
        resp.sendRedirect("login.html");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String guest = req.getParameter("guest");
        String redirect = "quests";

        if (guest != null) {
            LOGGER.info("Logging in as a guest");
            httpSession.setAttribute("isAdmin", false);
        } else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (AdminConfig.USERNAME.equals(username) && AdminConfig.PASSWORD.equals(password)) {
                LOGGER.info("Admin successfully logged in");
                httpSession.setAttribute("isAdmin", true);
            } else {
                LOGGER.warn("Failed login attempt for username: {}", username);
                redirect = "login";
            }
        }
        resp.sendRedirect(redirect);
    }
}



