<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${not empty locale}">
        <fmt:setLocale value="${locale}"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        <c:import url="/css/style.css"/>
        <c:import url="/css/login.css"/>
    </style>
    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-3 col-md-3 col-sm-3 col-xs-2"></div>

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
            <div class="container-fluid form shadow">
                <form action="user" method="post">
                    <input name="command" type="hidden" value="sign_in"/>
                    <div class="form-group">
                        <label for="login"><fmt:message bundle="${bundle}" key="input.login"/></label>
                        <span class="error">${error_login}</span>
                    </div>
                    <div class="form-group">
                        <input  type="email"   class="form-control" id="login"
                                placeholder="<fmt:message bundle="${bundle}" key="placeholder.login"/>"
                               name="login" value="${login}" required/>
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message bundle="${bundle}" key="input.password"/></label>
                        <span class="error">${error_password}</span>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password"  value="${password}"
                               required  placeholder=  <fmt:message bundle="${bundle}"  key="placeholder.pass"/>/>
                    </div>
                    <div class="form-group">
                        <span class="error">${error}</span>
                    </div>
                    <div class="flex-container">
                        <button type="submit" class="btn btn-default"><fmt:message bundle="${bundle}"
                                                                                   key="button.signin"/></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-3 col-md-3 col-sm-3 col-xs-2"></div>
    </div>
</div>

<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>