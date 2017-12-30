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
<div class="container">
    <h2><fmt:message bundle="${bundle}" key="ref.prescription"/></h2>

    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message bundle="${bundle}" key="table.prescription.column.number"/></th>
            <th><fmt:message bundle="${bundle}" key="table.prescription.column.doctor"/></th>
            <th><fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/></th>
            <th><fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="prescription" items="${prescriptions}">
            <tr>
                <c:url var="detailprescription" value="/controller">
                    <c:param name="command" value="detailprescription"/>
                    <c:param name="prescription" value="${prescription.prescriptionId}"/>
                </c:url>
                <td><a href="${detailprescription}">${prescription.prescriptionId}</a></td>
                <td>${prescription.doctor.name} ${prescription.doctor.surname}</td>
                <td> <fmt:formatDate value="${prescription.dateBegin}"/></td>
                <td>${prescription.dateEnd}</td>
                <td>
                    <c:choose>
                        <c:when test="${prescription.isActive}">
                            <fmt:message bundle="${bundle}" key="label.prescription.active"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${bundle}" key="label.prescription.notactive"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>