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
                    <c:url var="allclients" value="/doctor">
                        <c:param name="command" value="allclients"/>
                        <c:param name="currentPage" value="1"/>
                    </c:url>

                    <a href="${allclients}"><fmt:message bundle="${bundle}" key="ref.clients"/></a>
                    <c:url var="prescriptionstoextend" value="/doctor">
                        <c:param name="command" value="prescriptoextend"/>
                    </c:url>
                    <a href="${prescriptionstoextend}"><fmt:message bundle="${bundle}" key="ref.request.prescriptions"/></a>
                    <c:url var="editpassword" value="/doctor">
                        <c:param name="command" value="editpassword"/>
                    </c:url>

                    <a href="${editpassword}"><fmt:message bundle="${bundle}" key="ref.change.password"/></a>

                    <c:url var="logout" value="/controller">
                        <c:param name="command" value="logout"/>
                    </c:url>
                    <a href="${logout}"><fmt:message bundle="${bundle}" key="ref.logout"/></a>
                </div>
            </div>
        </li>

        <c:url var="englishLang" value="/controller">
            <c:param name="newlocale" value="en-EN"/>
            <c:param name="command" value="locale"/>
            <c:param name="page" value="${pageContext.request.requestURI}"/>
        </c:url>

        <li>
            <a href="${englishLang}">
                <img src="/image/united-kingdom-flag.png">
            </a>
        </li>


        <c:url var="rusLang" value="/controller">
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



</nav>
</body>
</html>
