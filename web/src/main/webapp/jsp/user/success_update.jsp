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
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet"  type="text/css">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>
        .button{
            margin-top: 7%;

        }
        button{
            background-color: #00cc99;
            color: white;
            font-size: 16px;
            border: none;
            padding: 10px 10px;
            border-radius: 5px;
        }
        .flex-container{
            display: flex;
            justify-content: center;

        }
    </style>

    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
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
        </c:choose>

    </nav>

    <div class="container" >

        <div class="flex-container" >
            <picture >
                <img src="/image/success.png" style="width:auto;">
            </picture>
        </div>
        <div class="flex-container word">
            <h1><fmt:message bundle="${bundle}" key="message.pasword.update.success"/></h1>
        </div>

        <div class="flex-container" >
                <c:choose>
                    <c:when test="${user.role=='CLIENT'}">
                        <button class="button">
                            <a href="/chemist/jsp/client/main.jsp"><fmt:message bundle="${bundle}" key="ref.main.page"/></a>
                        </button>
                    </c:when>
                    <c:when test="${user.role=='DOCTOR'}">
                        <button class="button">
                            <a href="/chemist/jsp/doctor/main.jsp"><fmt:message bundle="${bundle}" key="ref.main.page"/></a>
                        </button>
                    </c:when>
                    <c:when test="${user.role=='PHARMACIST'}">
                        <button class="button">
                            <a href="/chemist/jsp/pharmacist/main.jsp"><fmt:message bundle="${bundle}" key="ref.main.page"/></a>
                        </button>
                    </c:when>
                </c:choose>

        </div>
    </div>
    <footer class="container-fluid text-center">
        <c:import url="/jsp/common/footer.jsp"/>
    </footer>
</body>
</html>