<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags" %>


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
                <a href="#confirmar-logout" class="cabecera__enlace">Cerrar sesión</a>
                <ui:confirmacion id="confirmar-logout"
                                 mensaje="¿Seguro que quieres cerrar sesión?"
                                 urlConfirmar="app/logout"
                                 textoConfirmar="Cerrar sesión"/>
            </c:otherwise>
        </c:choose>
    </nav>
</header>