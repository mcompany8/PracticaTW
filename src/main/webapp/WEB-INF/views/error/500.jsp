<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head.jsp"%>
<%@ include file="../common/header.jsp"%>

<h1>Ha ocurrido un error de tipo 500</h1>
<p>URI: ${requestScope['jakarta.servlet.error.request_uri']}</p>
<p>Código de estado: ${requestScope['jakarta.servlet.error.status_code']}</p>
<p>Tipo: ${requestScope['jakarta.servlet.error.exception_type']}</p>

<%@ include file="../common/footer.jsp"%>
