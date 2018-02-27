<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
        <style>
            <c:import url="/css/main.css"/>
            <c:import url="/css/error.css"/>
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
                    <c:set var="main_page" value="/chemist/jsp/client/main.jsp"/>
                </c:when>
                <c:when test="${user.role=='DOCTOR'}">
                    <c:set var="main_page" value="/chemist/jsp/doctor/main.jsp"/>
                </c:when>
                <c:when test="${user.role=='PHARMACIST'}">
                    <c:set var="main_page" value="/chemist/jsp/pharmacist/main.jsp"/>
                </c:when>
                <c:otherwise>
                    <c:set var="main_page" value="/chemist/index.jsp"/>
                </c:otherwise>
            </c:choose>
            <button class="button"> <a href="${main_page}"><fmt:message bundle="${bundle}" key="ref.main.page"/></a></button>
        </div>
        <footer class="container-fluid text-center">
            <c:import url="/jsp/common/footer.jsp"/>
        </footer>
    </body>
</html>