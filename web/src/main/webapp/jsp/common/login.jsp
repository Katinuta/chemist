<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="ru-RU"/>
<c:if test="${not empty locale}">
    <fmt:setLocale value="${locale}" />
</c:if>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">

    <style>
        <c:import url="/css/style.css"/>

    </style>
</head>
<body>
<header>
    <nav>
        <a href="" id="main">Chemist</a>
        <%--<a href="#home">Home</a>--%>
        <a class="right" href="/controller?command=register">Sing up</a>
        <%--<a class="right" href="#sign_in">Sign in</a>--%>

    </nav>
</header>
<%--<c:import url="../admin/header.jspf"/>--%>
<main>
    <div class="form" >
        <form action="controller" method="get">
            <input name="command" type="hidden" name="login" value="login" />
            <label for="login"><fmt:message bundle="${bundle}" key="input.login"/></label>
            <input type="text" name="login" id="login" required placeholder=<fmt:message bundle="${bundle}" key="placeholder.login"/>/>
            <label for="password"><fmt:message bundle="${bundle}" key="input.password"/></label>
            <input type="password" name="password" id="password" required placeholder=<fmt:message bundle="${bundle}" key="placeholder.pass"/>/>
            <input type="submit" value=<fmt:message bundle="${bundle}" key="button.submit"/> />
        </form>
    </div>
    <ctg:info-tag/>
</main>
<footer>

</footer>
</body>
</html>