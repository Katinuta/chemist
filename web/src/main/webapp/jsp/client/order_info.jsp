<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
            <c:import url="/css/order_info.css"/>
        </style>
        <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
    </head>
    <body>
        <nav class="navbar navbar-inverse ">
            <c:import url="navbar.jsp"/>
        </nav>
        <div class="container">
            <h2><fmt:message bundle="${bundle}" key="table.name.orderdetail"/></h2>
            <span class="error">${error}</span>
            <div class="container-fluid form">
                <table class="table">
                    <tr class="top">
                        <th><fmt:message bundle="${bundle}" key="table.column.number"/></th>
                        <td>${order.orderId}</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="top">
                        <th><fmt:message bundle="${bundle}" key="table.order.column.date"/></th>
                        <td>
                            <input type="date" data-date="" data-date-format="DD MMMM YYYY" value="${order.dateCreating}"
                                   readonly/>
                        </td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr class="top">
                        <th><fmt:message bundle="${bundle}" key="table.order.column.status"/></th>
                        <td>${fn:toLowerCase(fn:replace(order.status,'_', " "))}</td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.medicine.column.price"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.column.amount"/></th>
                        <th></th>
                    </tr>
                    <c:forEach var="detail" items="${order.details}">
                        <tr>
                            <td>${detail.medicine.name}</td>
                            <td>${detail.price}</td>
                            <td>${detail.quantity}</td>
                            <td> ${detail.amount}
                            </td>
                        </tr>

                    </c:forEach>
                    <tfoot>
                    <tr>
                        <td><fmt:message bundle="${bundle}" key="table.cart.foot.total"/></td>
                        <td></td>
                        <td></td>
                        <td>${order.total}</td>
                    </tr>
                    </tfoot>
                </table>

            </div>
            <div class="flex-container" >
                <form action="/chemist/client">
                    <input type="hidden" name="command" value="show_all_orders"/>
                    <button class="button"><fmt:message bundle="${bundle}" key="ref.orders"/></button>
                </form>
            </div>
        </div>
        <footer class="container-fluid text-center">
            <c:import url="/jsp/common/footer.jsp"/>
        </footer>
    </body>
</html>