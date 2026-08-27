<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>

<%@ include file="common/head.jsp"%>
<%@ include file="common/header.jsp"%>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>
<main>

<h1>Subir contenido</h1>

<form action="app/subirContenido"
      method="post" enctype="multipart/form-data">
    <input type="file" name="archivo" accept="application/pdf" required>
    <input type="submit" value="Subir material">
</form>

</main>


<%@ include file="common/footer.jsp"%>