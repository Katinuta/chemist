<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<%--<fmt:setLocale value="ru-RU" scope="session"/>--%>
<%--<c:if test="${not empty locale}">--%>
    <%--<fmt:setLocale value="${locale}" />--%>
<%--</c:if>--%>
<%--<c:if test="${ empty locale}">--%>
    <%--<fmt:setLocale value="ru-RU" scope="session"/>--%>
<%--</c:if>--%>
<c:choose>
    <c:when test="${not empty locale}">
        <fmt:setLocale value="${locale}" />
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en-EN" scope="session"/>
    </c:otherwise>
</c:choose>
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
        <a href="" id="main"><fmt:message bundle="${bundle}" key="ref.brand"/></a>
        <a class="right" href="/controller?command=register"><fmt:message bundle="${bundle}" key="ref.register"/></a>
        <c:url  var="englishLang" value="/controller">
            <c:param name="newlocale" value="en-EN"/>
            <c:param name="command" value="locale"/>
            <c:param name="page" value="${pageContext.request.requestURI}"/>
        </c:url>
        <a class="right" href="${englishLang}">en</a>
        <c:url var="rusLang" value="/controller">
            <c:param name="command" value="locale"/>
            <c:param name="newlocale" value="ru-RU"/>
            <c:param name="page" value="${pageContext.request.requestURI}"/>
        </c:url>
        <a class="right" href="${rusLang}">ru</a>

    </nav>
</header>
<%--<c:import url="../admin/header.jspf"/>--%>
<main>
    <%--${pageContext.request.localAddr}--%>

    <div class="form" >
        <form action="controller" method="post">
            <input name="command" type="hidden" name="login" value="login" />
            <label for="login"><fmt:message bundle="${bundle}" key="input.login"/></label>
            <input type="text" name="login" id="login" required placeholder=<fmt:message bundle="${bundle}" key="placeholder.login"/>/>
            <label for="password"><fmt:message bundle="${bundle}" key="input.password"/></label>
            <input type="password" name="password" id="password" required placeholder=<fmt:message bundle="${bundle}" key="placeholder.pass"/>/>
            <input type="submit" value=<fmt:message bundle="${bundle}" key="button.submit"/> />
        </form>
    </div>
        <c:choose>
            <c:when test="${  currentpage ==null}">
                <c:out value="1"/>
            </c:when>
            <%--<c:otherwise>--%>
            <%--<c:param name="currentpage" value="1"/>--%>
            <%--</c:otherwise>--%>
        </c:choose>
    <%--<ctg:info-tag/>--%>
</main>
<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>