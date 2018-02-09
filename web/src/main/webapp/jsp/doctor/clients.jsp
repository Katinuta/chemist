<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">

    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <h2><fmt:message bundle="${bundle}" key="ref.clients"/></h2>
            <div class="container-fluid form clients">
                <table class="table table-striped">
                    <thead>
                    <tr >
                        <th><fmt:message bundle="${bundle}" key="table.client.surname"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.client.name"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.client.phone"/></th>
                        <th><fmt:message bundle="${bundle}" key="table.client.action"/></th>
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
                                    <input type="hidden" name="command" value="to_new_prescription"/>
                                    <button class="btn btn-default medicineForAdd" name="client_id" value="${client.userId}">
                                        <fmt:message bundle="${bundle}" key="button.add.prescription"/>
                                    </button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="container">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${countpages}" step="1" var="i">
                            <c:url var="show_all_clients" value="/doctor">
                                <c:param name="command" value="show_all_clients"/>
                                <c:param name="current_page" value="${i}"/>
                            </c:url>
                            <li class="page-item"><a class="page-link" href="${show_all_clients}">${i}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>


</div>


