<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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

    </style>
    <script type="application/javascript">

        $(document).ready(function () {

            $(".medicineForAdd").click(function () {
                var button = $(this);
                var medicine_id = button.val();

                $.ajax({
                    url: "/ajax",
                    type: "get",
                    data: {
                        medicine_id: medicine_id,
                        command: "add_product_to_cart"
                    },
                    success: function (response) {
                        var response = $.parseJSON(response);
                        $('#cartvalue').text(response.size);
                        button.text("Product was added");
                        button.addClass("disabled");
                    }
                });
            })

        });
        $(window).on('load', function () {
            $.ajax({
                    url: "/ajax",
                    type: "get",
                    data: {
                        command: "get_cart_products_id"
                    },
                    success: function (response) {
                        var response = $.parseJSON(response);
                        var idsArray = response.ids;
                        if (response.ids != null) {
                            idsArray.forEach(function (item, i, idsArray) {
                                var buttons = $('button.medicineForAdd').toArray();
                                buttons.forEach(function (button, i, buttons) {
                                    if (button.value == item) {
                                        button.textContent = "Product was added";
                                        button.classList.add("disabled");
                                    }
                                });
                            })
                        }
                    }
                }
            )
        });

    </script>
    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
</head>
<body>

<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>

<c:if test="${ not flagFind and empty prescriptions}">
    <c:if test="${  current_page ==null}">
        <c:set var="current_page" value="1"/>
    </c:if>
    <c:import url="/client">
        <c:param name="command" value="show_medicine_by_page"/>

        <c:param name="current_page" value="${current_page}"/>
    </c:import>
</c:if>

<c:if test="${flagFind}">
    <c:import url="medicines.jsp"/>
</c:if>

<c:if test="${not empty prescriptions }">
    <c:import url="prescriptions.jsp"/>
</c:if>
${error}

<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>
</body>
</html>