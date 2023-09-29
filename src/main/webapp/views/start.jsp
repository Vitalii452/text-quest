<%--
  Created by IntelliJ IDEA.
  User: dalahan14
  Date: 28.09.2023
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Приветствие</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Caveat&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
    <body>
        <div class="container">
            <h1>Тёмные Эха Призраков</h1>
            <p class="appearance">
                В мире, где свет борется с тьмой, а зло прокрадывается в самые тёмные уголки душ, начинается ваше путешествие.
                Перед вами лежат дороги, ведущие к неведомым секретам, опасности и возможности познать истину, что скрыта за завесой мрака.
                В этом мире каждое решение имеет значение, и каждый шаг может быть последним.
            </p>
            <p class="appearance">
                Тьма окутала земли, и только отважные сердца могут восстановить баланс и вернуть свет в этот мир.
                Героические поступки и жестокие решения будут сопутствовать вам на этом пути.
                Но помните, не все то, что кажется злом, есть зло, и не все, что сверкает, есть свет.
            </p>
            <p class="appearance">
                Теперь, когда вы стоите на пороге этого мистического мира, время выбрать свой путь.
                Но прежде чем вы шагнете в неизведанное, подарите себе имя, пусть оно будет вашим щитом и мечом в этой битве между светом и тьмой.
            </p>
        </div>

    <form action="start" method="post">
        <input type="text" name="playerName" placeholder="Введите ваше имя" required>
        <input type="submit" value="Начать квест">
        </form>

        <script src="scripts/script.js"></script>
    </body>
</html>
