<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            <c:import url="/css/main.css"/>
            <c:import url="/css/success.css"/>
        </style>
        <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
    </head>
    <body>

        <nav class="navbar navbar-inverse ">
            <c:import url="navbar.jsp"/>
        </nav>

        <div class="container" >

            <div class="flex-container" >
                <picture >
                    <img src="/chemist/image/success.png"  style="width:auto;">
                </picture>
            </div>
            <div class="flex-container word">
                <h2><fmt:message bundle="${bundle}" key="message.prescrip.create.success"/></h2>
            </div>

            <div class="flex-container" >
                <form>
                    <button class="button">
                        <a href="/chemist/jsp/doctor/main.jsp"><fmt:message bundle="${bundle}" key="ref.prescription"/></a></button>
                </form>
            </div>
        </div>
        <footer class="container-fluid text-center">
            <c:import url="/jsp/common/footer.jsp"/>
        </footer>
    </body>
</html>