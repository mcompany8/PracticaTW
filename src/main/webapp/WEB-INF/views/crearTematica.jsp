<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Crear temática"/>
<c:set var="extraCss" value="${['componentes/formularios.css','paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>
<main>
    <div class="contenedor detalle-curso">
        <h1 class="detalle-curso__titulo">Crear temática</h1>

        <section class="detalle-curso__panel">
            <form class="formulario" method="post" action="app/guardarTematica" enctype="multipart/form-data">

                <div class="formulario__campo">
                    <label for="titulo" class="formulario__etiqueta formulario__etiqueta--requerido">Título</label>
                    <input type="text" id="titulo" name="titulo" class="formulario__input" required>
                </div>

                <div class="formulario__campo">
                    <label for="descripcion" class="formulario__etiqueta">Descripción</label>
                    <textarea class="formulario__textarea" id="descripcion" name="descripcion" rows="3"></textarea>
                </div>

                <div class="formulario__campo">
                    <label for="imagen" class="formulario__etiqueta">Imagen</label>
                    <input type="file" id="imagen" name="imagen" class="formulario__input"
                           accept="image/png, image/jpeg, image/webp">
                </div>

                <div class="formulario__acciones">
                    <button type="submit" class="boton boton--primario">Crear temática</button>
                    <a href="app/listarTematicas" class="boton boton--secundario">Cancelar</a>
                </div>
            </form>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>