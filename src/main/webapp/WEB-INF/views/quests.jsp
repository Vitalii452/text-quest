<%@ page import="com.javarush.quest.budiak.model.dto.QuestDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список доступных квестов</title>
    <link rel="stylesheet" href="../../css/quests.css">
    <script>
        function stopPropagation(event) {
            event.stopPropagation();
        }
    </script>
</head>
<body>
<h2>Список Квестов</h2>
<ul>
    <%
        List<QuestDto> quests = (List<QuestDto>) request.getAttribute("quests");
        boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
        for(QuestDto quest : quests) {
    %>
    <li class="quest-item" onclick="location.href='game?questId=<%= quest.getId() %>';">
        <span><%= quest.getName() %></span>
        <% if (isAdmin) { %>
        <form class="delete-quest-form" action="deleteQuest" method="post" onclick="stopPropagation(event);">
            <input type="hidden" name="questId" value="<%= quest.getId() %>">
            <button type="submit">Удалить квест</button>
        </form>
        <% } %>
    </li>
    <% } %>
</ul>
<% if (isAdmin) { %>
<a href="addQuest" id="createQuestButton">Создать новый квест</a>
<% } %>
</body>
</html>
