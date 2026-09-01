<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp"%>

<h1>Error 404</h1>
<p>URI: ${requestScope['jakarta.servlet.error.request_uri']}</p>
<p>Código de estado: ${requestScope['jakarta.servlet.error.status_code']}</p>
<p>Tipo: ${requestScope['jakarta.servlet.error.exception_type']}</p>

<%@ include file="../layout/footer.jspf"%>
