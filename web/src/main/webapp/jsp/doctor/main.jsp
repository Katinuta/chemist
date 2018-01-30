<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>
        .flex-container {
            display: flex;
            justify-content: center;

        }
        form{
            margin-bottom: 0;
        }

    </style>


</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>
<c:choose>
    <c:when test="${empty clients}">
        <c:choose>
            <c:when test="${empty extending}">
                <c:import url="/controller">
                    <c:param name="command" value="doctorprescription"/>
                </c:import>
            </c:when>
            <c:otherwise>
                <c:import url="prescription.jsp"/>
            </c:otherwise>
        </c:choose>

    </c:when>
    <c:otherwise>
        <c:import url="client.jsp"/>
    </c:otherwise>

</c:choose>


<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>