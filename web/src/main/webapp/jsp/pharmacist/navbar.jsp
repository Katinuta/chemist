<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>


<c:choose>
    <c:when test="${not empty locale}">
        <fmt:setLocale value="${locale}"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>
<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="/jsp/pharmacist/main.jsp">
            <fmt:message bundle="${bundle}" key="ref.brand"/>
        </a>
    </div>

    <form class="navbar-form navbar-left" action="/controller">
        <input name="command" type="hidden" value="findmedicine"/>
        <div class="input-group">
            <input type="text" class="form-control" name="search"
                   placeholder="<fmt:message bundle="${bundle}" key="placeholder.search"/>"/>
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
        </div>
    </form>

    <ul class="nav navbar-nav navbar-right">

        <li>
            <div class="dropdown">
                <a class="dropbtn disabled"><span class="glyphicon glyphicon-user"></span> Your account</a>
                <div class="dropdown-content">
                    <c:url var="newmedicine" value="/controller">
                        <c:param name="command" value="newmedicine"/>
                    </c:url>

                    <a href="${newmedicine}"><fmt:message bundle="${bundle}" key="ref.newmedicine"/></a>

                    <c:url var="logout" value="/controller">
                        <c:param name="command" value="logout"/>
                    </c:url>
                    <a href="${logout}"><fmt:message bundle="${bundle}" key="ref.logout"/></a>
                </div>
            </div>
        </li>

        <c:url var="englishLang" value="/controller">
            <c:param name="newlocale" value="en-EN"/>
            <c:param name="command" value="locale"/>
            <c:param name="page" value="${pageContext.request.requestURI}"/>
        </c:url>

        <li>
            <a href="${englishLang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>


        <c:url var="rusLang" value="/controller">
            <c:param name="command" value="locale"/>
            <c:param name="newlocale" value="ru-RU"/>
            <c:param name="page" value="${pageContext.request.requestURI}"/>
        </c:url>
        <li>

            <a href="${rusLang}"> <img src="/image/russia-flag.png"></a>
        </li>
        <li>
            <ctg:hello-tag name="${user.name}"></ctg:hello-tag>
        </li>

    </ul>
</div>
