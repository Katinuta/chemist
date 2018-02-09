<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="application/javascript" src="/js/doctor.js"></script>
        <style>
            <c:import url="/css/main.css"/>
            <c:import url="/css/clients.css"/>
        </style>
        <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
    </head>
    <body>
        <nav class="navbar navbar-inverse ">
            <c:import url="navbar.jsp"/>
        </nav>
        <c:choose>
            <c:when test="${empty clients}">
                <c:choose>
                    <c:when test="${empty extending}">
                        <c:import url="/doctor">
                            <c:param name="command" value="show_doctor_prescriptions"/>
                        </c:import>
                    </c:when>
                    <c:otherwise>
                        <c:import url="prescriptions.jsp"/>
                    </c:otherwise>
                </c:choose>

            </c:when>
            <c:otherwise>
                <c:import url="clients.jsp"/>
            </c:otherwise>
        </c:choose>

        <footer class="container-fluid text-center">
            <c:import url="/jsp/common/footer.jsp"/>
        </footer>
    </body>
</html>