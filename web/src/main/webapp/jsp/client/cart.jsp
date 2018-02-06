<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>
        <c:import url="/css/cart.css"/>

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
            console.log(sum);
            console.log($('input[type=submit]'));
            if (sum == 0) {
                $('input[type=submit]').attr('disabled', 'disabled');
            }
            return sum;
        }

        function calculateSumProduct(id, amount) {
            var product = $('#row_' + id);
            var price = parseFloat(product.find('.price_product').text());
            product.find('.sum_product').text((price * amount).toFixed(2));
        }

        $(window).on('load', function () {

            $('.plus').click(function () {
                    var medicine_id = $(this).val();
                    var amount = parseInt($('#' + medicine_id).val());
                    amount++;
                    $.ajax({
                        url: "/ajax",
                        type: "get",
                        data: {
                            medicine_id: medicine_id,
                            command: "increase_product_amount_cart",
                            amount: amount
                        },
                        success: function (response) {
                            var response = $.parseJSON(response);
                            var message = response.message;
                            amount = parseInt(response.amount);
                            if (message === undefined) {
                                $('#cartvalue').text(response.size);
                                $('#' + medicine_id).val(amount);
                                calculateSumProduct(medicine_id, amount);
                                calculateSumTotal();
                                $('.messagecount_' + medicine_id).text();
                            } else {
                                $('#cartvalue').text(response.size);
                                $('.messagecount_' + medicine_id).text(message);
                                $('#' + medicine_id).val(response.amount);
                                calculateSumProduct(medicine_id, amount);
                                calculateSumTotal();
                                if (amount == 0) {
                                    $('#quantity_' + medicine_id + '>input').text('<fmt:message bundle="${bundle}" key="label.archive"/>');
                                }
                            }
                        }
                    });
                }
            );
            $('.minus').click(
                function () {
                    var medicine_id = $(this).val();
                    var amount = parseInt($('#' + medicine_id).val());
                    if (amount > 1) {
                        amount--;

                    }
                    $.ajax({
                        url: "/ajax",
                        type: "get",
                        data: {
                            medicine_id: medicine_id,
                            command: "reduce_product_amount_cart",
                            amount: amount
                        },
                        success: function (response) {
                            var response = $.parseJSON(response);
                            amount = parseInt(response.amount);
                            var message = response.message;
                            if (message === undefined) {
                                $('.messagecount_' + medicine_id).text("");
                            } else {
                                $('.messagecount_' + medicine_id).text(message);
                            }
                            $('#' + medicine_id).val(amount);

                            calculateSumProduct(medicine_id, amount);
                            calculateSumTotal();
                            $('#cartvalue').text(response.size);
                        }
                    })

                }
            );
            $('.close').click(
                function () {
                    var button = $(this);
                    var medicine_id = button.val();
                    $.ajax(
                        {
                            url: "/ajax",
                            type: "get",
                            data: {
                                medicine_id: medicine_id,
                                command: "delete_product_cart"
                            },
                            success: function (response) {
                                var response = $.parseJSON(response);
                                button.parent().parent().remove();
                                $('#cartvalue').text(response.size);
                                if (response.size != 0) {
                                    calculateSumTotal();
                                } else {
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
            <div class="container-fluid form shadow ">
                <table class="table">
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
                                            <div class="form-group">
                                                <c:set var="messagecount"
                                                       value="${requestScope['messagecount_'.concat(entry.key.medicineId)]}"/>
                                                <span class="error messagecount_${entry.key.medicineId}">${messagecount}</span>
                                                <c:set var="messageprescrip"
                                                       value="${requestScope['messageprescrip_'.concat(entry.key.medicineId)]}"/>
                                                <span class="error">${messageprescrip}</span>
                                            </div>
                                            <div class="form-group">
                                                <button class="minus" value="${entry.key.medicineId}">
                                                    <i class="fa fa-minus-square"></i>
                                                </button>
                                                <input type="number" step="1" name="amount" id="${entry.key.medicineId}"
                                                       value="${entry.value}"
                                                       readonly/>

                                                <button class="plus" value="${entry.key.medicineId}">
                                                    <i class="fa fa-plus-square" style=""></i>
                                                </button>

                                            </div>

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
                        <td></td>
                    </tr>
                    </tfoot>
                </table>
                <%--<div class="container ">--%>
                    <div class="flex-container right-justify">
                        <form action="/client">
                            <input type="hidden" value="buy" name="command"/>
                            <input type="submit" id="button"
                                   value="<fmt:message bundle="${bundle}" key="button.buy"/>"/>
                        </form>
                    <%--</div>--%>
                </div>
            </div>
        </div>
        <div class="container message">
            <div class="flex-container">
                <h2 id="empty"></h2>
            </div>
            <div class="flex-container">
                <h4 id="suggest"></h4>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container message">
            <div class="flex-container">
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