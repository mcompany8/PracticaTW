<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="${curso.titulo}}"/>
<c:set var="extraCss" value=
        "${[
        'paginas/curso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>
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
                        <a href="${urlActual}#confirmar-inscripcion" class="boton boton--primario">
                            Inscribirme en este curso
                        </a>

                        <ui:confirmacion id="confirmar-inscripcion"
                                         urlActual="${urlActual}"
                                         mensaje="¿Confirmas tu inscripción en «${curso.titulo}»?"
                                         accionFormulario="app/inscripcion"
                                         textoConfirmar="Confirmar inscripción">
                            <jsp:attribute name="camposOcultos">
                                <input type="hidden" name="cursoId" value="${curso.id}">
                            </jsp:attribute>
                        </ui:confirmacion>
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

<%@ include file="layout/footer.jspf" %>
</body>
</html>