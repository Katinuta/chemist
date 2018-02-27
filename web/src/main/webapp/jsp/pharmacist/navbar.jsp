<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="MessagesBundle" var="bundle" scope="session"/>
<div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="/chemist/jsp/pharmacist/main.jsp">
            <fmt:message bundle="${bundle}" key="ref.brand"/>
        </a>
    </div>

    <form class="navbar-form navbar-left" action="/chemist/pharmacist">
        <input name="command" type="hidden" value="find_medicine"/>
        <div class="input-group">
            <input type="text" class="form-control" name="medicine_name"
                   placeholder="<fmt:message bundle="${bundle}" key="placeholder.search"/>" value="${fn:escapeXml(medicine_name)}"/>
            <div class="input-group-btn">
                <button class="btn btn-default search" type="submit">
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
                    <fmt:message bundle="${bundle}" key="ref.account"/>
                </a>
                <div class="dropdown-content">
                    <c:url var="to_new_medicine" value="/pharmacist">
                        <c:param name="command" value="to_new_medicine"/>
                    </c:url>

                    <a href="${to_new_medicine}"><fmt:message bundle="${bundle}" key="ref.newmedicine"/></a>
                    <c:url var="to_edit_password" value="/pharmacist">
                        <c:param name="command" value="to_edit_password"/>
                    </c:url>

                    <a href="${to_edit_password}"><fmt:message bundle="${bundle}" key="ref.change.password"/></a>

                    <c:url var="sign_out" value="/pharmacist">
                        <c:param name="command" value="sign_out"/>
                    </c:url>
                    <a href="${sign_out}"><fmt:message bundle="${bundle}" key="ref.signout"/></a>
                </div>
            </div>
        </li>

        <c:url var="english_lang" value="/pharmacist">
            <c:param name="new_locale" value="en-EN"/>
            <c:param name="command" value="change_locale"/>
        </c:url>

        <li>
            <a href="${english_lang}">
                <img src="/chemist/image/united-kingdom-flag.png">
            </a>
        </li>

        <li>
            <c:url var="rus_lang" value="/pharmacist">
                <c:param name="command" value="change_locale"/>
                <c:param name="new_locale" value="ru-RU"/>
            </c:url>
            <a href="${rus_lang}"> <img src="/chemist/image/russia-flag.png"></a>
        </li>
        <li>
           <span class="hello"><ctg:hello-tag name="${user.name}"/></span>
        </li>
    </ul>
</div>
