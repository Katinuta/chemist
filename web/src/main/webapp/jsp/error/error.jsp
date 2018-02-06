<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>error</title>
    <style>
        <c:import url="/css/main.css"/>
        .button {
            margin-top: 7%;

        }

        button {
            background-color: #00cc99;
            color: white;
            font-size: 16px;
            border: none;
            padding: 10px 10px;
            border-radius: 5px;
        }

        .flex-container {
            display: flex;
            justify-content: center;

        }

        .container {
            margin-top: 7%;
        }

        button:hover {
            background-color: #009973;
            color: white;
        }

        button > a, button > a:hover {
            text-decoration: none;
            color: white;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:choose>
        <c:when test="${user.role=='CLIENT'}">
            <c:import url="/jsp/client/navbar.jsp"/>
        </c:when>
        <c:when test="${user.role=='DOCTOR'}">
            <c:import url="/jsp/doctor/navbar.jsp"/>
        </c:when>
        <c:when test="${user.role=='PHARMACIST'}">
            <c:import url="/jsp/pharmacist/navbar.jsp"/>
        </c:when>
        <c:otherwise>
            <c:import url="/jsp/common/navbar.jsp"/>
        </c:otherwise>
    </c:choose>
</nav>

    <div class="flex-container ">
        <h1>${message}</h1>
    </div>
<div class="flex-container">

    <c:choose>
        <c:when test="${user.role=='CLIENT'}">
            <c:set var="main_page" value="/jsp/client/main.jsp"/>
        </c:when>
        <c:when test="${user.role=='DOCTOR'}">
            <c:set var="main_page" value="/jsp/doctor/main.jsp"/>
        </c:when>
        <c:when test="${user.role=='PHARMACIST'}">
            <c:set var="main_page" value="/jsp/pharmacist/main.jsp"/>
        </c:when>
        <c:otherwise>
            <c:set var="main_page" value="/index.jsp"/>
        </c:otherwise>
    </c:choose>
    <button class="button"> <a href="${main_page}"><fmt:message bundle="${bundle}" key="ref.main.page"/></a></button>
</div>
${error}

<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>