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
        <a class="navbar-brand" href="/jsp/doctor/main.jsp">
            <fmt:message bundle="${bundle}" key="ref.brand"/>
        </a>
    </div>

    <ul class="nav navbar-nav navbar-right">

        <li>
            <div class="dropdown">
                <a class="dropbtn disabled">
                    <span class="glyphicon glyphicon-user"></span>
                    <fmt:message bundle="${bundle}" key="ref.account"/>
                </a>
                <div class="dropdown-content">
                    <c:url var="show_all_clients" value="/doctor">
                        <c:param name="command" value="show_all_clients"/>
                        <c:param name="current_page" value="1"/>
                    </c:url>

                    <a href="${show_all_clients}"><fmt:message bundle="${bundle}" key="ref.clients"/></a>
                    <c:url var="show_prescriptions_to_extend" value="/doctor">
                        <c:param name="command" value="show_prescriptions_to_extend"/>
                    </c:url>
                    <a href="${show_prescriptions_to_extend}"><fmt:message bundle="${bundle}" key="ref.request.prescriptions"/></a>
                    <c:url var="to_edit_password" value="/doctor">
                        <c:param name="command" value="to_edit_password"/>
                    </c:url>

                    <a href="${to_edit_password}"><fmt:message bundle="${bundle}" key="ref.change.password"/></a>

                    <c:url var="sign_out" value="/doctor">
                        <c:param name="command" value="sign_out"/>
                    </c:url>
                    <a href="${sign_out}"><fmt:message bundle="${bundle}" key="ref.signout"/></a>
                </div>
            </div>
        </li>

        <c:url var="eng_lang" value="/doctor">
            <c:param name="new_locale" value="en-EN"/>
            <c:param name="command" value="change_locale"/>
        </c:url>

        <li>
            <a href="${eng_lang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>


        <c:url var="rus_lang" value="/doctor">
            <c:param name="command" value="change_locale"/>
            <c:param name="new_locale" value="ru-RU"/>
        </c:url>
        <li>

            <a href="${rus_lang}"> <img src="/image/russia-flag.png"></a>
        </li>
        <li>
            <ctg:hello-tag name="${user.name}"></ctg:hello-tag>
        </li>

    </ul>
</div>



</nav>
</body>
</html>
