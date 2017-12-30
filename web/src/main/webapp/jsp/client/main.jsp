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
        <c:import url="/css/footer.css"></c:import>
    </style>
</head>
<body>
<header>
    <nav class="navbar ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/jsp/client/main.jsp"><fmt:message bundle="${bundle}" key="ref.brand"/></a>
            </div>
            <ul class="nav navbar-nav">
                <c:url var="allclientprescription" value="/controller">
                    <c:param name="command" value="allclientprescription"/>
                </c:url>
                <li><a href="${allclientprescription}"><fmt:message bundle="${bundle}" key="ref.prescription"/></a></li>
                <c:url var="openbasket" value="/controller">
                    <c:param name="command" value="openbasket"/>
                </c:url>
                <li><a href="${openbasket}" id="basket"><i class="fa fa-cart-arrow-down"
                                                           style="font-size:40px;color:black">

                </i></a>
                    <span style="font-size: medium; margin-left: -20%;">${fn:length(basket)}</span></li>

            </ul>
            <form class="navbar-form navbar-right" action="/controller">
                <input name="command" type="hidden" value="findmedicine"/>
                <div class="input-group">
                    <input type="text" name="name" class="form-control"
                           placeholder=<fmt:message bundle="${bundle}" key="placeholder.search"/>>
                    <div class="input-group-btn">
                        <button class="btn btn-default" type="submit">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </nav>
</header>
<main>
    <c:if test="${ not flagFind}">
        <c:import url="/controller">
            <c:param name="command" value="allmedicine"/>
        </c:import>
    </c:if>

    <c:if test="${not empty medicines }">
        <c:import url="medicine.jsp"/>
    </c:if>
    <c:if test="${not empty prescriptions }">
        <c:import url="prescription.jsp"/>
    </c:if>
    ${error}
</main>
<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>