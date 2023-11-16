package com.javarush.quest.budiak.controller.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.quest.budiak.model.dto.LocationDto;
import com.javarush.quest.budiak.model.services.GameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@WebServlet("/getNextLocation")
public class NextLocationServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(NextLocationServlet.class);
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        this.objectMapper = (ObjectMapper) getServletContext().getAttribute("objectMapper");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        GameService gameService = (GameService) httpSession.getAttribute("gameService");

        if (gameService == null) {
            LOGGER.error("GameService not found in session");
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            return;
        }

        try {
            LocationDto currentLocationDto = (LocationDto) httpSession.getAttribute("locationDto");
            if (currentLocationDto == null) {
                LOGGER.warn("Current LocationDto not found in session");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Current location not set");
                return;
            }

            Optional<LocationDto> nextLocationDtoOpt = gameService.getNextLocationDto(currentLocationDto.getId());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            if (nextLocationDtoOpt.isPresent()) {
                LocationDto nextLocationDto = nextLocationDtoOpt.get();
                int dialoguesContainerId = gameService.getDialoguesContainerIdByLocationDtoId(nextLocationDto.getId());
                gameService.setDialoguesContainer(dialoguesContainerId);
                httpSession.setAttribute("locationDto", nextLocationDto);
                objectMapper.writeValue(resp.getWriter(), nextLocationDto);
            } else {
                objectMapper.writeValue(resp.getWriter(), Collections.singletonMap("endOfGame", true));
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred in NextLocationServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }
}

