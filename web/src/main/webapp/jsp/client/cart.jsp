<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="application/javascript" src="/chemist/js/cart.js"> </script>

        <style>
            <c:import url="/css/main.css"/>
            <c:import url="/css/cart.css"/>
            @import url('https://fonts.googleapis.com/css?family=Roboto');
        </style>
    </head>
    <body>
        <nav class="navbar navbar-inverse ">
            <c:import url="navbar.jsp"/>
        </nav>
        <c:choose>
            <c:when test="${ not empty cart}">
                <div class="container detail ">
                    <h2><fmt:message bundle="${bundle}" key="message.cart.goods"/></h2>
                    <div class="container-fluid form shadow ">
                        <div class="form-group">
                            <span class="error" id="ajax_error">${error} </span>
                        </div>
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
                        <div class="flex-container ">
                            <form action="/chemist/client">
                                <input type="hidden" value="buy" name="command"/>
                                <input type="submit" id="button"
                                       value="<fmt:message bundle="${bundle}" key="button.buy"/>"/>
                            </form>

                        </div>
                    </div>
                </div>
                <div class="flex-container-center">
                    <h2 id="empty"></h2>
                </div>
                <div class="flex-container-center">
                    <h4 id="suggest"></h4>
                </div>
            </c:when>
            <c:otherwise>
                <div class="container message">
                    <div class="flex-container-center">
                        <h2><fmt:message bundle="${bundle}" key="message.cart.empty"/></h2>
                    </div>
                    <div class="flex-container-center">
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