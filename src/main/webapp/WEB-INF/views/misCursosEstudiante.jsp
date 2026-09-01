<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value=""/>
<c:set var="extraCss" value=
        "${[
        'componentes/cursosGrid.css',
        'paginas/misCursos.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="layout/menu.jsp"/>
</c:if>

<body>

<main>
    <div class="contenedor mis-cursos">

        <div class="mis-cursos__cabecera">
            <h1 class="mis-cursos__titulo">Mis cursos</h1>
        </div>

        <c:choose>
            <c:when test="${empty inscripciones}">
                <p class="mis-cursos__vacio">
                    Todavía no estás inscrito en ningún curso.
                    <a href="app/catalogo">Explora el catálogo</a>.
                </p>
            </c:when>
            <c:otherwise>
                <div class="cursos-grid">
                    <c:forEach var="inscripcion" items="${inscripciones}">
                        <a href="app/curso?id=${inscripcion.curso.id}"
                           class="curso-tarjeta curso-tarjeta--enlace">

                            <div class="curso-tarjeta__imagen-contenedor">
                                <img src="imagenes/cursos/${inscripcion.curso.imagen}"
                                     alt="${inscripcion.curso.titulo}"
                                     class="curso-tarjeta__imagen">
                            </div>

                            <div class="curso-tarjeta__cuerpo">
                                <h3 class="curso-tarjeta__titulo">${inscripcion.curso.titulo}</h3>
                                <p class="curso-tarjeta__profesor">
                                        ${inscripcion.curso.responsable.nombre} ${inscripcion.curso.responsable.apellidos}
                                </p>
                                <p class="curso-tarjeta__fecha-inscripcion">
                                    Inscrito el ${inscripcion.fechaInscripcionFormateada}
                                </p>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>