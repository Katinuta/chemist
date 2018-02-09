<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h2><fmt:message bundle="${bundle}" key="ref.prescription"/></h2>
    <c:if test="${ not empty prescriptions_message}">
        <h2>${prescriptions_message}</h2>
    </c:if>
    <div class="container-fluid form">
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.number"/></th>
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.doctor"/></th>
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/></th>
                <th><fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/></th>
                <th><fmt:message bundle="${bundle}" key="table.order.column.status"/></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="prescription" items="${prescriptions}">
                <tr>
                    <c:url var="show_prescription_info" value="/client">
                        <c:param name="command" value="show_prescription_info"/>
                        <c:param name="prescription_id" value="${prescription.prescriptionId}"/>
                    </c:url>
                    <td><a href="${show_prescription_info}">${prescription.prescriptionId}</a></td>
                    <td>${prescription.doctor.name} ${prescription.doctor.surname}</td>
                    <td>${prescription.dateBegin}</td>
                    <td>${prescription.dateEnd}</td>
                    <td>${fn:toLowerCase(prescription.status)}
                    </td>
                    <c:choose>
                        <c:when test="${prescription.status=='INACTIVE'}">

                            <c:url var="extend_prescription" value="/client">
                                <c:param name="command" value="extend_prescription"/>
                                <c:param name="prescription_id" value="${prescription.prescriptionId}"/>
                            </c:url>
                            <td>
                                <a href="${extend_prescription}"><fmt:message bundle="${bundle}" key="button.extend"/>
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td></td>
                        </c:otherwise>
                    </c:choose>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
