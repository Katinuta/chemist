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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>
        <c:import url="/css/footer.css"></c:import>
    </style>

</head>
<body>

<div class="container">
    <h2>
        <c:choose>
            <c:when test="${flagFind}"><fmt:message bundle="${bundle}" key="table.name.fmedicunes"/></c:when>
            <c:otherwise><fmt:message bundle="${bundle}" key="table.name.medicines"/></c:otherwise>
        </c:choose>
    </h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.price"/></th>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.balance"/></th>
            <th><fmt:message bundle="${bundle}" key="table.medicine.column.prescription"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medicine" items="${medicines}">
            <tr>
                <td>${medicine.name}</td>
                <td>${medicine.price}</td>
                <td>${medicine.quantityPackages}</td>
                <td>
                    <c:choose>
                        <c:when test="${medicine.isNeedRecipe}">
                            <fmt:message bundle="${bundle}" key="label.prescription.isneed"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message bundle="${bundle}" key="label.prescription.isnotneed"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <button class="btn btn-default" name="medicineForAdd" id="medicineForAdd" value="${medicine.medicineId}">
                        <fmt:message bundle="${bundle}" key="button.addtocart"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<c:if test="${ not flagFind}">
    <div class="container">
        <ul class="pagination">
            <c:forEach begin="1" end="${countpages}" step="1" var="i">
                <li class="page-item"><a class="page-link" href="/controller?command=nextpage&currentpage=${i}">${i}</a></li>
            </c:forEach>
        </ul>
    </div>
</c:if>

</body>
</html>