<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Catálogo"/>
<c:set var="extraCss" value=
        "${[
        'componentes/cursosGrid.css',
        'paginas/catalogo.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>

<main>
    <div class="contenedor catalogo">

        <div class="catalogo__cabecera">
            <h1 class="catalogo__titulo">Catálogo de cursos</h1>
            <p class="catalogo__subtitulo">Explora todos los cursos disponibles en InfoFormación</p>
        </div>

        <nav class="catalogo__filtros">
            <a href="app/catalogo"
               class="catalogo__filtro <c:if test='${empty tematicaSeleccionada}'>catalogo__filtro--activo</c:if>">
                Todos
            </a>
            <c:forEach items="${tematicas}" var="tematica">
                <a href="app/catalogo?tematica=${tematica.id}"
                   class="catalogo__filtro <c:if test='${tematicaSeleccionada == tematica.id}'>catalogo__filtro--activo</c:if>">
                    <img src="imagenes/tematicas/${tematica.imagen}"
                         alt="" class="catalogo__filtro-icono">
                        ${tematica.titulo}
                </a>
            </c:forEach>
        </nav>

        <c:choose>
            <c:when test="${empty cursos}">
                <p class="catalogo__vacio">No hay cursos disponibles para esta temática.</p>
            </c:when>
            <c:otherwise>
                <jsp:include page="components/cursosGrid.jsp"/>
            </c:otherwise>
        </c:choose>

    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>