package com.javarush.quest.budiak.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        ObjectMapper objectMapper = new ObjectMapper();

        ctx.setAttribute("objectMapper", objectMapper);
    }
}
