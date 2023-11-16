<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Игра</title>
    <script src="../../scripts/dialogueHandler.js"></script>
    <link rel="stylesheet" href="../../css/game.css">
</head>
<body>
<h2 id="locationName">${locationDto.name}</h2>
<div id="locationDescription">
    <c:forEach items="${locationDto.descriptionParagraph}" var="paragraph">
        <p>${paragraph}</p>
    </c:forEach>
</div>

<div id="dialogueText">${dialogueDto.text}</div>
<div id="dialogueOptions">
    <c:forEach items="${dialogueDto.dialogueOptions}" var="option">
        <button onclick="loadDialogue(${option.id})">
                ${option.text}
        </button>
    </c:forEach>
</div>
</body>
</html>
