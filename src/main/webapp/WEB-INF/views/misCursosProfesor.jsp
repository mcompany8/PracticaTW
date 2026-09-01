<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Mis cursos"/>
<c:set var="extraCss" value=
        "${[
        'componentes/cursosGrid.css',
        'paginas/misCursos.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>

<main>
    <div class="contenedor mis-cursos">

        <div class="mis-cursos__cabecera mis-cursos__cabecera--con-accion">
            <h1 class="mis-cursos__titulo">Mis cursos</h1>
            <a href="app/crearCurso" class="boton boton--primario">+ Crear curso</a>
        </div>

        <c:choose>
            <c:when test="${empty cursos}">
                <p class="mis-cursos__vacio">
                    Todavía no has creado ningún curso.
                    <a href="app/crearCurso">Crea el primero</a>.
                </p>
            </c:when>
            <c:otherwise>
                <div class="cursos-grid">
                    <c:forEach var="curso" items="${cursos}">
                        <a href="app/detalleCurso?id=${curso.id}"
                           class="curso-tarjeta curso-tarjeta--enlace">

                            <div class="curso-tarjeta__imagen-contenedor">
                                <img src="imagenes/cursos/${curso.imagen}"
                                     alt="${curso.titulo}"
                                     class="curso-tarjeta__imagen">
                                <span class="curso-tarjeta__nivel
                                              curso-tarjeta__nivel--${curso.nivel}">
                                        ${curso.nivel}
                                </span>
                            </div>

                            <div class="curso-tarjeta__cuerpo">
                                <h3 class="curso-tarjeta__titulo">${curso.titulo}</h3>
                                <p class="curso-tarjeta__descripcion">${curso.descripcion}</p>
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