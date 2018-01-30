<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet" type="text/css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
</head>
<body>

<nav class="navbar navbar-inverse ">
    <c:import url="/jsp/common/navbar.jsp"/>
</nav>

<div class="container">

    <div class="flex-container">
        <picture>
            <img src="/image/success.png" style="width:auto;">
        </picture>
    </div>
    <div class="flex-container word">
        <h1><fmt:message bundle="${bundle}" key="message.user.success.create"/></h1>
    </div>

    <div class="flex-container">
        <button class="button">
            <c:url var="signin" value="/user">
                <c:param name="command" value="signin"></c:param>
            </c:url>
            <a href="${signin}"><fmt:message bundle="${bundle}" key="ref.signin"/></a></button>
    </div>
</div>
<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>