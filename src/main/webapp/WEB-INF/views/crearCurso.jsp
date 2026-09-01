<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Crear curso"/>
<c:set var="extraCss" value=
        "${[
        'componentes/formularios.css',
        'paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="layout/menu.jsp"/>
</c:if>

<body>

<main>
    <div class="contenedor detalle-curso">

        <h1 class="detalle-curso__titulo">Crear curso</h1>

        <section class="detalle-curso__panel">
            <form class="formulario" method="post" action="app/guardarCurso" enctype="multipart/form-data">

                <div class="formulario__campo">
                    <label for="titulo" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Título
                    </label>
                    <input type="text" id="titulo" name="titulo" class="formulario__input" required>
                </div>

                <div class="formulario__campo">
                    <label for="descripcion" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Descripción corta
                    </label>
                    <input type="text" id="descripcion" name="descripcion" class="formulario__input" required>
                </div>

                <div class="formulario__campo">
                    <label for="descripcionLarga" class="formulario__etiqueta">
                        Descripción larga
                    </label>
                    <textarea class="formulario__textarea" id="descripcionLarga" name="descripcionLarga"
                              rows="6"></textarea>
                </div>

                <div class="formulario__fila">
                    <div class="formulario__campo">
                        <label for="nivel" class="formulario__etiqueta formulario__etiqueta--requerido">
                            Nivel
                        </label>
                        <select id="nivel" name="nivel" class="formulario__input" required>
                            <option value="BASICO">Básico</option>
                            <option value="INTERMEDIO">Intermedio</option>
                            <option value="AVANZADO">Avanzado</option>
                        </select>
                    </div>
                    <div class="formulario__campo">
                        <label for="duracionHoras" class="formulario__etiqueta">
                            Duración (horas)
                        </label>
                        <input type="number" id="duracionHoras" name="duracionHoras" class="formulario__input"
                               min="1">
                    </div>
                </div>

                <div class="formulario__campo">
                    <label for="imagen" class="formulario__etiqueta">Imagen del curso</label>
                    <input type="file" id="imagen" name="imagen" class="formulario__input"
                           accept="image/png, image/jpeg, image/webp">
                    <p class="formulario__ayuda">Si no subes ninguna, se usa una imagen genérica por defecto.</p>
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
                                        <input type="checkbox" name="tematicasIds" value="${tematica.id}">
                                            ${tematica.titulo}
                                    </label>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </fieldset>

                <div class="formulario__acciones">
                    <button type="submit" class="boton boton--primario">Crear curso</button>
                    <a href="app/cursosProf" class="boton boton--secundario">Cancelar</a>
                </div>
            </form>
        </section>

    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>