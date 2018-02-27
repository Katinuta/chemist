<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <c:import url="/css/success.css"/>
        </style>
        <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
    </head>
    <body>
        <nav class="navbar navbar-inverse ">
            <c:import url="/jsp/common/navbar.jsp"/>
        </nav>
        <div class="container">

            <div class="flex-container">
                <picture>
                    <img src="/chemist/image/success.png" style="width:auto;">
                </picture>
            </div>
            <div class="flex-container word">
                <h1><fmt:message bundle="${bundle}" key="message.user.success.create"/></h1>
            </div>
            <div class="flex-container">
                    <c:url var="to_sign_in" value="/user">
                        <c:param name="command" value="to_sign_in"></c:param>
                    </c:url>
                <button class="button"> <a href="${to_sign_in}"><fmt:message bundle="${bundle}" key="ref.signin"/></a></button>
            </div>
        </div>
        <footer class="container-fluid text-center">
            <c:import url="/jsp/common/footer.jsp"/>
        </footer>
    </body>
</html>