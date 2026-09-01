<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Crear usuario"/>
<c:set var="extraCss" value="${['componentes/formularios.css','paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<c:set var="errorUsuario" value="${sessionScope.errorUsuario}" scope="page"/>
<c:remove var="errorUsuario" scope="session"/>

<body>
<main>
    <div class="contenedor detalle-curso">
        <h1 class="detalle-curso__titulo">Crear usuario</h1>

        <section class="detalle-curso__panel">
            <c:if test="${not empty errorUsuario}">
                <p class="formulario__aviso formulario__aviso--error" role="alert">${errorUsuario}</p>
            </c:if>

            <form class="formulario" method="post" action="app/guardarUsuarioAdmin">

                <div class="formulario__campo">
                    <label for="rol" class="formulario__etiqueta formulario__etiqueta--requerido">Rol</label>
                    <select id="rol" name="rol" class="formulario__input" required>
                        <option value="ESTUDIANTE">Estudiante</option>
                        <option value="PROFESOR">Profesor</option>
                        <option value="ADMINISTRADOR">Administrador</option>
                    </select>
                </div>

                <div class="formulario__fila">
                    <div class="formulario__campo">
                        <label for="nombre" class="formulario__etiqueta formulario__etiqueta--requerido">Nombre</label>
                        <input type="text" id="nombre" name="nombre" class="formulario__input" required>
                    </div>
                    <div class="formulario__campo">
                        <label for="apellidos" class="formulario__etiqueta formulario__etiqueta--requerido">Apellidos</label>
                        <input type="text" id="apellidos" name="apellidos" class="formulario__input" required>
                    </div>
                </div>

                <div class="formulario__fila">
                    <div class="formulario__campo">
                        <label for="email" class="formulario__etiqueta formulario__etiqueta--requerido">Correo electrónico</label>
                        <input type="email" id="email" name="email" class="formulario__input" required>
                    </div>
                    <div class="formulario__campo">
                        <label for="password" class="formulario__etiqueta formulario__etiqueta--requerido">Contraseña</label>
                        <input type="password" id="password" name="password" class="formulario__input" required minlength="6">
                    </div>
                </div>

                <div class="formulario__campo">
                    <label for="direccion" class="formulario__etiqueta">Dirección</label>
                    <input type="text" id="direccion" name="direccion" class="formulario__input">
                </div>

                <div class="formulario__fila">
                    <div class="formulario__campo">
                        <label for="poblacion" class="formulario__etiqueta">Población</label>
                        <input type="text" id="poblacion" name="poblacion" class="formulario__input">
                    </div>
                    <div class="formulario__campo">
                        <label for="provincia" class="formulario__etiqueta">Provincia</label>
                        <input type="text" id="provincia" name="provincia" class="formulario__input">
                    </div>
                    <div class="formulario__campo">
                        <label for="codigopostal" class="formulario__etiqueta">Código postal</label>
                        <input type="text" id="codigopostal" name="codigopostal" class="formulario__input">
                    </div>
                </div>

                <div class="formulario__acciones">
                    <button type="submit" class="boton boton--primario">Crear usuario</button>
                    <a href="app/listarUsuarios" class="boton boton--secundario">Cancelar</a>
                </div>
            </form>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>