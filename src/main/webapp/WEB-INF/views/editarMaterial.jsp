<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Editar material"/>
<c:set var="extraCss" value=
        "${['componentes/formularios.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>

<main>
    <div class="contenedor detalle-curso__panel">
        <h1 class="detalle-curso__titulo">Editar material</h1>

        <form class="formulario" method="post" action="app/guardarMaterial" enctype="multipart/form-data">
            <input type="hidden" name="materialId" value="${material.id}">
            <input type="hidden" name="cursoId" value="${material.curso.id}">

            <div class="formulario__fila">
                <div class="formulario__campo">
                    <label for="titulo" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Título
                    </label>
                    <input type="text" id="titulo" name="titulo" class="formulario__input"
                           value="${material.titulo}" required>
                </div>
                <div class="formulario__campo">
                    <label for="orden" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Orden
                    </label>
                    <input type="number" id="orden" name="orden" class="formulario__input"
                           min="1" value="${material.orden}" required>
                </div>
            </div>

            <div class="formulario__campo">
                <label for="archivo" class="formulario__etiqueta">Reemplazar archivo</label>
                <input type="file" id="archivo" name="archivo" class="formulario__input"
                       accept=".pdf,.doc,.docx">
            </div>

            <div class="formulario__campo">
                <label for="url" class="formulario__etiqueta">O reemplazar por un enlace externo</label>
                <input type="url" id="url" name="url" class="formulario__input" placeholder="https://...">
                <p class="formulario__ayuda">Deja los dos vacíos para mantener el material actual (${material.uri}).</p>
            </div>

            <div class="formulario__acciones">
                <button type="submit" class="boton boton--primario">Guardar cambios</button>
                <a href="app/detalleCurso?id=${material.curso.id}&vista=materiales" class="boton boton--secundario">
                    Cancelar
                </a>
            </div>
        </form>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>