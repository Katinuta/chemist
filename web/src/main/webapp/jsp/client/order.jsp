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

    <script type="application/javascript">
        $(document).ready(function () {
                calculateSumTotal();
            }
        );

        function calculateSumTotal() {
            var sum = 0;
            $('.sum_product').each(function () {
                sum += parseFloat($(this).text());
            });
            $('.sum_total').text(sum.toFixed(2));

            return sum;
        }

        function calculateSumProduct(id, amount) {
            var product = $('#row_' + id);
            var price = parseFloat(product.find('.price_product').text());
            product.find('.sum_product').text((price * amount).toFixed(2));
        }

        $(window).on('load', function () {
            $('.plus').click(function () {
                    var id = $(this).val();
                    var amount = parseInt($('#' + id).val());
                    amount++;
                    $.ajax({
                        url: "/controllerAjax",
                        type: "get",
                        data: {
                            id: id,
                            command: "increaseproduct",
                            amount: amount
                        },
                        success: function (response) {
                            var message = response.message;
                            if (message === undefined) {
                                $('#cartvalue').text(response.size);
                                $('#' + id).val(amount);
                                calculateSumProduct(id, amount);
                                calculateSumTotal();
                                $('.message_' + id).text();
                            } else {
                                $('#cartvalue').text(response.size);
                                $('.message_' + id).text(message);
                            }
                        }
                    });
                }
            );
            $('.minus').click(
                function () {
                    var id = $(this).val();
                    var amount = parseInt($('#' + id).val());
                    if (amount > 1) {
                        amount--;
                    }
                    $.ajax({
                        url: "/controllerAjax",
                        type: "get",
                        data: {
                            id: id,
                            command: "reduceproduct",
                            amount: amount
                        },
                        success: function (response) {
                            $('#' + id).val(amount);
                            calculateSumProduct(id, amount);
                            calculateSumTotal();
                            $('#cartvalue').text(response.size);
                        }
                    })
                }
            );
            $('.close').click(
                function () {
                    var button = $(this);
                    var id = button.val();
                    $.ajax(
                        {
                            url: "/controllerAjax",
                            type: "get",
                            data: {
                                id: id,
                                command: "deletemedicinecart"
                            },
                            success: function (response) {
                                button.parent().parent().remove();
                                $('#cartvalue').text(response.size);
                                calculateSumTotal();
                            }
                        }
                    );
                }
            );
        });
    </script>
</head>
<body>
<header>
    <nav class="navbar ">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/jsp/client/main.jsp">
                    <fmt:message bundle="${bundle}" key="ref.brand"/></a>
            </div>
            <ul class="nav navbar-nav">
                <c:url var="allclientprescription" value="/controller">
                    <c:param name="command" value="allclientprescription"/>
                </c:url>
                <li><a href="${allclientprescription}"><fmt:message bundle="${bundle}" key="ref.prescription"/></a></li>
                <c:url var="opencart" value="/controller">
                    <c:param name="command" value="opencart"/>
                </c:url>
                <li>
                    <a href="${opencart}" id="cart">
                        <i class="fa fa-cart-arrow-down"  style="font-size:40px;color:black"></i>
                    </a> <span  id="cartvalue" style="font-size: medium; margin-left: -20%;">${fn:length(cart)}</span>
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
    <table class="table table-hover">
        <thead>
        <tr>
            <td>
                <fmt:message bundle="${bundle}" key="table.column.number"/>
            </td>
            <td>${order.orderId}</td>
        </tr>
        <tr>
            <td>
                <fmt:message bundle="${bundle}" key="table.order.column.date"/>
            </td>
            <td>${order.dateCreating}</td>
        </tr>
        <tr>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.price"/></th>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/></th>
            <th><fmt:message bundle="${bundle}" key="table.column.amount"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="detail" items="${order.details}">
            <tr>
                <td>${detail.medicine.name}</td>
                <td >${detail.price}</td>
                <td>${detail.quantity}</td>
                <td> ${detail.amount}
                </td>

            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td><fmt:message bundle="${bundle}" key="table.cart.foot.total"/></td>
            <td></td>
            <td></td>
            <td>${order.total}</td>
        </tr>
        </tfoot>
    </table>
    <form action="/controller">
        <input type="hidden" value="buy" name="command"/>
        <input type="submit" value="<fmt:message bundle="${bundle}" key="button.buy"/>"/>
    </form>
</main>
<footer>
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>