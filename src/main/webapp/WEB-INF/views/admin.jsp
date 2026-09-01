<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="title" value="Administración"/>
<c:set var="extraCss" value="${['paginas/detalleCurso.css','paginas/admin.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>
<main>
    <div class="contenedor detalle-curso">
        <h1 class="detalle-curso__titulo">Administración</h1>

        <section class="detalle-curso__panel detalle-curso__panel--ancho">
            <h2 class="curso-detalle__subtitulo">Estadísticas globales</h2>

            <div class="detalle-curso__stats">
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">${totalUsuarios}</span>
                    <span class="detalle-curso__stat-etiqueta">Usuarios registrados</span>
                </div>
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">${totalCursos}</span>
                    <span class="detalle-curso__stat-etiqueta">Cursos</span>
                </div>
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">${totalInscripciones}</span>
                    <span class="detalle-curso__stat-etiqueta">Inscripciones</span>
                </div>
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">
                        <c:choose>
                            <c:when test="${not empty valoracionMedia}">
                                <fmt:formatNumber value="${valoracionMedia}" maxFractionDigits="1"/>
                            </c:when>
                            <c:otherwise>—</c:otherwise>
                        </c:choose>
                    </span>
                    <span class="detalle-curso__stat-etiqueta">
                        Valoración media (${totalValoraciones} valoraciones)
                    </span>
                </div>
            </div>

            <h2 class="curso-detalle__subtitulo detalle-curso__subtitulo-materiales">Usuarios por rol</h2>
            <div class="detalle-curso__stats">
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">${totalEstudiantes}</span>
                    <span class="detalle-curso__stat-etiqueta">Estudiantes</span>
                </div>
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">${totalProfesores}</span>
                    <span class="detalle-curso__stat-etiqueta">Profesores</span>
                </div>
                <div class="detalle-curso__stat">
                    <span class="detalle-curso__stat-numero">${totalAdministradores}</span>
                    <span class="detalle-curso__stat-etiqueta">Administradores</span>
                </div>
            </div>
        </section>

        <section class="detalle-curso__panel detalle-curso__panel--ancho admin-opciones">
            <h2 class="curso-detalle__subtitulo">Opciones generales</h2>
            <div class="lista-usuarios__acciones">
                <a href="app/listarUsuarios" class="boton boton--secundario">Gestionar usuarios</a>
                <a href="app/listarTematicas" class="boton boton--secundario">Gestionar temáticas</a>
                <a href="app/editarConfiguracion" class="boton boton--secundario">Configurar portada</a>
            </div>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>