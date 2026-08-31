<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Catálogo de cursos · InfoFormación</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="imagenes/logo.png" type="favicon/x-icon">
    <link rel="stylesheet" type="text/css" href="assets/css/base.css">
    <link rel="stylesheet" type="text/css" href="assets/css/layout.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/botones.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/formularios.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/menu.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/cursosGrid.css">
    <link rel="stylesheet" type="text/css" href="assets/css/paginas/catalogo.css">
</head>

<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/layout/menu.jsp"/>
</c:if>

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

<%@ include file="layout/footer.jsp" %>
</body>
</html>