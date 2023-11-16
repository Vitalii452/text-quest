package com.javarush.quest.budiak.controller;

import com.javarush.quest.budiak.config.AdminConfig;
import org.junit.jupiter.api.Test;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import static org.mockito.Mockito.*;

class LoginServletTest {
    @Test
    public void doGet_anyRequest_shouldRedirectToLoginPage() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        new LoginServlet().doGet(request, response);

        verify(response).sendRedirect("login.html");
    }

    @Test
    public void doPost_guestLogin_shouldSetSessionAndRedirectToQuests() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("guest")).thenReturn("true");
        when(request.getSession()).thenReturn(session);

        new LoginServlet().doPost(request, response);

        verify(session).setAttribute("isAdmin", false);
        verify(response).sendRedirect("quests");
    }

    @Test
    public void doPost_validAdminData_shouldSetSessionAndRedirectToQuests() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn(AdminConfig.USERNAME);
        when(request.getParameter("password")).thenReturn(AdminConfig.PASSWORD);
        when(request.getSession()).thenReturn(session);

        new LoginServlet().doPost(request, response);

        verify(session).setAttribute("isAdmin", true);
        verify(response).sendRedirect("quests");
    }

    @Test
    public void doPost_invalidData_shouldRedirectToLogin() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter("username")).thenReturn("wronguser");
        when(request.getParameter("password")).thenReturn("wrongpass");
        when(request.getSession()).thenReturn(session);

        new LoginServlet().doPost(request, response);

        verify(response).sendRedirect("login");
    }


}