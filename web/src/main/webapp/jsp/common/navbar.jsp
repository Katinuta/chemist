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
        <a class="navbar-brand" href="/index.jsp"><fmt:message bundle="${bundle}" key="ref.brand"/></a>
    </div>

    <ul class="nav navbar-nav navbar-right">
        <li>
            <c:url var="to_sign_up" value="/user">
                <c:param name="command" value="to_sign_up"/>
            </c:url>
            <a href="${to_sign_up}">
                <span class="glyphicon glyphicon-user"></span>
                <fmt:message bundle="${bundle}" key="ref.register"/></a>
        </li>
        <li>
            <c:url var="to_sign_in" value="/user">
                <c:param name="command" value="to_sign_in"></c:param>
            </c:url>
            <a href="${to_sign_in}">
                <span class="glyphicon glyphicon-user"></span>
                <fmt:message bundle="${bundle}" key="ref.signin"/></a>
        </li>
        <li>
            <c:url var="eng_lang" value="/user">
                <c:param name="new_locale" value="en-EN"/>
                <c:param name="command" value="change_locale"/>
            </c:url>
            <a href="${eng_lang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>

        <li>
            <c:url var="rus_lang" value="/user">
                <c:param name="command" value="change_locale"/>
                <c:param name="new_locale" value="ru-RU"/>
            </c:url>
            <a href="${rus_lang}"> <img src="/image/russia-flag.png"></a>
        </li>

    </ul>
</div>



