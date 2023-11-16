<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Концовка</title>
    <link rel="stylesheet" href="../../css/endGame.css">
</head>
<body>
<h2>${endingDto.text}</h2>
<form action="${pageContext.request.contextPath}/endGame" method="post">
    <input type="submit" value="Вернуться к списку квестов">
</form>
</body>
</html>
