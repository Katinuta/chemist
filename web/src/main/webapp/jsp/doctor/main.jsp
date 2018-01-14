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
    <script src="../../js/jquery.toastmessage.js"></script>


</head>
<body>
<header>
    <nav class="navbar ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/jsp/pharmacist/main.jsp"><fmt:message bundle="${bundle}" key="ref.brand"/></a>
            </div>
            <ul class="nav navbar-nav">
                <c:url var="newmedicine" value="/controller">
                    <c:param name="command" value="newprescription"/>
                </c:url>
                <li>
                    <a href="${newmedicine}"><fmt:message bundle="${bundle}" key="ref.newprescription"/></a>
                </li>

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
${message}
<main>
        <c:import url="/controller">
            <c:param name="command" value="doctorprescription"/>
        </c:import>
</main>
<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>