<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>--%>

<%@ include file="layout/header.jsp" %>

<main class="contenido">
    <section class="seccion-mis-cursos">
        <div class="contenedor">
            <h1 class="titulo-pagina">Mis cursos</h1>

            <c:choose>
                <c:when test="${empty inscripciones}">
                    <p class="mensaje-vacio">
                        Todavía no estás inscrito en ningún curso.
                        <a href="${pageContext.request.contextPath}/" class="enlace">Explora el catálogo</a>.
                    </p>
                </c:when>
                <c:otherwise>
                    <div class="grid-cursos">
                        <c:forEach var="inscripcion" items="${inscripciones}">
                            <%-- Ajusta la ruta si tu detalle de curso usa otro patrón --%>
                            <a class="tarjeta-curso tarjeta-curso--enlace"
                               href="${pageContext.request.contextPath}/app/cursos/${inscripcion.curso.id}">

                                <div class="tarjeta-curso__miniatura-contenedor">
                                        <%-- Ajusta esta ruta a tu servlet/comando de servido de archivos --%>
                                    <img class="tarjeta-curso__miniatura"
                                         src="${pageContext.request.contextPath}/archivos/${inscripcion.curso.miniatura}"
                                         alt="Miniatura del curso ${inscripcion.curso.titulo}">
                                </div>

                                <div class="tarjeta-curso__cuerpo">
                                    <h2 class="tarjeta-curso__titulo">${inscripcion.curso.titulo}</h2>
                                    <p class="tarjeta-curso__profesor">
                                            ${inscripcion.curso.profesor.nombre} ${inscripcion.curso.profesor.apellidos}
                                    </p>
                                    <p class="tarjeta-curso__fecha-inscripcion">
                                        Inscrito el
                                        <fmt:formatDate value="${inscripcion.fechaInscripcion}" pattern="dd/MM/yyyy"/>
                                    </p>
                                </div>
                            </a>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</main>

<%@ include file="/WEB-INF/jsp/fragments/footer.jspf" %>