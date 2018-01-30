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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <c:import url="/css/main.css"/>
        input[type=date]:not(.end) {
            background-color: #f2f2f2;
            border: none;
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
</head>
<body>
<div class="container">
    <h2><fmt:message bundle="${bundle}" key="ref.prescription"/></h2>
    <c:choose>
        <c:when test="${not empty prescriptions}">
            <div class="container-fluid form">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.number"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.client"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="prescription" items="${prescriptions}">
                        <form action="/doctor" method="post">
                            <input type="hidden" name="command" value="approveExtendPrescrip">
                            <tr>
                                <c:url var="detailprescription" value="/doctor">
                                    <c:param name="command" value="openprescription"/>
                                    <c:param name="prescriptionId" value="${prescription.prescriptionId}"/>
                                </c:url>
                                <td><a href="${detailprescription}">${prescription.prescriptionId}</a>
                                    <span class="error">${error_prescriptionId}</span>
                                </td>
                                <td>${prescription.client.name} ${prescription.client.surname}</td>
                                <td><input type="date" name="dateBegin" class="begin" data-date=""
                                           data-date-format="DD MMMM YYYY" value="${prescription.dateBegin}" required readonly/>
                                    <span class="error">${error_dateBegin}</span>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${prescription.status=='EXTEND'}">
                                            <input type="date" name="dateEnd" class="end" value="${prescription.dateEnd}" required />
                                            <span class="error"></span>
                                            <span class="error">${error_dateEnd}</span>
                                    </c:when>
                                    <c:otherwise>
                                             <input type="date" name="dateEnd" class="begin" value="${prescription.dateEnd}" required readonly/>
                                    </c:otherwise>
                                    </c:choose>

                                <td>
                                    <c:choose>
                                        <c:when test="${prescription.status=='EXTEND'}">
                                            <fmt:message bundle="${bundle}" key="label.prescription.extend"/>
                                            <button name="prescriptionId" value="${prescription.prescriptionId}">
                                                <fmt:message bundle="${bundle}" key="button.extend"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            ${fn:toLowerCase(prescription.status)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>

                    </tbody>
                </table>
            </div>

        </c:when>
        <c:otherwise>
            <div class="flex-container word">
                <h3>${message}</h3>
            </div>
        </c:otherwise>
    </c:choose>


</div>
</body>
</html>