<%-- editarTematica.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Editar temática"/>
<c:set var="extraCss" value="${['componentes/formularios.css','paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>
<main>
    <div class="contenedor detalle-curso">
        <h1 class="detalle-curso__titulo">Editar temática</h1>

        <section class="detalle-curso__panel">
            <form class="formulario" method="post" action="app/actualizarTematica" enctype="multipart/form-data">
                <input type="hidden" name="tematicaId" value="${tematica.id}">

                <div class="formulario__campo">
                    <label for="titulo" class="formulario__etiqueta formulario__etiqueta--requerido">Título</label>
                    <input type="text" id="titulo" name="titulo" class="formulario__input"
                           value="${tematica.titulo}" required>
                </div>

                <div class="formulario__campo">
                    <label for="descripcion" class="formulario__etiqueta">Descripción</label>
                    <textarea class="formulario__textarea" id="descripcion" name="descripcion"
                              rows="3">${tematica.descripcion}</textarea>
                </div>

                <div class="formulario__campo">
                    <span class="formulario__etiqueta">Imagen</span>
                    <c:if test="${not empty tematica.imagen}">
                        <img src="imagenes/tematicas/${tematica.imagen}" alt="${tematica.titulo}"
                             class="detalle-curso__imagen-actual">
                    </c:if>
                    <input type="file" id="imagen" name="imagen" class="formulario__input"
                           accept="image/png, image/jpeg, image/webp">
                    <p class="formulario__ayuda">Deja este campo vacío para mantener la imagen actual.</p>
                </div>

                <div class="formulario__acciones">
                    <button type="submit" class="boton boton--primario">Guardar cambios</button>
                    <a href="app/listarTematicas" class="boton boton--secundario">Cancelar</a>
                </div>
            </form>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>