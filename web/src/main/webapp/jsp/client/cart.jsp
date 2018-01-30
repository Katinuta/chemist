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
        .flex-container{
            display: flex;
            justify-content: center;

        }
        .message{
            margin-top: 7%;
        }
    </style>

    <script type="application/javascript">
        $(document).ready(function () {
                calculateSumTotal();
//            $('#empty').innerHTML="";
//            $('#suggest').innerHTML="";
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
                            amount = parseInt(response.amount);
                            if (message === undefined) {
                                $('#cartvalue').text(response.size);
                                $('#' + id).val(amount);
                                calculateSumProduct(id, amount);
                                calculateSumTotal();
                                $('.messagecount_' + id).text();
                            } else {
                                $('#cartvalue').text(response.size);
                                $('.messagecount_' + id).text(message);
                                $('#' + id).val(response.amount);
                                calculateSumProduct(id, amount);
                                calculateSumTotal();
                                if (amount == 0) {
                                    $('.quantity_' + id).re
                                }
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
                            console.log(response);
                            console.log(response.amount);
                            amount = parseInt(response.amount);
                            var message = response.message;
                            console.log(message);
                            if (message === undefined) {
                                $('.messagecount_' + id).text("");
                            } else {
                                $('.messagecount_' + id).text(message);
                            }
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
                                if(response.size!=0){
                                    calculateSumTotal();
                                }else{
                                    $('.detail').remove();
                                    $('#empty').text("<fmt:message bundle="${bundle}" key="message.cart.empty"/>");
                                    $('#suggest').text("<fmt:message bundle="${bundle}" key="message.cart.suggest"/>");
                                }

                            }
                        }
                    );
                }
            );
        });
    </script>
</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>
<c:choose>
    <c:when test="${ not empty cart}">
        <div class="container detail ">

            <h2><fmt:message bundle="${bundle}" key="message.cart.goods"/></h2>
            <div class="form-group">
                <span class="error">${error}</span>
            </div>
            <div class="container-fluid form ">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.medicine.column.price"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.column.amount"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="entry" items="${cart}">
                        <tr id="row_${entry.key.medicineId}">
                            <td>${entry.key.name}</td>
                            <td class="price_product">${entry.key.price}</td>
                            <td>
                                <div id="quantity_${entry.key.medicineId}">
                                    <c:choose>
                                        <c:when test="${entry.value!=0}">
                                            <button class="minus" value="${entry.key.medicineId}">
                                                <fmt:message bundle="${bundle}" key="table.minus"/>
                                            </button>
                                            <input type="text" name="amount" id="${entry.key.medicineId}"
                                                   value="${entry.value}"
                                                   readonly/>

                                            <button class="plus" value="${entry.key.medicineId}">
                                                <fmt:message bundle="${bundle}" key="table.plus"/>
                                            </button>
                                            <span class="messagecount_${entry.key.medicineId}"></span>
                                            <span class="messageprescrip_${entry.key.medicineId}"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span><fmt:message bundle="${bundle}" key="label.archive"/></span>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </td>

                            <td class="sum_product">${entry.value*entry.key.price}</td>
                            <td>
                                <button class="close" value="${entry.key.medicineId}">
                                    <i class="fa fa-close"></i>
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td><fmt:message bundle="${bundle}" key="table.cart.foot.total"/></td>
                        <td></td>
                        <td></td>
                        <td class="sum_total"></td>
                    </tr>
                    </tfoot>
                </table>
                <div class="container ">
                    <div class="flex-container" >
                        <form action="/controller">
                            <input type="hidden" value="buy" name="command"/>
                            <input type="submit" value="<fmt:message bundle="${bundle}" key="button.buy"/>"/>
                        </form>
                    </div>
                </div>

            </div>
        </div>
        <div class="container message">
            <div class="flex-container" >
                <h2 id="empty"></h2>

            </div>
            <div class="flex-container">
                <h4 id="suggest"></h4>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container message">
            <div class="flex-container" >
                <h2><fmt:message bundle="${bundle}" key="message.cart.empty"/></h2>

            </div>
            <div class="flex-container">
                <h4><fmt:message bundle="${bundle}" key="message.cart.suggest"/></h4>
            </div>
        </div>
    </c:otherwise>
</c:choose>




<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>