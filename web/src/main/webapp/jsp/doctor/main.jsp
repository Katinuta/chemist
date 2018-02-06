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
        input[type=date]:not(.end) {
            background-color: inherit;
            border: none;
        }
        .table>tbody>tr>td, .table>tfoot>tr>td {
            padding: 5px;
            vertical-align: middle;
        }
        .center{
            text-align: center;
        }
        .form-control{
            width: 55%;
        }
        .table>tbody{
            font-size: 14px;
        }
        .table-presrip{
            margin-bottom: 5%;
        }
        button {
            background-color: #00cc99;
            color: white;
            font-size: 14px;
            border: none;
            padding: 10px 10px;
            border-radius: 5px;
        }
        button:hover{
            text-decoration: none;
            color: white;
            background-color: #009973;

        }
        form{
            margin-bottom: 0;
        }
        .clients{
            padding-left: 5%;
            padding-right: 5%;
        }
        .flex-container {
            display: flex;
            justify-content: center;

        }

    </style>

    <script type="application/javascript">
        $(document).ready(function () {
            $("input.end").change(function () {
                var dateEnd = $(this).val();
                var dateBegin = $(".begin").val();
                var now = new Date();
                var day = now.getDate();
                if (day < 10) day = '0' + day;
                var month = now.getMonth() + 1;
                if (month < 10) month = '0' + month;
                var dateNow = now.getFullYear() + "-" + month + "-" + day;
                if (dateEnd < dateNow) {
                    $("input.end:focus  ~ span.end").text("Date end can not be less than date now")
                }
                else {
                    $("input.end:focus ~ span.end").text("");
                }
            });
        });

    </script>
    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
</head>
<body>
<nav class="navbar navbar-inverse ">
    <c:import url="navbar.jsp"/>
</nav>
<c:choose>
    <c:when test="${empty clients}">
        <c:choose>
            <c:when test="${empty extending}">
                <c:import url="/doctor">
                    <c:param name="command" value="show_doctor_prescriptions"/>
                </c:import>
            </c:when>
            <c:otherwise>
                <c:import url="prescriptions.jsp"/>
            </c:otherwise>
        </c:choose>

    </c:when>
    <c:otherwise>
        <c:import url="clients.jsp"/>
    </c:otherwise>

</c:choose>


<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>