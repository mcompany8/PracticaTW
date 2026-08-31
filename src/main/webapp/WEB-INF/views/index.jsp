<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Bienvenido a InfoFormacion</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="imagenes/logo.png" type="favicon/x-icon">
    <link rel="stylesheet" type="text/css" href="assets/css/base.css">
    <link rel="stylesheet" type="text/css" href="assets/css/layout.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/botones.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/formularios.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/menu.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/cursosGrid.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/modalConfirmacion.css">
    <link rel="stylesheet" type="text/css" href="assets/css/paginas/index.css">
</head>

<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/layout/menu.jsp"/>
</c:if>

<body>

<main>

    <%@include file="components/hero.jsp" %>

    <section class="cursos-destacados">
        <header class="cursos-destacados__header">
            <h2 class="cursos-destacados__title">Cursos destacados</h2>
            <a href="#" class="cursos-destacados__todos">Ver todos los cursos →</a>
        </header>
        <jsp:include page="components/cursosGrid.jsp"/>
    </section>

</main>

<%@ include file="layout/footer.jsp" %>

</body>
</html>


