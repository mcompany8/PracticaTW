<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Editar usuario"/>
<c:set var="extraCss" value="${['componentes/formularios.css','paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<body>
<main>
    <div class="contenedor detalle-curso">
        <h1 class="detalle-curso__titulo">Editar usuario</h1>

        <section class="detalle-curso__panel">
            <form class="formulario" method="post" action="app/actualizarUsuarioAdmin">
                <input type="hidden" name="usuarioId" value="${usuarioEditado.id}">

                <div class="formulario__campo">
                    <label class="formulario__etiqueta">Correo electrónico</label>
                    <p class="formulario__ayuda">${usuarioEditado.email}</p>
                </div>

                <div class="formulario__fila">
                    <div class="formulario__campo">
                        <label for="nombre" class="formulario__etiqueta formulario__etiqueta--requerido">Nombre</label>
                        <input type="text" id="nombre" name="nombre" class="formulario__input"
                               value="${usuarioEditado.nombre}" required>
                    </div>
                    <div class="formulario__campo">
                        <label for="apellidos" class="formulario__etiqueta formulario__etiqueta--requerido">Apellidos</label>
                        <input type="text" id="apellidos" name="apellidos" class="formulario__input"
                               value="${usuarioEditado.apellidos}" required>
                    </div>
                </div>

                <div class="formulario__campo">
                    <label for="direccion" class="formulario__etiqueta">Dirección</label>
                    <input type="text" id="direccion" name="direccion" class="formulario__input"
                           value="${usuarioEditado.direccion}">
                </div>

                <div class="formulario__fila">
                    <div class="formulario__campo">
                        <label for="poblacion" class="formulario__etiqueta">Población</label>
                        <input type="text" id="poblacion" name="poblacion" class="formulario__input"
                               value="${usuarioEditado.poblacion}">
                    </div>
                    <div class="formulario__campo">
                        <label for="provincia" class="formulario__etiqueta">Provincia</label>
                        <input type="text" id="provincia" name="provincia" class="formulario__input"
                               value="${usuarioEditado.provincia}">
                    </div>
                    <div class="formulario__campo">
                        <label for="codigopostal" class="formulario__etiqueta">Código postal</label>
                        <input type="text" id="codigopostal" name="codigopostal" class="formulario__input"
                               value="${usuarioEditado.codigopostal}">
                    </div>
                </div>

                <c:if test="${usuarioEditado.tipoUsuario != 'Estudiante'}">
                    <div class="formulario__campo">
                        <label class="formulario__opcion">
                            <input type="checkbox" name="esAdministrador"
                                ${usuarioEditado.tipoUsuario == 'Administrador' ? 'checked' : ''}>
                            Es administrador
                        </label>
                        <p class="formulario__ayuda">
                            Solo se puede alternar entre Profesor y Administrador. El rol de Estudiante no se
                            puede cambiar desde aquí (más abajo te explico por qué).
                        </p>
                    </div>
                </c:if>

                <div class="formulario__acciones">
                    <button type="submit" class="boton boton--primario">Guardar cambios</button>
                    <a href="app/listarUsuarios" class="boton boton--secundario">Cancelar</a>
                </div>
            </form>
        </section>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>