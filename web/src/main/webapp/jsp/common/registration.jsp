<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="ru-RU"/>
<c:if test="${not empty locale}">
    <fmt:setLocale value="${locale}"/>
</c:if>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <script>
        <c:import url="/js/registration.js"/>
    </script>
    <style>
        <c:import url="/css/registration.css"/>
    </style>
</head>
<body>
<header>
    <nav id="nav_bar">
        <a href="">Chemist</a>
        <a href="#home">Home</a>
        <a class="right active" href="#sign_up">Sing up</a>
        <a class="right" href="#sign_in">Sign in</a>
    </nav>
</header>
<main>
    <h1>Registration user</h1>
    ${error}
    <div class="form">
        <form action="controller" id="myform" method="post">
            <input name="command" type="hidden" value="createuser"/>
            <div class="group">
                <input type="text" name="name" id="name" pattern="[A-Za-zА-Яа-яЁё]{2,}+" required oninput="validate()">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label for="name" class="required">First name</label>
                <span class="helper">Only letter</span>
            </div>
            <div class="group">
                <input type="text" name="surname" pattern="[A-Za-zА-Яа-яЁё]{2,}+" required oninput="validate()">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="required">Surname</label>
                <span class="helper">Only letter</span>
            </div>
            <div class="group">
                <input type="number" name="account" required min="0" step="0.01">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="required">Account</label>
                <span class="helper">Balance of account</span>
            </div>
            <div class="group">
                <input type="text" name="phone" pattern="^375(44|29|33)\d{7}" required oninput="validate()">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="required">Phone</label>
                <span class="helper">375 XX XXX XX XX</span>
            </div>
            <div class="group">
                <input type="text" name="login" pattern="[A-z]{1}\w{4,}" required oninput="validate()">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="required">Login</label>
                <span class="helper">Contatins letters,numbers, "_".First letter.At least 5 symbols. </span>
            </div>
            <div class="group">
                <input type="password" name="password" id="password" pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}"
                       required
                       oninput="checkPasswords()">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label for="password" class="required">Password</label>
                <span class="helper">At least 6 symbols and has 1 letter in uppercase, 1 letter, 1 number</span>
            </div>
            <div class="group">
                <input type="password" name="repPassword" id="repPassword"
                       pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}" required
                       oninput="checkPasswords()">
                <span class="highlight"></span>
                <span class="bar"></span>
                <label class="required" id="labelRepPassword">Repeat password</label>
                <span class="helper">At least 6 symbols and has 1 letter in uppercase, 1 letter, 1 number</span>
            </div>
            <div class="group" id="emailDiv" oninput="validate()">
                <input type="email" name="email" id="email" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label for="email" id="labelEmail" class="required">E-mail</label>
                <span class="helper">Real email with "@" and "."</span>
            </div>
            <input type="button" value="Add e-mail" id="addfields" onclick="addfield()"/>
            <input type="submit" value="Submit">
        </form>
    </div>
</main>
<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>