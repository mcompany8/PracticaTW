<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>

<%--<!DOCTYPE html>--%>
<%--<html lang="es">--%>
<%--<head>--%>
<%--    <meta charset="utf-8" >--%>
<%--    <title>Bienvenido a Infoformacion</title>--%>
<%--    <base href="${pageContext.request.contextPath}/">--%>
<%--    <link rel="icon" href="imagenes/logo.png" type="favicon/x-icon">--%>
<%--    <link rel="stylesheet" type="text/css" href="assets/css/styles.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="assets/css/componentes.css">--%>
<%--</head>--%>
<%--<%@ include file="common/header.jsp" %>--%>
<%--<c:if test="${!empty sessionScope.usuario}">--%>
<%--    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>--%>
<%--</c:if>--%>
<%--<body>--%>

<%--<main>--%>

<%--    <h2>Mis cursos</h2>--%>

<%--    <section class="lista-cursos">--%>

<%--        <ul class="lista-cursos__items">--%>
<%--            <c:forEach var="curso" items="${requestScope.cursos}">--%>
<%--                <li class="lista-cursos__item">--%>
<%--                    <a href="app/detalleCurso?id=${curso.id}">--%>
<%--                        <img src="imagenes/${curso.imagen}"--%>
<%--                             alt="">--%>
<%--                        <h3>${curso.titulo}</h3>--%>
<%--                    </a>--%>

<%--                </li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </section>--%>

<%--    <a href="app/nuevoCurso" class="link-icon">--%>
<%--        <img src="assets/icons/plus-solid-full.svg" alt="">--%>
<%--        <h3>Añadir nuevo curso</h3>--%>
<%--    </a>--%>


<%--</main>--%>



<%--<%@ include file="common/footer.jsp" %>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8" >
    <title>Bienvenido a InfoFormacion</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="imagenes/logo.png" type="favicon/x-icon">
    <link rel="stylesheet" type="text/css" href="assets/css/base.css">
    <link rel="stylesheet" type="text/css" href="assets/css/layout.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/botones.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/formularios.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/menu.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/cursosGrid.css">
    <link rel="stylesheet" type="text/css" href="assets/css/paginas/index.css">
</head>

<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/layout/menu.jsp"/>
</c:if>

<body>

<main>
    <div class="contenedor cursos-lista-pagina">

        <div class="cursos-lista-pagina__cabecera">
            <h1 class="cursos-lista-pagina__titulo">Mis cursos</h1>
            <a href="${pageContext.request.contextPath}/app/curso?accion=crear"
               class="boton boton--primario">Crear curso</a>
        </div>

        <c:choose>
            <c:when test="${empty cursos}">
                <p class="cursos-lista-pagina__vacio">
                    Todavía no tienes cursos asignados.
                    <a href="${pageContext.request.contextPath}/app/curso?accion=crear">Crea tu primer curso</a>.
                </p>
            </c:when>
            <c:otherwise>
                <ul class="cursos-lista">
                    <c:forEach items="${cursos}" var="curso">
                        <li>
                            <article class="curso-lista-item">

                                <a href="${pageContext.request.contextPath}/app/curso?id=${curso.id}"
                                   class="curso-lista-item__imagen-enlace">
                                    <img src="imagenes/cursos/${curso.imagen}"
                                         alt="${curso.titulo}" class="curso-lista-item__imagen">
                                </a>

                                <div class="curso-lista-item__info">
                                    <div class="curso-lista-item__cabecera">
                                        <h2 class="curso-lista-item__titulo">
                                            <a href="${pageContext.request.contextPath}/app/curso?id=${curso.id}">
                                                    ${curso.titulo}
                                            </a>
                                        </h2>
                                        <span class="curso-lista-item__nivel curso-lista-item__nivel--${curso.nivel}">
                                                ${curso.nivel}
                                        </span>
                                    </div>

                                    <p class="curso-lista-item__descripcion">${curso.descripcion}</p>

<%--                                    <ul class="curso-lista-item__meta">--%>
<%--                                        <li><fmt:formatDate value="${curso.fecha}" pattern="dd/MM/yyyy"/></li>--%>
<%--                                        <li>${curso.numEstudiantes} estudiantes matriculados</li>--%>
<%--                                    </ul>--%>
                                </div>

                                <div class="curso-lista-item__acciones">
                                    <a href="${pageContext.request.contextPath}/app/curso?id=${curso.id}&accion=matriculados"
                                       class="boton boton--secundario">Ver alumnos</a>
                                    <a href="${pageContext.request.contextPath}/app/curso?id=${curso.id}&accion=editar"
                                       class="boton boton--primario">Editar</a>
                                </div>

                            </article>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>

    </div>
</main>

<%@ include file="layout/footer.jsp" %>
</body>
</html>