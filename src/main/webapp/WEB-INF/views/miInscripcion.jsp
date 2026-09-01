<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="title" value="${curso.titulo}"/>
<c:set var="extraCss" value=
        "${[
        'paginas/curso.css',
        'componentes/formularios.css',
        'paginas/miInscripcion.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>

<main>

    <%-- Mismo banner que en la vista de Catálogo --%>
    <jsp:include page="components/bannerCurso.jsp"/>

    <section class="inscripcion-detalle__meta">
        <div class="contenedor inscripcion-detalle__meta-fila">
            <div class="inscripcion-detalle__meta-datos">
                <span class="inscripcion-detalle__badge-nivel
                              inscripcion-detalle__badge-nivel--${fn:toLowerCase(curso.nivel)}">
                    ${curso.nivel}
                </span>
                <c:if test="${not empty curso.duracionHoras}">
                    <span class="inscripcion-detalle__meta-dato">${curso.duracionHoras} h</span>
                </c:if>
                <span class="inscripcion-detalle__meta-dato">
                    Inscrito el ${inscripcion.fechaInscripcionFormateada}
                </span>
            </div>
            <a href="app/misCursos" class="inscripcion-detalle__volver">&larr; Volver a mis cursos</a>
        </div>
    </section>

    <section class="inscripcion-detalle__contenidos">
        <div class="contenedor">
            <h2 class="curso-detalle__subtitulo">Contenidos del curso</h2>

            <c:choose>
                <c:when test="${empty contenidos}">
                    <p class="inscripcion-detalle__vacio">
                        El profesor todavía no ha publicado contenidos para este curso.
                    </p>
                </c:when>
                <c:otherwise>
                    <ul class="contenidos-lista">
                        <c:forEach var="contenido" items="${contenidos}">
                            <c:set var="uriMin" value="${fn:toLowerCase(contenido.uri)}"/>
                            <c:choose>
                                <c:when test="${fn:startsWith(uriMin,'http://') or fn:startsWith(uriMin,'https://')}">
                                    <c:set var="tipoContenido" value="enlace"/>
                                    <c:set var="hrefContenido" value="${contenido.uri}"/>
                                </c:when>
                                <c:when test="${fn:endsWith(uriMin,'.pdf')}">
                                    <c:set var="tipoContenido" value="pdf"/>
                                    <c:set var="hrefContenido"
                                           value="${pageContext.request.contextPath}/contenidos/${contenido.uri}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="tipoContenido" value="documento"/>
                                    <c:set var="hrefContenido"
                                           value="${pageContext.request.contextPath}/contenidos/${contenido.uri}"/>
                                </c:otherwise>
                            </c:choose>

                            <li class="contenido-item">
                                <svg class="contenido-item__icono" aria-hidden="true">
                                    <c:choose>
                                        <c:when test="${tipoContenido == 'pdf'}">
                                            <use href="assets/icons/icons.svg#file-pdf"></use>
                                        </c:when>
                                        <c:otherwise>
                                            <use href="assets/icons/icons.svg#folder-plus"></use>
                                        </c:otherwise>
                                    </c:choose>
                                </svg>

                                <div class="contenido-item__cuerpo">
                                    <p class="contenido-item__titulo">${contenido.titulo}</p>
                                    <span class="contenido-item__badge
                                                  contenido-item__badge--${tipoContenido}">
                                        <c:choose>
                                            <c:when test="${tipoContenido == 'pdf'}">PDF</c:when>
                                            <c:when test="${tipoContenido == 'enlace'}">Enlace</c:when>
                                            <c:otherwise>Documento</c:otherwise>
                                        </c:choose>
                                    </span>
                                </div>

                                <a href="${hrefContenido}"
                                   class="boton boton--secundario contenido-item__accion"
                                   target="_blank" rel="noopener noreferrer">
                                    Ver
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </section>

    <section class="inscripcion-detalle__valoracion">
        <div class="contenedor">
            <h2 class="curso-detalle__subtitulo">Tu valoración</h2>

            <c:choose>

                <%-- Caso 1: la inscripción ya tiene una Valoracion asociada --%>
                <c:when test="${not empty inscripcion.valoracion}">
                    <div class="valoracion-resumen">
                        <div class="valoracion-resumen__estrellas" aria-hidden="true">
                            <c:forEach begin="1" end="5" var="i">
                                <span class="valoracion-resumen__estrella
                                              ${i <= inscripcion.valoracion.valoracion ? 'valoracion-resumen__estrella--llena' : ''}">&#9733;</span>
                            </c:forEach>
                        </div>
                        <c:if test="${not empty inscripcion.valoracion.comentario}">
                            <p class="valoracion-resumen__comentario">${valoracion.comentario}</p>
                        </c:if>
                        <p class="valoracion-resumen__aviso">Ya has valorado este curso. Gracias por tu opinión.</p>
                    </div>
                </c:when>

                <%-- Caso 2: la inscripción todavía no tiene Valoracion --%>
                <c:otherwise>
                    <form class="formulario valoracion-form" method="post" action="app/valorarCurso">
                        <input type="hidden" name="inscripcionId" value="${inscripcion.id}">

                        <div class="formulario__campo">
                            <span class="formulario__etiqueta formulario__etiqueta--requerido">Puntuación</span>
                            <div class="valoracion__estrellas">
                                <input type="radio" id="estrella5" name="valoracion" value="5" required>
                                <label for="estrella5" title="5 estrellas">&#9733;</label>
                                <input type="radio" id="estrella4" name="valoracion" value="4">
                                <label for="estrella4" title="4 estrellas">&#9733;</label>
                                <input type="radio" id="estrella3" name="valoracion" value="3">
                                <label for="estrella3" title="3 estrellas">&#9733;</label>
                                <input type="radio" id="estrella2" name="valoracion" value="2">
                                <label for="estrella2" title="2 estrellas">&#9733;</label>
                                <input type="radio" id="estrella1" name="valoracion" value="1">
                                <label for="estrella1" title="1 estrella">&#9733;</label>
                            </div>
                        </div>

                        <div class="formulario__campo">
                            <label class="formulario__etiqueta" for="comentario">Comentario (opcional)</label>
                            <textarea class="formulario__textarea" id="comentario" name="comentario"
                                      placeholder="¿Qué te ha parecido el curso?"></textarea>
                        </div>

                        <div class="formulario__acciones">
                            <button type="submit" class="boton boton--primario">Enviar valoración</button>
                        </div>
                    </form>
                </c:otherwise>

            </c:choose>
        </div>
    </section>

    <section class="inscripcion-detalle__baja">
        <div class="contenedor inscripcion-detalle__baja-caja">
            <div class="inscripcion-detalle__baja-texto">
                <h2 class="curso-detalle__subtitulo">Darse de baja</h2>
                <p class="inscripcion-detalle__baja-aviso">
                    Si te desinscribes perderás el acceso a los contenidos de este curso
                    <c:if test="${not empty valoracion}"> y a tu valoración</c:if>.
                </p>
            </div>
            <a href="${urlActual}#confirmar-desinscripcion" class="boton boton--peligro">
                Desinscribirme del curso
            </a>

            <ui:confirmacion id="confirmar-desinscripcion"
                             urlActual="${urlActual}"
                             titulo="Darse de baja"
                             mensaje="¿Seguro que quieres desinscribirte de «${curso.titulo}»? Esta acción no se puede deshacer."
                             accionFormulario="app/desinscripcion"
                             textoConfirmar="Sí, desinscribirme">
                <jsp:attribute name="camposOcultos">
                    <input type="hidden" name="inscripcionId" value="${inscripcion.id}">
                </jsp:attribute>
            </ui:confirmacion>
        </div>
    </section>

</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>