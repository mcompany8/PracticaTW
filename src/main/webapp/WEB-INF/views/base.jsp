<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value=""/>
<c:set var="extraCss" value=
        "${[
        'componentes/cursosGrid.css',
        'paginas/catalogo.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="layout/menu.jsp"/>
</c:if>

<body>

<main>

<h1>Hola</h1>

</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>