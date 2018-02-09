<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<c:choose>
    <c:when test="${not empty locale}">
        <fmt:setLocale value="${locale}"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>
<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="/jsp/client/main.jsp">
            <fmt:message bundle="${bundle}" key="ref.brand"/>
        </a>
    </div>

    <form class="navbar-form navbar-left" action="/client">
        <input name="command" type="hidden" value="find_medicine"/>
        <div class="input-group">
            <input type="text" class="form-control"
                   placeholder="<fmt:message bundle="${bundle}" key="placeholder.search"/>" name="medicine_name"
                   value="${fn:escapeXml(medicine_name)}"
            />
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit">
                    <i class="glyphicon glyphicon-search"></i>
                </button>
            </div>
        </div>
    </form>

    <ul class="nav navbar-nav navbar-right">

        <li>
            <div class="dropdown">
                <a class="dropbtn disabled">
                    <span class="glyphicon glyphicon-user"></span>
                    <fmt:message bundle="${bundle}" key="ref.account"/></a>
                <div class="dropdown-content">
                    <c:url var="show_client_prescriptions" value="/client">
                        <c:param name="command" value="show_client_prescriptions"/>
                    </c:url>
                    <a href="${show_client_prescriptions}"><fmt:message bundle="${bundle}" key="ref.prescription"/></a>
                    <c:url var="show_all_orders" value="/client">
                        <c:param name="command" value="show_all_orders"/>
                    </c:url>
                    <a href="${show_all_orders}"><fmt:message bundle="${bundle}" key="ref.orders"/></a>
                    <c:url var="to_cart" value="/client">
                        <c:param name="command" value="to_cart"/>
                    </c:url>
                    <a href="${to_cart}" id="cart"><fmt:message bundle="${bundle}" key="ref.cart"/></a>
                    <c:url var="to_edit_password" value="/client">
                        <c:param name="command" value="to_edit_password"/>
                    </c:url>

                    <a href="${to_edit_password}"><fmt:message bundle="${bundle}" key="ref.change.password"/></a>
                    <c:url var="sign_out" value="/client">
                        <c:param name="command" value="sign_out"/>
                    </c:url>

                    <a href="${sign_out}"><fmt:message bundle="${bundle}" key="ref.signout"/></a>
                </div>
            </div>
        </li>
        <li>
            <a href="${to_cart}"><span class="glyphicon glyphicon-shopping-cart"></span><fmt:message bundle="${bundle}"
                                                                                                     key="ref.cart"/></a>
        </li>
        <li>
            <a id="cartvalue"><span>${fn:length(cart)}</span></a>
        </li>
        <li>
            <c:url var="eng_lang" value="/client">
                <c:param name="new_locale" value="en-EN"/>
                <c:param name="command" value="change_locale"/>
            </c:url>
            <a href="${eng_lang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>
        <li>
            <c:url var="rus_lang" value="/client">
                <c:param name="command" value="change_locale"/>
                <c:param name="new_locale" value="ru-RU"/>
            </c:url>
            <a href="${rus_lang}"> <img src="/image/russia-flag.png"></a>
        </li>
        <li>
            <span class="hello"><ctg:hello-tag name="${user.name}"/></span>
        </li>
    </ul>
</div>
