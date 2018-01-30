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
        <input name="command" type="hidden" value="findmedicine"/>
        <div class="input-group">
            <input type="text" class="form-control"
                   placeholder="<fmt:message bundle="${bundle}" key="placeholder.search"/>" name="search"/>
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
                    <c:url var="allclientprescription" value="/client">
                        <c:param name="command" value="allclientprescription"/>
                    </c:url>
                    <a href="${allclientprescription}"><fmt:message bundle="${bundle}" key="ref.prescription"/></a>
                    <c:url var="allclientpurchases" value="/client">
                        <c:param name="command" value="clientpurchases"/>
                    </c:url>
                    <a href="${allclientpurchases}"><fmt:message bundle="${bundle}" key="ref.purchase"/></a>
                    <c:url var="opencart" value="/client">
                        <c:param name="command" value="opencart"/>
                    </c:url>
                    <a href="${opencart}" id="cart"><fmt:message bundle="${bundle}" key="ref.cart"/></a>
                    <c:url var="editpassword" value="/client">
                        <c:param name="command" value="editpassword"/>
                    </c:url>

                    <a href="${editpassword}"><fmt:message bundle="${bundle}" key="ref.change.password"/></a>
                    <c:url var="logout" value="/client">
                        <c:param name="command" value="logout"/>
                    </c:url>

                    <a href="${logout}"><fmt:message bundle="${bundle}" key="ref.logout"/></a>
                </div>
            </div>
        </li>
        <li>

            <a href="${opencart}"><span class="glyphicon glyphicon-shopping-cart"></span><fmt:message bundle="${bundle}"
                                                                                                      key="ref.cart"/></a>

        </li>
        <li>
            <a id="cartvalue"><span>${fn:length(cart)}</span></a>
        </li>

        <c:url var="englishLang" value="/user">
            <c:param name="newlocale" value="en-EN"/>
            <c:param name="command" value="locale"/>

        </c:url>

        <li>
            <a href="${englishLang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>


        <c:url var="rusLang" value="/user">
            <c:param name="command" value="locale"/>
            <c:param name="newlocale" value="ru-RU"/>

        </c:url>
        <li>

            <a href="${rusLang}"> <img src="/image/russia-flag.png"></a>
        </li>
        <li>
            <ctg:hello-tag name="${user.name}"></ctg:hello-tag>
        </li>

    </ul>
</div>
