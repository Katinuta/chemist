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
        <c:import url="/css/footer.css"></c:import>
    </style>
    <script type="application/javascript">
        $(document).ready(function () {

            $("#medicineForAdd").click(function () {
                var button = $(this);
                var medicineForAdd = button.val();

                $.ajax({
                    url: "/controllerAjax",
                    type: "get",
                    data: {
                        medicineForAdd: medicineForAdd,
                        command: "addproduct"
                    },
                    success: function (response) {
                        $('#cartvalue').text(response.size);
                        button.text("Product was added");

                        button.addClass("disabled");
                    }
                });
            })
        });
    </script>
</head>
<body>
<header>
    <nav class="navbar ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/jsp/client/main.jsp"><fmt:message bundle="${bundle}"
                                                                                 key="ref.brand"/></a>
            </div>
            <ul class="nav navbar-nav">
                <c:url var="allclientprescription" value="/controller">
                    <c:param name="command" value="allclientprescription"/>
                </c:url>
                <li><a href="${allclientprescription}"><fmt:message bundle="${bundle}" key="ref.prescription"/></a></li>
                <c:url var="clientpurchases" value="/controller">
                    <c:param name="command" value="clientpurchases"/>
                </c:url>
                <li><a href="${clientpurchases}"><fmt:message bundle="${bundle}" key="ref.purchase"/></a></li>
                <c:url var="opencart" value="/controller">
                    <c:param name="command" value="opencart"/>
                </c:url>
                <li>
                    <a href="${opencart}" id="cart">
                        <i class="fa fa-cart-arrow-down" style="font-size:40px;color:black"></i>
                    </a> <span id="cartvalue" style="font-size: medium; margin-left: -20%;">${fn:length(cart)}</span>
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
<main>
    <div class="container">
        <h2>
            <fmt:message bundle="${bundle}" key="ref.purchase"/>
        </h2>
        <h2>
            ${user.name} ${user.surname}
        </h2>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message bundle="${bundle}" key="table.order.column.number"/></th>
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
                    <td>
                            ${order.status}
                    </td>
                    <td>
                            ${order.total}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


</main>


<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>