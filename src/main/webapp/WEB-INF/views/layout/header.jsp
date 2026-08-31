<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>


<%--<header>--%>
<%--    <figure>--%>
<%--        <img src="imagenes/logo.png" alt="Logotipo" id="logo">--%>
<%--    </figure>--%>
<%--    <nav>--%>
<%--        <ul>--%>
<%--            <li><a href="app/inicio">INICIO</a></li>--%>
<%--            <li><a href="#">CATÁLOGO</a></li>--%>
<%--            <li>--%>
<%--                <c:choose>--%>
<%--                <c:when test="${empty sessionScope.usuario}">--%>
<%--                <a href="app/login">INICIAR SESIÓN</a>--%>
<%--                </c:when>--%>
<%--                <c:otherwise>--%>
<%--                <a href="perfil">MI CUENTA</a>--%>
<%--                </c:otherwise>--%>
<%--                </c:choose>--%>

<%--                <c:if test="${usuario.tipoUsuario == 'Profesor' or usuario.tipoUsuario == 'Administrador'}">--%>
<%--            <li><a href="app/vercursos">DOCENCIA</a></li>--%>
<%--            </c:if>--%>
<%--            <li>--%>
<%--                <div class="avatar-container">--%>
<%--                    <div class="avatar-circle">--%>
<%--                        ${fn:substring(usuario.nombre, 0, 1)}${fn:substring(usuario.apellidos, 0, 1)}--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                &lt;%&ndash;                    <ul class="avatar-menu">&ndash;%&gt;--%>
<%--                &lt;%&ndash;                        <li>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                            <a href="#">Editar datos personales</a>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                        </li>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                        <li>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                            <a href="#">Cerrar sesión</a>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                        </li>&ndash;%&gt;--%>
<%--                &lt;%&ndash;                    </ul>&ndash;%&gt;--%>

<%--            </li>--%>
<%--        </ul>--%>
<%--    </nav>--%>
<%--</header>--%>

<header class="cabecera">
    <a href="${pageContext.request.contextPath}/" class="cabecera__logo-enlace">
        <img src="imagenes/logo.png"
             alt="InfoFormación" class="cabecera__logo">
    </a>
    <nav class="cabecera__nav">
        <a href="${pageContext.request.contextPath}/" class="cabecera__enlace">Inicio</a>
        <a href="${pageContext.request.contextPath}/app/catalogo" class="cabecera__enlace">Catálogo</a>
        <c:choose>
            <c:when test="${empty sessionScope.usuario}">
                <a href="app/login" class="cabecera__enlace">Iniciar sesión</a>
            </c:when>
            <c:otherwise>
                <a href="app/perfil" class="cabecera__enlace">Mi cuenta</a>
                <a href="app/logout" class="cabecera__enlace">Cerrar sesión</a>
            </c:otherwise>
        </c:choose>
    </nav>
</header>