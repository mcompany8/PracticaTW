<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="title" value="Mi cuenta"/>
<c:set var="extraCss" value=
        "${[
        'componentes/formularios.css',
        'paginas/login.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<c:set var="perfilActualizado" value="${sessionScope.perfilActualizado}" scope="page"/>
<c:remove var="perfilActualizado" scope="session"/>

<body>

<main class="login">
    <div class="login__tarjeta login__tarjeta--ancha">

        <h1 class="login__titulo">Mi cuenta</h1>

        <c:if test="${not empty perfilActualizado}">
            <p class="formulario__aviso formulario__aviso--exito" role="status">${perfilActualizado}</p>
        </c:if>

        <form class="formulario" method="post" action="app/actualizarPerfil">

            <div class="formulario__campo">
                <label class="formulario__etiqueta">Correo electrónico</label>
                <p class="formulario__ayuda">${sessionScope.usuario.email}</p>
            </div>

            <div class="formulario__fila">
                <div class="formulario__campo">
                    <label for="nombre" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Nombre
                    </label>
                    <input type="text" id="nombre" name="nombre" class="formulario__input"
                           value="${sessionScope.usuario.nombre}" required>
                </div>
                <div class="formulario__campo">
                    <label for="apellidos" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Apellidos
                    </label>
                    <input type="text" id="apellidos" name="apellidos" class="formulario__input"
                           value="${sessionScope.usuario.apellidos}" required>
                </div>
            </div>

            <div class="formulario__campo">
                <label for="direccion" class="formulario__etiqueta">Dirección</label>
                <input type="text" id="direccion" name="direccion" class="formulario__input"
                       value="${sessionScope.usuario.direccion}">
            </div>

            <div class="formulario__fila">
                <div class="formulario__campo">
                    <label for="poblacion" class="formulario__etiqueta">Población</label>
                    <input type="text" id="poblacion" name="poblacion" class="formulario__input"
                           value="${sessionScope.usuario.poblacion}">
                </div>
                <div class="formulario__campo">
                    <label for="provincia" class="formulario__etiqueta">Provincia</label>
                    <input type="text" id="provincia" name="provincia" class="formulario__input"
                           value="${sessionScope.usuario.provincia}">
                </div>
                <div class="formulario__campo">
                    <label for="codigopostal" class="formulario__etiqueta">Código postal</label>
                    <input type="text" id="codigopostal" name="codigopostal" class="formulario__input"
                           inputmode="numeric" pattern="[0-9]{5}" value="${sessionScope.usuario.codigopostal}">
                </div>
            </div>

            <c:if test="${sessionScope.usuario.tipoUsuario == 'Estudiante'}">
                <fieldset class="formulario__campo">
                    <legend class="formulario__etiqueta">Temáticas de interés</legend>

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
            </c:if>

            <button type="submit" class="boton boton--primario boton--bloque">Guardar cambios</button>
        </form>

    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>