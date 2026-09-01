<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<%-- Si la petición llegó vía forward (caso normal: FrontController -> Command -> forward al JSP),
     request.getRequestURI() devuelve la ruta INTERNA del JSP (/WEB-INF/views/...), no la pública.
     La URI/query string originales se exponen en estos atributos especiales del forward. --%>
<c:set var="urlActual" value="${requestScope['jakarta.servlet.forward.request_uri']}"/>
<c:if test="${empty urlActual}">
    <c:set var="urlActual" value="${pageContext.request.requestURI}"/>
</c:if>

<c:set var="queryStringActual" value="${requestScope['jakarta.servlet.forward.query_string']}"/>
<c:if test="${empty queryStringActual}">
    <c:set var="queryStringActual" value="${pageContext.request.queryString}"/>
</c:if>
<c:if test="${not empty queryStringActual}">
    <c:set var="urlActual" value="${urlActual}?${queryStringActual}"/>
</c:if>


<header class="cabecera">
    <a href="${pageContext.request.contextPath}/" class="cabecera__logo-enlace">
        <img src="imagenes/logo.png"
             alt="InfoFormación" class="cabecera__logo">
    </a>
    <nav class="cabecera__nav">
        <a href="${pageContext.request.contextPath}/" class="cabecera__enlace">Inicio</a>
        <a href="${pageContext.request.contextPath}/app/catalogo" class="cabecera__enlace">Catálogo</a>

        <c:if test="${not empty sessionScope.usuario}">
            <c:choose>
                <c:when test="${sessionScope.usuario.tipoUsuario == 'Estudiante'}">
                    <a href="app/misCursos"
                       class="cabecera__enlace ${fn:contains(urlActual, '/app/misCursos') ? 'cabecera__enlace--activo' : ''}">
                        Mis cursos
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="app/cursosProf"
                       class="cabecera__enlace ${fn:contains(urlActual, '/app/cursosProf') ? 'cabecera__enlace--activo' : ''}">
                        Mis cursos
                    </a>
                </c:otherwise>
            </c:choose>

            <c:if test="${sessionScope.usuario.tipoUsuario == 'Administrador'}">
                <span class="cabecera__separador"></span>
                <a href="app/listarUsuarios" class="cabecera__enlace">Usuarios</a>
                <a href="app/admin" class="cabecera__enlace">Administración</a>
            </c:if>

            <span class="cabecera__separador"></span>
        </c:if>

        <c:choose>
            <c:when test="${empty sessionScope.usuario}">
                <a href="app/login" class="cabecera__enlace">Iniciar sesión</a>
            </c:when>
            <c:otherwise>
                <a href="app/perfil" class="cabecera__enlace">Mi cuenta</a>
                <a href="#confirmar-logout" class="cabecera__enlace">Cerrar sesión</a>
                <ui:confirmacion id="confirmar-logout"
                                 urlActual="${urlActual}"
                                 mensaje="¿Seguro que quieres cerrar sesión?"
                                 urlConfirmar="${pageContext.request.contextPath}/app/logout"
                                 textoConfirmar="Cerrar sesión"/>
            </c:otherwise>
        </c:choose>
    </nav>
</header>