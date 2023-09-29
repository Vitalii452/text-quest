<%@ page import="com.textquest.game.GameEngine" %>
<%@ page import="com.textquest.game.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Игра</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat&display=swap" rel="stylesheet">
</head>
    <body>
        <%
            GameEngine gameEngine = (GameEngine) session.getAttribute("gameEngine");
            Location currentLocation = gameEngine.getGameWorld().getCharacter().getCurrentLocation();
            request.setAttribute("currentLocation", currentLocation);
        %>
        <h1 style="margin-top: 50px">Локация: ${currentLocation.name}</h1>
        <div class="container">
            <p>${currentLocation.description}</p>
        </div>
        <form action="move" method="post">
            <input type="submit" name="direction" value="Предыдущая локация">
            <input type="submit" name="direction" value="Следующая локация">
        </form>
    </body>
</html>
