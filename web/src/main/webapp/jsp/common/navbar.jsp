<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty locale}">
        <fmt:setLocale value="${locale}"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>

<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="/index.jsp"><fmt:message bundle="${bundle}"
                                                               key="ref.brand"/></a>
    </div>

    <ul class="nav navbar-nav navbar-right">
        <li>
            <c:url var="register" value="/user">
                <c:param name="command" value="register"/>
            </c:url>
            <a href="${register}">
                <span class="glyphicon glyphicon-user"></span>
                <fmt:message bundle="${bundle}" key="ref.register"/></a>
        </li>
        <li>
            <c:url var="signin" value="/user">
                <c:param name="command" value="signin"></c:param>
            </c:url>
            <a href="${signin}">
                <span class="glyphicon glyphicon-user"></span>
                <fmt:message bundle="${bundle}" key="ref.signin"/></a>
        </li>
        <li>
            <c:url var="englishLang" value="/user">
                <c:param name="newlocale" value="en-EN"/>
                <c:param name="command" value="locale"/>
            </c:url>
            <a href="${englishLang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>

        <li>
            <c:url var="rusLang" value="/user">
                <c:param name="command" value="locale"/>
                <c:param name="newlocale" value="ru-RU"/>
            </c:url>
            <a href="${rusLang}"> <img src="/image/russia-flag.png"></a>
        </li>

    </ul>
</div>



