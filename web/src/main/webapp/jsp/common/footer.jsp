<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="customtags" %>
<span><fmt:message bundle="${bundle}" key="footer.brand"/></span>
<%--<ctg:footer-tag/>--%>
<span >8.00-22.00</span>

<span ><fmt:message bundle="${bundle}" key="footer.adress"/> <span ><fmt:message bundle="${bundle}" key="footer.phone"/></span></span>
<span><fmt:message bundle="${bundle}" key="footer.inform"/> <a class="email" href="mailto:chemist@chemist.com">chemist@chemist.com</a></span>
