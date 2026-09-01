<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Configuración"/>
<c:set var="extraCss" value="${['componentes/formularios.css','paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>
<main>
    <div class="contenedor detalle-curso">
        <h1 class="detalle-curso__titulo">Configuración de la plataforma</h1>

        <section class="detalle-curso__panel">
            <form class="formulario" method="post" action="app/guardarConfiguracion" enctype="multipart/form-data">

                <h2 class="curso-detalle__subtitulo">Portada (hero)</h2>

                <div class="formulario__campo">
                    <label for="heroTitulo" class="formulario__etiqueta formulario__etiqueta--requerido">Título</label>
                    <input type="text" id="heroTitulo" name="heroTitulo" class="formulario__input"
                           value="${config.heroTitulo}" required>
                </div>

                <div class="formulario__campo">
                    <label for="heroSubtitulo" class="formulario__etiqueta">Subtítulo</label>
                    <textarea class="formulario__textarea" id="heroSubtitulo" name="heroSubtitulo" rows="3">${config.heroSubtitulo}</textarea>
                </div>

                <div class="formulario__campo">
                    <span class="formulario__etiqueta">Imagen de fondo</span>
                    <c:if test="${not empty config.heroImagen}">
                        <img src="imagenes/${config.heroImagen}" alt="Hero actual"
                             class="detalle-curso__imagen-actual">
                    </c:if>
                    <input type="file" id="heroImagen" name="heroImagen" class="formulario__input"
                           accept="image/png, image/jpeg, image/webp">
                    <p class="formulario__ayuda">Deja este campo vacío para mantener la imagen actual.</p>
                </div>

                <h2 class="curso-detalle__subtitulo detalle-curso__subtitulo-materiales">Página de inicio</h2>

                <div class="formulario__campo">
                    <label for="numCursosRecomendados" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Número de cursos recomendados en la portada
                    </label>
                    <input type="number" id="numCursosRecomendados" name="numCursosRecomendados"
                           class="formulario__input" min="1" max="12"
                           value="${config.numCursosRecomendados}" required>
                </div>

                <div class="formulario__acciones">
                    <button type="submit" class="boton boton--primario">Guardar cambios</button>
                    <a href="app/admin" class="boton boton--secundario">Cancelar</a>
                </div>
            </form>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>