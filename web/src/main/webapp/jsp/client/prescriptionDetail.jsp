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
</head>
<body>
<main>
    <div class="container">
        <h2><fmt:message bundle="${bundle}" key="table.name.prescriptiondetail"/></h2>
        ${error}
        <table class="table ">
            <%--<thead>--%>
            <tr>
                <td>
                    <fmt:message bundle="${bundle}" key="table.prescription.column.number"/>
                </td>
                <td>${prescription.prescriptionId}</td>
            </tr>
            <tr>
                <td>
                    <fmt:message bundle="${bundle}" key="table.prescription.column.doctor"/>
                </td>
                <td>
                    ${prescription.doctor.name} ${prescription.doctor.surname}
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/>
                </td>
                <td>
                   <input type="date" data-date="" data-date-format="DD MMMM YYYY" value="${prescription.dateBegin}" readonly/>
                </td>
            </tr>
            <tr>
                <td>
                    <fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/>
                </td>
                <td>
                    <input type="date" data-date="" data-date-format="DD MMMM YYYY" value="${prescription.dateEnd}" readonly/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <fmt:message bundle="${bundle}" key="table.medicine.column.name"/>
                </td>
                <td>
                    <fmt:message bundle="${bundle}" key="table.medicine.column.quantity"/>
                </td>
                <td>

                </td>
            </tr>
            <c:forEach var="detail" items="${prescription.details}" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${detail.medicine.name}</td>
                    <td>${detail.quantityPack}</td>
                    <td>
                        <c:choose>
                            <c:when test="${detail.status=='ACTIVE'}">
                                <fmt:message bundle="${bundle}" key="label.prescription.active"/>
                            </c:when>
                            <c:when test="${detail.status=='EXTAND'}">
                                <fmt:message bundle="${bundle}" key="label.prescription.extand"/>
                            </c:when>
                            <c:when test="${detail.status=='USED'}">
                                <fmt:message bundle="${bundle}" key="label.prescription.used"/>
                            </c:when>
                            <c:otherwise>
                               <fmt:message bundle="${bundle}" key="label.prescription.notactive"/>
                                <c:url var="extendprescripdetail" value="/controller">
                                    <c:param name="command" value="extendprescripdetail"/>
                                    <c:param name="prescripDetailId" value="${detail.detailId}"/>
                                </c:url>
                                <a href="${extendprescripdetail}">
                                    <fmt:message bundle="${bundle}" key="button.extend"/>
                                </a>

                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</main>

</body>
</html>