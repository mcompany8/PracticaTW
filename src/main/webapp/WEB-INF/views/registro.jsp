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
    <link rel="stylesheet" type="text/css" href="assets/css/paginas/login.css">
</head>

<%@ include file="layout/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/layout/menu.jsp"/>
</c:if>

<c:set var="credencialesInvalidas" value="${sessionScope.credencialesInvalidas}" scope="page"/>
<c:remove var="credencialesInvalidas" scope="session"/>

<body class="login-page">

<main class="login">
    <div class="login__tarjeta login__tarjeta--ancha">

        <h1 class="login__titulo">Crear cuenta</h1>

        <c:if test="${not empty error}">
            <p class="formulario__aviso formulario__aviso--error" role="alert">${error}</p>
        </c:if>

        <form class="formulario" method="post"
              action="app/registro">

            <input type="hidden" name="tipo_usuario" value="ESTUDIANTE">

            <div class="formulario__fila">
                <div class="formulario__campo">
                    <label for="nombre" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Nombre
                    </label>
                    <input type="text" id="nombre" name="nombre" class="formulario__input"
                           value="${param.nombre}" required autofocus>
                </div>
                <div class="formulario__campo">
                    <label for="apellidos" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Apellidos
                    </label>
                    <input type="text" id="apellidos" name="apellidos" class="formulario__input"
                           value="${param.apellidos}" required>
                </div>
            </div>

            <div class="formulario__campo">
                <label for="email" class="formulario__etiqueta formulario__etiqueta--requerido">
                    Correo electrónico
                </label>
                <input type="email" id="email" name="email" class="formulario__input"
                       value="${param.email}" required>
            </div>

            <div class="formulario__fila">
                <div class="formulario__campo">
                    <label for="password" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Contraseña
                    </label>
                    <input type="password" id="password" name="password" class="formulario__input"
                           required minlength="6">
                </div>
                <div class="formulario__campo">
                    <label for="confirmarPassword" class="formulario__etiqueta formulario__etiqueta--requerido">
                        Confirmar contraseña
                    </label>
                    <input type="password" id="confirmarPassword" name="confirmarPassword"
                           class="formulario__input" required minlength="6">
                </div>
            </div>

            <div class="formulario__campo">
                <label for="direccion" class="formulario__etiqueta">Dirección</label>
                <input type="text" id="direccion" name="direccion" class="formulario__input"
                       value="${param.direccion}">
            </div>

            <div class="formulario__fila">
                <div class="formulario__campo">
                    <label for="poblacion" class="formulario__etiqueta">Población</label>
                    <input type="text" id="poblacion" name="poblacion" class="formulario__input"
                           value="${param.poblacion}">
                </div>
                <div class="formulario__campo">
                    <label for="provincia" class="formulario__etiqueta">Provincia</label>
                    <input type="text" id="provincia" name="provincia" class="formulario__input"
                           value="${param.provincia}">
                </div>
                <div class="formulario__campo">
                    <label for="codigoPostal" class="formulario__etiqueta">Código postal</label>
                    <input type="text" id="codigoPostal" name="codigoPostal" class="formulario__input"
                           inputmode="numeric" pattern="[0-9]{5}" value="${param.codigoPostal}">
                </div>
            </div>

            <button type="submit" class="boton boton--primario boton--bloque">Crear cuenta</button>
        </form>

        <p class="login__registro">
            ¿Ya tienes cuenta?
            <a href="app/login" class="login__enlace-registro">
                Inicia sesión
            </a>
        </p>

    </div>
</main>


<%@ include file="layout/footer.jsp" %>

</body>

</html>