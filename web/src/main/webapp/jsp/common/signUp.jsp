<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script  type="application/javascript">
        $(document).ready(function () {
            $('#password').change(
                function () {
                    var password = $(this).val();
                    var passwordRepeat = $('#password_rep').val();
                    if (password != passwordRepeat) {
                        var spanError = $('#l_password + span.error');

                        spanError.text("<fmt:message bundle="${bundle}" key="label.passwords.mismatch"/>");
                        $('#password_rep').setCustomValidity("<fmt:message bundle="${bundle}" key="label.passwords.warning"/>");
                    } else {
                        $('#l_password + span.error').text("");
                        $('#password_rep').setCustomValidity('');
                    }
                }
            );
            $('#password_rep').change(
                function () {
                    var password = $("#password").val();
                    var passwordRepeat = $(this).val();

                    if (password != passwordRepeat) {
                        var spanError = $('#l_password + span.error');


                        spanError.text("<fmt:message bundle="${bundle}" key="label.passwords.mismatch"/>");
                        $('#password_rep').setCustomValidity("<fmt:message bundle="${bundle}" key="label.passwords.warning"/>");
                    } else {
                        $('#l_password + span.error').text("");
                        $('#password_rep').setCustomValidity('');
                    }
                }
            );

        });
    </script>

    <style>
        <c:import url="/css/style.css"/>
        <c:import url="/css/singUp.css"/>
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
            <h2><fmt:message bundle="${bundle}" key="ref.register"/></h2>
            <div class="container-fluid form">
                <form action="controller" method="post">
                    <input name="command" type="hidden" value="createuser"/>
                    <div class="form-group">
                        <label for="name" class="required"><fmt:message bundle="${bundle}" key="input.name"/></label>
                        <span class="error">${error_name}</span>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" value="${name}"
                        pattern="(^[A-Za-z]{2,}\\s*$)|(^[А-Яа-яЁё]{2,}\\s*$)"  required
                               placeholder=<fmt:message bundle="${bundle}" key="placeholder.name"/>/>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.name"/></span>
                    </div>
                    <div class="form-group">
                        <label for="surname" class="required"><fmt:message bundle="${bundle}"
                                                                           key="input.surname"/></label>
                        <span class="error">${error_surname}</span>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" id="surname" name="surname" value="${surname}"
                        required pattern="(^[A-Za-z]{2,}\\-?[A-Za-z]{2,}$)|(^[А-Яа-яЁё]{2,}\\-?[А-Яа-яЁё]{2,}$)"
                               placeholder=<fmt:message bundle="${bundle}"
                                                        key="placeholder.surname"/>/>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.name"/></span>
                    </div>
                    <div class="form-group">
                        <label for="account" class="required"><fmt:message bundle="${bundle}"
                                                                           key="input.account"/></label>
                        <span class="error">${error_account}</span>
                    </div>
                    <div class="form-group">

                        <input class="form-control" id="account" name="account" value="${account}"
                               type="text" type="number"  min="0" step="0.01" required
                               placeholder= <fmt:message bundle="${bundle}"  key="placeholder.account"/>/>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.account"/></span>
                    </div>
                    <div class="form-group">
                        <label for="phone" class="required"><fmt:message bundle="${bundle}"
                                                                         key="input.phone"/></label>
                        <span class="error">${error_phone}</span>
                    </div>
                    <div class="form-group">

                        <input class="form-control" id="phone" name="phone" value="${phone}"
                        type="number"pattern="^375(44|29|33|25)\d{7}"         required
                               placeholder= <fmt:message bundle="${bundle}"
                                                         key="placeholder.phone"/>/>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.phone"/></span>
                    </div>
                    <div class="form-group">
                        <label for="login" class="required"><fmt:message bundle="${bundle}" key="input.login"/></label>
                        <span class="error">${error_login}</span>
                    </div>
                    <div class="form-group">
                        <input type="email" name="login" value="${login}" required
                               class="form-control" id="login" placeholder="<fmt:message bundle="${bundle}"
                                                                                         key="placeholder.login"/>"/>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.login"/></span>

                    </div>
                    <div class="form-group">
                        <label for="password" class="required" id="l_password"><fmt:message bundle="${bundle}"
                                                                                            key="input.password"/></label>
                        <span class="error">${error_password}</span>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" value="${password}"
                               required pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}"
                               placeholder=
                               <fmt:message bundle="${bundle}" key="placeholder.pass"/>/>
                        <span class="helper"><fmt:message bundle="${bundle}" key="page.message.helper.password"/></span>
                    </div>
                    <div class="form-group">
                        <label for="password_rep" class="required"><fmt:message bundle="${bundle}"
                                                                                key="input.password.repeat"/></label>
                        <input type="password" class="form-control" id="password_rep"
                               pattern="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}" required
                               placeholder= <fmt:message bundle="${bundle}"
                                                         key="placeholder.pass"/>/>

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