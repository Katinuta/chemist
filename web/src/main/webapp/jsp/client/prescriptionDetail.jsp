<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link href="https://fonts.googleapis.com/css?family=Quicksand" rel="stylesheet"  type='text/css'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title><fmt:message bundle="${bundle}" key="ref.brand"/></title>
    <style>
        <c:import url="/css/main.css"/>

        .table{
            margin-bottom: 20px;
            margin-top: 20px;
        }
        .table>tbody>tr.top>td,
        .table>tbody>tr.top>th{
            border-top: none;
        }
        input[type=date]{
            background-color: #f2f2f2;
            border: none;
        }
        .button{
            margin-top: 7%;

        }
        button{
            background-color: #00cc99;
            color: white;
            font-size: 16px;
            border: none;
            padding: 10px 10px;
            border-radius: 5px;
        }
        .flex-container{
            display: flex;
            justify-content: center;

        }

    </style>
</head>
<body>

<nav class="navbar navbar-inverse ">

    <c:import url="navbar.jsp"/>
</nav>
<div class="container">

    <h2><fmt:message bundle="${bundle}" key="table.name.prescriptiondetail"/></h2>
    ${error}
    <div class="container-fluid form">
        <table class="table">
            <tr class="top">
                <th ><fmt:message bundle="${bundle}" key="table.column.number"/></th>
                <td >${prescription.prescriptionId}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="top">
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.doctor"/></th>
                <td>${prescription.doctor.name} ${prescription.doctor.surname}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="top">
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/></th>
                <td>
                    <input type="date" data-date="" data-date-format="DD MMMM YYYY" value="${prescription.dateBegin}"
                           required
                           readonly/>
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr class="top">
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/></th>
                <td>
                    <input type="date" data-date="" data-date-format="DD MMMM YYYY" value="${prescription.dateEnd}"
                           readonly/>
                </td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <th><fmt:message bundle="${bundle}" key="table.column.number"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/></th>
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.status"/></th>
                <th></th>
            </tr>
            <c:forEach var="detail" items="${prescription.details}" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${detail.medicine.name}</td>
                    <td>${detail.quantityPack}</td>
                    <td>${fn:toLowerCase(detail.status)}</td>
                    <c:choose>
                        <c:when test="${detail.status=='INACTIVE'}">
                            <c:url var="extendprescripdetail" value="/client">
                                <c:param name="command" value="extendprescripdetail"/>
                                <c:param name="prescripDetailId" value="${detail.detailId}"/>
                            </c:url>
                            <td>
                                <button>
                                    <a href="${extendprescripdetail}">
                                        <fmt:message bundle="${bundle}" key="button.extend"/>
                                    </a>
                                </button>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>

    </div>
    <div class="flex-container" >
        <form action="/client">
            <input type="hidden" name="command" value="allclientprescription"/>
            <button class="button"><fmt:message bundle="${bundle}" key="ref.prescription"/></button>
        </form>
    </div>
</div>


<footer class="container-fluid text-center">
    <c:import url="/jsp/common/footer.jsp"/>
</footer>

</body>
</html>