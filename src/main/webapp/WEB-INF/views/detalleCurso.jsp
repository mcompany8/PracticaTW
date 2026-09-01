<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="title" value="${curso.titulo}"/>
<c:set var="extraCss" value=
        "${[
        'componentes/formularios.css',
        'paginas/miInscripcion.css',
        'paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<c:set var="pestana" value="${empty param.vista ? 'info' : param.vista}"/>

<body>

<main>
    <div class="contenedor detalle-curso">

        <div class="detalle-curso__cabecera">
            <h1 class="detalle-curso__titulo">${curso.titulo}</h1>
            <a href="${pageContext.request.contextPath}/app/detalleCurso?id=${curso.id}#eliminar-curso"
               class="boton boton--peligro">
                Eliminar curso
            </a>
        </div>

        <nav class="detalle-curso__pestanas">
            <a href="app/detalleCurso?id=${curso.id}&vista=info"
               class="detalle-curso__pestana ${pestana == 'info' ? 'detalle-curso__pestana--activa' : ''}">
                Información
            </a>
            <a href="app/detalleCurso?id=${curso.id}&vista=materiales"
               class="detalle-curso__pestana ${pestana == 'materiales' ? 'detalle-curso__pestana--activa' : ''}">
                Materiales
            </a>
            <a href="app/detalleCurso?id=${curso.id}&vista=matriculados"
               class="detalle-curso__pestana ${pestana == 'matriculados' ? 'detalle-curso__pestana--activa' : ''}">
                Matriculados
            </a>
            <a href="app/detalleCurso?id=${curso.id}&vista=estadisticas"
               class="detalle-curso__pestana ${pestana == 'estadisticas' ? 'detalle-curso__pestana--activa' : ''}">
                Estadísticas
            </a>
        </nav>

        <%-- ============================================================
             INFORMACIÓN DEL CURSO
             ============================================================ --%>
        <c:if test="${pestana == 'info'}">
            <section class="detalle-curso__panel">
                <form class="formulario" method="post" action="app/actualizarCurso" enctype="multipart/form-data">
                    <input type="hidden" name="cursoId" value="${curso.id}">

                    <div class="formulario__campo">
                        <label for="titulo" class="formulario__etiqueta formulario__etiqueta--requerido">
                            Título
                        </label>
                        <input type="text" id="titulo" name="titulo" class="formulario__input"
                               value="${curso.titulo}" required>
                    </div>

                    <div class="formulario__campo">
                        <label for="descripcion" class="formulario__etiqueta formulario__etiqueta--requerido">
                            Descripción corta
                        </label>
                        <input type="text" id="descripcion" name="descripcion" class="formulario__input"
                               value="${curso.descripcion}" required>
                    </div>

                    <div class="formulario__campo">
                        <label for="descripcionLarga" class="formulario__etiqueta">
                            Descripción larga
                        </label>
                        <textarea class="formulario__textarea" id="descripcionLarga" name="descripcionLarga"
                                  rows="6">${curso.descripcionLarga}</textarea>
                    </div>

                    <div class="formulario__fila">
                        <div class="formulario__campo">
                            <label for="nivel" class="formulario__etiqueta formulario__etiqueta--requerido">
                                Nivel
                            </label>
                            <select id="nivel" name="nivel" class="formulario__input" required>
                                <option value="BASICO" ${curso.nivel == 'BASICO' ? 'selected' : ''}>Básico</option>
                                <option value="INTERMEDIO" ${curso.nivel == 'INTERMEDIO' ? 'selected' : ''}>Intermedio</option>
                                <option value="AVANZADO" ${curso.nivel == 'AVANZADO' ? 'selected' : ''}>Avanzado</option>
                            </select>
                        </div>
                        <div class="formulario__campo">
                            <label for="duracionHoras" class="formulario__etiqueta">
                                Duración (horas)
                            </label>
                            <input type="number" id="duracionHoras" name="duracionHoras" class="formulario__input"
                                   min="1" value="${curso.duracionHoras}">
                        </div>
                    </div>

                    <div class="formulario__campo">
                        <span class="formulario__etiqueta">Imagen del curso</span>
                        <c:if test="${not empty curso.imagen}">
                            <img src="imagenes/cursos/${curso.imagen}" alt="${curso.titulo}"
                                 class="detalle-curso__imagen-actual">
                        </c:if>
                        <input type="file" id="imagen" name="imagen" class="formulario__input"
                               accept="image/png, image/jpeg, image/webp">
                        <p class="formulario__ayuda">Deja este campo vacío para mantener la imagen actual.</p>
                    </div>

                    <fieldset class="formulario__campo">
                        <legend class="formulario__etiqueta">Temáticas</legend>
                        <c:choose>
                            <c:when test="${empty tematicas}">
                                <p class="formulario__ayuda">No hay temáticas disponibles todavía.</p>
                            </c:when>
                            <c:otherwise>
                                <div class="formulario__opciones-grid">
                                    <c:forEach var="tematica" items="${tematicas}">
                                        <label class="formulario__opcion">
                                            <input type="checkbox" name="tematicasIds" value="${tematica.id}"
                                                ${fn:contains(tematicasSeleccionadasCsv, ','.concat(tematica.id).concat(',')) ? 'checked' : ''}>
                                                ${tematica.titulo}
                                        </label>
                                    </c:forEach>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </fieldset>

                    <div class="formulario__acciones">
                        <button type="submit" class="boton boton--primario">Guardar cambios</button>
                    </div>
                </form>
            </section>
        </c:if>

        <%-- ============================================================
             MATERIALES
             ============================================================ --%>
        <c:if test="${pestana == 'materiales'}">
            <section class="detalle-curso__panel">

                <c:choose>
                    <c:when test="${empty materiales}">
                        <p class="inscripcion-detalle__vacio">Todavía no has añadido materiales a este curso.</p>
                    </c:when>
                    <c:otherwise>
                        <ul class="contenidos-lista">
                            <c:forEach var="material" items="${materiales}">
                                <li class="contenido-item">
                                    <div class="contenido-item__cuerpo">
                                        <p class="contenido-item__titulo">${material.titulo}</p>
                                    </div>

                                    <form class="contenido-item__orden-form" method="post" action="app/actualizarOrdenMaterial">
                                        <input type="hidden" name="materialId" value="${material.id}">
                                        <input type="hidden" name="cursoId" value="${curso.id}">
                                        <label class="contenido-item__orden-etiqueta" for="orden-${material.id}">Orden</label>
                                        <input type="number" id="orden-${material.id}" name="orden"
                                               class="contenido-item__orden-input" min="1" value="${material.orden}" required>
                                        <button type="submit" class="boton boton--secundario contenido-item__accion">
                                            Guardar
                                        </button>
                                    </form>

                                    <a href="${pageContext.request.contextPath}/app/editarMaterial?id=${material.id}&cursoId=${curso.id}"
                                       class="boton boton--secundario contenido-item__accion">
                                        Editar
                                    </a>

                                    <a href="${pageContext.request.contextPath}/app/detalleCurso?id=${curso.id}&vista=materiales#eliminar-material-${material.id}"
                                       class="boton boton--peligro contenido-item__accion">
                                        Eliminar
                                    </a>

                                    <ui:confirmacion id="eliminar-material-${material.id}"
                                                     urlActual="${urlActual}&vista=materiales"
                                                     titulo="Eliminar material"
                                                     mensaje="¿Seguro que quieres eliminar «${material.titulo}»? Los alumnos ya no podrán acceder a él."
                                                     accionFormulario="app/eliminarMaterial"
                                                     textoConfirmar="Sí, eliminar">
                        <jsp:attribute name="camposOcultos">
                            <input type="hidden" name="materialId" value="${material.id}">
                            <input type="hidden" name="cursoId" value="${curso.id}">
                        </jsp:attribute>
                                    </ui:confirmacion>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>

                <h2 class="curso-detalle__subtitulo detalle-curso__subtitulo-materiales">Añadir material</h2>

                <form class="formulario" method="post" action="app/anadirMaterial" enctype="multipart/form-data">
                    <input type="hidden" name="cursoId" value="${curso.id}">

                    <div class="formulario__fila">
                        <div class="formulario__campo">
                            <label for="tituloMaterial" class="formulario__etiqueta formulario__etiqueta--requerido">
                                Título
                            </label>
                            <input type="text" id="tituloMaterial" name="titulo" class="formulario__input" required>
                        </div>
                        <div class="formulario__campo">
                            <label for="ordenMaterial" class="formulario__etiqueta formulario__etiqueta--requerido">
                                Orden
                            </label>
                            <input type="number" id="ordenMaterial" name="orden" class="formulario__input"
                                   min="1" value="${siguienteOrden}" required>
                        </div>
                    </div>

                    <div class="formulario__campo">
                        <label for="archivo" class="formulario__etiqueta">Archivo (PDF, DOCX...)</label>
                        <input type="file" id="archivo" name="archivo" class="formulario__input"
                               accept=".pdf,.doc,.docx">
                    </div>

                    <div class="formulario__campo">
                        <label for="url" class="formulario__etiqueta">O enlace externo</label>
                        <input type="url" id="url" name="url" class="formulario__input"
                               placeholder="https://...">
                        <p class="formulario__ayuda">Rellena solo uno de los dos: archivo o enlace.</p>
                    </div>

                    <div class="formulario__acciones">
                        <button type="submit" class="boton boton--primario">Añadir material</button>
                    </div>
                </form>
            </section>
        </c:if>

        <%-- ============================================================
             MATRICULADOS
             ============================================================ --%>
        <c:if test="${pestana == 'matriculados'}">
            <section class="detalle-curso__panel">
                <c:choose>
                    <c:when test="${empty inscripciones}">
                        <p class="inscripcion-detalle__vacio">Todavía no hay alumnos matriculados en este curso.</p>
                    </c:when>
                    <c:otherwise>
                        <table class="detalle-curso__tabla">
                            <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Correo electrónico</th>
                                <th>Fecha de inscripción</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="inscripcion" items="${inscripciones}">
                                <tr>
                                    <td>${inscripcion.estudiante.nombre} ${inscripcion.estudiante.apellidos}</td>
                                    <td>${inscripcion.estudiante.email}</td>
                                    <td>${inscripcion.fechaInscripcionFormateada}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </section>
        </c:if>

        <%-- ============================================================
             ESTADÍSTICAS
             ============================================================ --%>
        <c:if test="${pestana == 'estadisticas'}">
            <section class="detalle-curso__panel">

                <div class="detalle-curso__stats">
                    <div class="detalle-curso__stat">
                        <span class="detalle-curso__stat-numero">${totalInscritos}</span>
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
                        <span class="detalle-curso__stat-etiqueta">Valoración media</span>
                    </div>
                    <div class="detalle-curso__stat">
                        <span class="detalle-curso__stat-numero">${fn:length(valoraciones)}</span>
                        <span class="detalle-curso__stat-etiqueta">Valoraciones recibidas</span>
                    </div>
                </div>

                <c:if test="${not empty valoraciones}">
                    <h2 class="curso-detalle__subtitulo">Comentarios</h2>
                    <ul class="detalle-curso__comentarios">
                        <c:forEach var="valoracion" items="${valoraciones}">
                            <li class="detalle-curso__comentario">
                                <div class="valoracion-resumen__estrellas" aria-hidden="true">
                                    <c:forEach begin="1" end="5" var="i">
                                        <span class="valoracion-resumen__estrella
                                                      ${i <= valoracion.valoracion ? 'valoracion-resumen__estrella--llena' : ''}">&#9733;</span>
                                    </c:forEach>
                                </div>
                                <c:if test="${not empty valoracion.comentario}">
                                    <p class="detalle-curso__comentario-texto">${valoracion.comentario}</p>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </c:if>
            </section>
        </c:if>

    </div>
</main>

<ui:confirmacion id="eliminar-curso"
                 urlActual="${urlActual}"
                 titulo="Eliminar curso"
                 mensaje="¿Seguro que quieres eliminar «${curso.titulo}»? Se eliminarán también todas las inscripciones, valoraciones y materiales asociados. Esta acción no se puede deshacer."
                 accionFormulario="app/eliminarCurso"
                 textoConfirmar="Sí, eliminar curso">
    <jsp:attribute name="camposOcultos">
        <input type="hidden" name="cursoId" value="${curso.id}">
    </jsp:attribute>
</ui:confirmacion>

<%@ include file="layout/footer.jspf" %>
</body>
</html>