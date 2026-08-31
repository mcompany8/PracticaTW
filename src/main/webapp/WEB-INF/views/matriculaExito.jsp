<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Inscripción realizada · InfoFormación</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="imagenes/logo.png" type="favicon/x-icon">
    <link rel="stylesheet" type="text/css" href="assets/css/base.css">
    <link rel="stylesheet" type="text/css" href="assets/css/layout.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/botones.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/menu.css">
    <link rel="stylesheet" type="text/css" href="assets/css/componentes/modalConfirmacion.css">
    <link rel="stylesheet" type="text/css" href="assets/css/paginas/matriculaExito.css">
</head>

<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/layout/menu.jsp"/>
</c:if>

<body>

<main>
    <div class="contenedor matricula-exito">
        <div class="matricula-exito__tarjeta">

            <svg class="matricula-exito__icono" viewBox="0 0 24 24" aria-hidden="true">
                <use href="assets/icons/icons.svg#check"/>
            </svg>

            <h1 class="matricula-exito__titulo">¡Inscripción realizada con éxito!</h1>

            <p class="matricula-exito__texto">
                Te has matriculado correctamente en el curso
                <strong>«${curso.titulo}»</strong>.
            </p>

            <div class="matricula-exito__acciones">
                <a href="app/curso?id=${curso.id}" class="boton boton--secundario">
                    Ver curso
                </a>
                <a href="app/misCursos" class="boton boton--primario">
                    Ir a mis cursos
                </a>
            </div>

        </div>
    </div>
</main>

<%@ include file="layout/footer.jsp" %>
</body>
</html>