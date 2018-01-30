<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <h2><fmt:message bundle="${bundle}" key="ref.clients"/></h2>

    ${error}
            <div class="container-fluid form">
                <table class="table table-striped">
                    <thead>
                    <tr >
                        <th><fmt:message bundle="${bundle}" key="table.client.surname"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.client.name"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.client.phone"/></th>

                        <th></th>
                    </tr>

                    </thead>
                    <tbody class="tbody">
                        <c:forEach var="client" items="${clients}">
                        <tr>
                            <td>${client.surname}</td>
                            <td>${client.name}</td>
                            <td>${client.phone}</td>

                            <td>
                                <form action="/controller">
                                    <input type="hidden" name="command" value="newprescription"/>
                                    <button class="btn btn-default medicineForAdd" name="clientId" value="${client.userId}">
                                        <fmt:message bundle="${bundle}" key="button.add.prescription"/>
                                    </button>
                                </form>

                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <%--<c:if test="${ not flagFind}">--%>
                    <div class="container">
                        <ul class="pagination">
                            <c:forEach begin="1" end="${countpages}" step="1" var="i">
                                <c:url var="allclients" value="/controller">
                                    <c:param name="command" value="allclients"/>
                                    <c:param name="currentPage" value="${i}"/>
                                </c:url>
                                <li class="page-item"><a class="page-link" href="${allclients}">${i}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                <%--</c:if>--%>
    </div>
</div>


