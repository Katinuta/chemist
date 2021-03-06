<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="application/javascript" src="/chemist/js/edit_password.js"></script>
    <style>
        <c:import url="/css/main.css"/>
        <c:import url="/css/edit_password.css"/>
    </style>

</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:choose>
        <c:when test="${user.role=='CLIENT'}">
            <c:import url="/jsp/client/navbar.jsp"/>
            <c:set var="action" value="/chemist/client"/>
        </c:when>
        <c:when test="${user.role=='DOCTOR'}">
            <c:import url="/jsp/doctor/navbar.jsp"/>
            <c:set var="action" value="/chemist/doctor"/>
        </c:when>
        <c:when test="${user.role=='PHARMACIST'}">
            <c:import url="/jsp/pharmacist/navbar.jsp"/>
            <c:set var="action" value="/chemist/pharmacist"/>
        </c:when>
    </c:choose>
</nav>

<div class="container">
    <div class="row">
        <div class="col-md-3 col-md-3 col-sm-3 col-xs-2"></div>

        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
            <h2><fmt:message bundle="${bundle}" key="table.name.changing.pass"/></h2>
            <div class="container-fluid form shadow">

                <div class="form-group">
                    <label for="login" class="required"><fmt:message bundle="${bundle}" key="input.login"/></label>
                    <input type="email" class="form-control" id="login" value="${user.login}" name="login" readonly/>
                </div>
                <form action="${action}" method="post">
                    <input name="command" type="hidden" value="update_password"/>
                    <div class="form-group">
                        <label for="password" class="required"><fmt:message bundle="${bundle}"
                                                                            key="input.password"/></label>
                        <span class="error">${error_password}</span>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" required
                               value="${fn:escapeXml(password)}"
                               placeholder=
                                <fmt:message bundle="${bundle}" key="placeholder.pass"/>/>
                    </div>
                    <div class="form-group">
                        <label for="new_password" id="l_new_password" class="required"><fmt:message
                                bundle="${bundle}" key="input.password.new"/></label>
                        <span class="error"></span>
                        <span class="error">${error_new_password}</span>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="new_password" name="new_password"
                               value="${fn:escapeXml(new_password)}"
                               required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}" placeholder=
                                <fmt:message bundle="${bundle}" key="placeholder.pass"/>/>
                        <span class="helper"><fmt:message bundle="${bundle}"
                                                          key="page.message.helper.password"/></span>
                    </div>
                    <div class="form-group last">
                        <label for="new_password_rep" class="required"><fmt:message bundle="${bundle}"
                                                                                    key="input.password.new.repeat"/></label>
                        <input type="password" class="form-control" id="new_password_rep"
                               pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}"
                               required placeholder=<fmt:message bundle="${bundle}" key="placeholder.pass"/>/>

                    </div>
                    <div class="flex-container">
                        <button type="submit" class="btn btn-default"><fmt:message bundle="${bundle}"
                                                                                   key="button.submit"/></button>
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