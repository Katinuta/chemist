<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">

    <c:choose>
        <c:when test="${flagFind}">
            <h2><fmt:message bundle="${bundle}" key="table.name.fmedicunes"/>
            </h2></c:when>
        <c:otherwise>
            <h2>
                <fmt:message bundle="${bundle}" key="table.name.medicines"/>
            </h2></c:otherwise>
    </c:choose>
    <h2>${message}</h2>
    <c:if test="${ not empty medicines}">
    <div class="container-fluid form">
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message bundle="${bundle}" key="table.column.number"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.price"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.balance"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.producer"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.prescription"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.edit"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.delete"/></th>
            </tr>
            </thead>
            </c:if>
            <tbody class="tbody">
            <c:forEach var="medicine" items="${medicines}">
                <c:choose>
                    <c:when test="${not medicine.isDeleted}">
                        <tr>
                    </c:when>
                    <c:otherwise>
                        <tr class="table-dark">
                    </c:otherwise>
                </c:choose>
                <td class="right_table">${medicine.medicineId}</td>
                <td>${medicine.name}</td>
                <td>${medicine.price}</td>
                <td>${medicine.quantityPackages}</td>
                <td>${medicine.producer.name}</td>
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
                <c:choose>
                    <c:when test="${not medicine.isDeleted}">
                        <td>
                            <form action="/pharmacist" class="close">
                                <input type="hidden" value="to_edit_medicine" name="command"/>
                                <button class="btn edit" name="medicine_id" value="${medicine.medicineId}">
                                    <i class="fa fa-edit"></i>
                                </button>
                            </form>
                        </td>
                        <td >
                            <form action="/pharmacist" class="close">
                                <input type="hidden" value="delete_medicine" name="command"/>
                                <button class="btn close" name="medicine_id" value="${medicine.medicineId}">
                                    <i class="fa fa-close"></i>
                                </button>
                            </form>
                        </td>
                        <td></td>
                    </c:when>
                    <c:otherwise>
                        <td></td>
                        <td></td>
                        <td></td>
                    </c:otherwise>
                </c:choose>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${ not flagFind}">
            <div class="container">
                <ul class="pagination">
                    <c:forEach begin="1" end="${countpages}" step="1" var="i">
                        <c:url value="/pharmacist" var="next_page">
                            <c:param name="command" value="next_page"/>
                            <c:param name="current_page" value="${i}"/>
                        </c:url>
                        <c:choose>
                            <c:when test="${page==i}">
                                <li class="page-item active">
                                    <a class="page-link" href="${next_page}">${i}</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="${next_page}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </div>


</div>

