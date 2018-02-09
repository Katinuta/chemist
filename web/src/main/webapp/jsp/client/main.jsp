<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            <c:import url="/css/main.css"/>
        </style>
        <script type="application/javascript" src="/js/medicines.js"></script>
        <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
    </head>
    <body>

        <nav class="navbar navbar-inverse ">
            <c:import url="navbar.jsp"/>
        </nav>

        <c:if test="${ not flagFind and empty prescriptions}">
            <c:if test="${  current_page ==null}">
                <c:set var="current_page" value="1"/>
            </c:if>
            <c:import url="/client">
                <c:param name="command" value="show_medicine_by_page"/>

                <c:param name="current_page" value="${current_page}"/>
            </c:import>
        </c:if>

        <c:if test="${flagFind}">
            <c:import url="medicines.jsp"/>
        </c:if>

        <c:if test="${not empty prescriptions or not empty prescriptions_message}">
            <c:import url="prescriptions.jsp"/>
        </c:if>


        <footer class="container-fluid text-center">
            <c:import url="/jsp/common/footer.jsp"/>
        </footer>
    </body>
</html>