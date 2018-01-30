<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn"
           uri="http://java.sun.com/jsp/jstl/functions" %>
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

    </style>

</head>
<body>
    <nav class="navbar navbar-inverse ">
        <c:import url="navbar.jsp"/>
    </nav>

    <div class="container">
        <h2><fmt:message bundle="${bundle}" key="ref.purchase"/></h2>
        <h2>${user.name} ${user.surname}</h2>
        <div class="container-fluid form">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th><fmt:message bundle="${bundle}" key="table.column.number"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.order.column.date"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.order.column.status"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.column.amount"/></th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <c:url var="orderdetail" value="/controller">
                            <c:param name="command" value="orderdetail"/>
                            <c:param name="orderId" value="${order.orderId}"/>
                        </c:url>
                        <td><a href="${orderdetail}">${order.orderId}</a></td>
                        <td> ${order.dateCreating}</td>
                        <td>${fn:toLowerCase(fn:replace(order.status,'_', " "))}</td>
                        <td>${order.total}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <footer class="container-fluid text-center">
        <c:import url="/jsp/common/footer.jsp"/>
    </footer>


</body>
</html>