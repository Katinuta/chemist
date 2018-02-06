<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h2><fmt:message bundle="${bundle}" key="ref.prescription"/></h2>
    <c:choose>
        <c:when test="${not empty prescriptions}">
            <div class="container-fluid form">
                <table class="table table-striped table-presrip">
                    <thead>
                    <tr>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.number"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.client"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.datebegin"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.dateend"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.prescription.column.status"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="prescription" items="${prescriptions}">

                            <tr>
                                <c:url var="show_prescription_info" value="/doctor">
                                    <c:param name="command" value="show_prescription_info"/>
                                    <c:param name="prescription_id" value="${prescription.prescriptionId}"/>
                                </c:url>
                                <td class="center"><a href="${show_prescription_info}">${prescription.prescriptionId}</a>
                                    <c:set var="message_presription_id"
                                           value="${requestScope['error_prescription_id'.concat(prescription.prescriptionId)]}"/>
                                    <span class="error">${message_presription_id}</span>
                                </td>
                                <td>${prescription.client.name} ${prescription.client.surname}</td>
                                <td><input type="date" name="date_begin" class="begin" data-date=""
                                           data-date-format="DD MMMM YYYY" value="${prescription.dateBegin}" required readonly/>
                                    <c:set var="message_begin"
                                           value="${requestScope['error_date_begin'.concat(prescription.prescriptionId)]}"/>
                                    <span class="error">${message_begin}</span>
                                </td>
                                <form action="/doctor" method="post">
                                    <input type="hidden" name="command" value="approve_extending_prescription">
                                <td>
                                    <c:choose>
                                        <c:when test="${prescription.status=='EXTEND'}">
                                            <input type="date" name="date_end" class="end form-control" value="${prescription.dateEnd}" required />
                                            <c:set var="message_end"
                                                   value="${requestScope['error_date_end'.concat(prescription.prescriptionId)]}"/>
                                            <span class="error end">${message_end}</span>
                                    </c:when>
                                    <c:otherwise>
                                             <input type="date" name="date_end" class="begin" value="${prescription.dateEnd}" required readonly/>
                                    </c:otherwise>
                                    </c:choose>

                                <td>
                                    <c:choose>
                                        <c:when test="${prescription.status=='EXTEND'}">
                                            <fmt:message bundle="${bundle}" key="label.prescription.extend"/>
                                            <button name="prescription_id" value="${prescription.prescriptionId}">
                                                <fmt:message bundle="${bundle}" key="button.extend"/>
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            ${fn:toLowerCase(prescription.status)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                </form>
                            </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>

        </c:when>
        <c:otherwise>
            <div class="container message">
                <div class="flex-container">
                    <h3>${message}</h3>
                </div>
            </div>

        </c:otherwise>
    </c:choose>


</div>
>