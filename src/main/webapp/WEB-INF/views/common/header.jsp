<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<header>
    <figure>
        <img src="assets/img/logo.png" alt="Logotipo" id="logo">
    </figure>
    <nav>
        <ul>
            <li><a href="app/inicio">INICIO</a></li>
            <li><a href="#">CATÁLOGO</a></li>
            <li><a href="#">CONTACTO</a></li>
            <li>
                <c:choose>
                <c:when test="${empty sessionScope.usuario}">
                <a href="app/login">INICIAR SESIÓN</a>
                </c:when>
                <c:otherwise>
                <a href="perfil">MI CUENTA</a>
                </c:otherwise>
                </c:choose>

                <c:if test="${usuario.tipoUsuario == 'Profesor' or usuario.tipoUsuario == 'Administrador'}">
            <li><a href="app/vercursos">DOCENCIA</a></li>
            </c:if>
            <li>
                <div class="avatar-container">
                    <div class="avatar-circle">
                        ${fn:substring(usuario.nombre, 0, 1)}${fn:substring(usuario.apellidos, 0, 1)}
                    </div>
                </div>
                <%--                    <ul class="avatar-menu">--%>
                <%--                        <li>--%>
                <%--                            <a href="#">Editar datos personales</a>--%>
                <%--                        </li>--%>
                <%--                        <li>--%>
                <%--                            <a href="#">Cerrar sesión</a>--%>
                <%--                        </li>--%>
                <%--                    </ul>--%>

            </li>
        </ul>
    </nav>
</header>