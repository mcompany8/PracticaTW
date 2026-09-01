<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Bienvenido a InfoFormación"/>
<c:set var="extraCss" value=
        "${[
        'componentes/cursosGrid.css',
        'paginas/index.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="layout/menu.jsp"/>
</c:if>

<body>
<main>

    <%@include file="components/hero.jsp" %>

    <section class="cursos-destacados">
        <header class="cursos-destacados__header">
            <h2 class="cursos-destacados__title">Cursos destacados</h2>
            <a href="app/catalogo" class="cursos-destacados__todos">Ver todos los cursos →</a>
        </header>
        <jsp:include page="components/cursosGrid.jsp"/>
    </section>

</main>

<%@ include file="layout/footer.jspf" %>

</body>
</html>


