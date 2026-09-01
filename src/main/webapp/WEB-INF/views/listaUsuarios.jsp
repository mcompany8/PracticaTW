<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<c:set var="title" value="Usuarios"/>
<c:set var="extraCss" value=
        "${[
        'paginas/detalleCurso.css',
        'paginas/listaUsuarios.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<c:set var="errorUsuario" value="${sessionScope.errorUsuario}" scope="page"/>
<c:remove var="errorUsuario" scope="session"/>

<body>
<main>
    <div class="contenedor detalle-curso">

        <div class="detalle-curso__cabecera">
            <h1 class="detalle-curso__titulo">Usuarios</h1>
            <a href="app/crearUsuarioAdmin" class="boton boton--primario">+ Crear usuario</a>
        </div>

        <c:if test="${not empty errorUsuario}">
            <p class="formulario__aviso formulario__aviso--error" role="alert">${errorUsuario}</p>
        </c:if>

        <c:choose>
            <c:when test="${empty usuarios}">
                <p class="inscripcion-detalle__vacio">Todavía no hay usuarios registrados.</p>
            </c:when>
            <c:otherwise>
                <table class="detalle-curso__tabla">
                    <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Correo electrónico</th>
                        <th>Rol</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="usuario" items="${usuarios}">
                        <tr>
                            <td>${usuario.nombre} ${usuario.apellidos}</td>
                            <td>${usuario.email}</td>
                            <td>
                                <span class="lista-usuarios__rol lista-usuarios__rol--${fn:toLowerCase(usuario.tipoUsuario)}">
                                        ${usuario.tipoUsuario}
                                </span>
                            </td>
                            <td class="lista-usuarios__acciones">
                                <a href="app/editarUsuarioAdmin?id=${usuario.id}" class="boton boton--secundario">
                                    Editar
                                </a>
                                <c:if test="${usuario.id != sessionScope.usuario.id}">
                                    <a href="${pageContext.request.contextPath}/app/listarUsuarios#eliminar-usuario-${usuario.id}"
                                       class="boton boton--peligro">
                                        Eliminar
                                    </a>
                                    <ui:confirmacion id="eliminar-usuario-${usuario.id}"
                                                     urlActual="${urlActual}"
                                                     titulo="Eliminar usuario"
                                                     mensaje="¿Seguro que quieres eliminar a ${usuario.nombre} ${usuario.apellidos}? Esta acción no se puede deshacer."
                                                     accionFormulario="app/eliminarUsuarioAdmin"
                                                     textoConfirmar="Sí, eliminar">
                                        <jsp:attribute name="camposOcultos">
                                            <input type="hidden" name="usuarioId" value="${usuario.id}">
                                        </jsp:attribute>
                                    </ui:confirmacion>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>