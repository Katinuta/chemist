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
    <h2>${fn:escapeXml(message)}</h2>
    <span class="error" id="ajax_error">${error}</span>
    <c:if test="${ not empty medicines}">
    <div class="container-fluid form">

        <table class="table table-striped">

            <thead>
            <tr>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.name"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.price"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.balance"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.producer"/></th>
                <th><fmt:message bundle="${bundle}" key="table.medicine.column.prescription"/></th>
                <th></th>
            </tr>

            </thead>
            </c:if>
            <tbody class="tbody">
            <c:forEach var="medicine" items="${medicines}">
                <tr>
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
                    <td>
                        <c:choose>
                            <c:when test="${medicine.isDeleted or medicine.quantityPackages==0}">
                                        <span>
                                            <fmt:message bundle="${bundle}" key="label.archive"/>
                                        </span>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-default medicineForAdd" name="medicine_id"
                                        value="${medicine.medicineId}">
                                    <fmt:message bundle="${bundle}" key="button.addtocart"/>
                                </button>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${ not flagFind}">
            <div class="container">
                <ul class="pagination">
                    <c:forEach begin="1" end="${countpages}" step="1" var="i">
                        <c:url value="/client" var="next_page">
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


