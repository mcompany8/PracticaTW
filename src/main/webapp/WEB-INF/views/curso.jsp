<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8" >
    <title>${curso.titulo}</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="imagenes/logo.png" type="favicon/x-icon">
    <link rel="stylesheet" type="text/css" href="assets/css/base.css">
    <link rel="stylesheet" type="text/css" href="assets/css/layout.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/botones.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/formularios.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/menu.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/cursosGrid.css">
    <link rel="stylesheet" type="text/css" href="assets/css/paginas/curso.css">
<%--    <link rel="stylesheet" type="text/css" href="assets/css/paginas/index.css">--%>
</head>

<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/layout/menu.jsp"/>
</c:if>

<body>

<main>

    <jsp:include page="components/bannerCurso.jsp"/>

    <section class="curso-detalle__descripcion">
        <h2 class="curso-detalle__subtitulo">Descripción del curso</h2>
        <p class="curso-detalle__texto">${curso.descripcionLarga}</p>
    </section>

    <section class="curso-detalle__accion">

        <c:choose>

            <%-- Caso 1: hay sesión y el usuario es Estudiante --%>
            <c:when test="${not empty sessionScope.usuario and sessionScope.usuario.tipoUsuario eq 'Estudiante'}">
                <c:choose>
                    <c:when test="${not empty requestScope.inscripcion}">
                        <p class="curso-detalle__aviso">Ya estás inscrito en este curso.</p>
                    </c:when>
                    <c:otherwise>
                        <form class="curso-detalle__form"
                              action="app/curso"
                              method="post">
                            <input type="hidden" name="accion" value="inscribirse">
                            <input type="hidden" name="cursoId" value="${curso.id}">
                            <button type="submit" class="boton boton--primario">
                                Inscribirme en este curso
                            </button>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <%-- Caso 2: no hay nadie logueado --%>
            <c:when test="${empty sessionScope.usuario}">
                <p class="curso-detalle__aviso">
                    Debes registrarte para poder inscribirte en este curso.
                </p>
                <a class="boton boton--primario"
                   href="${pageContext.request.contextPath}/app/registro">
                    Registrarme
                </a>
            </c:when>

            <%-- Caso 3: logueado pero no es Estudiante (Profesor/Administrador) --%>
            <c:otherwise>
                <p class="curso-detalle__aviso">
                    Solo los estudiantes pueden inscribirse en los cursos.
                </p>
            </c:otherwise>

        </c:choose>
    </section>

</main>


<%@ include file="layout/footer.jsp" %>
</body>
</html>

