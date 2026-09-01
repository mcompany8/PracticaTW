<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>
<c:set var="extraCss" value=
        "${[
        'componentes/formularios.css',
        'paginas/login.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<c:set var="credencialesInvalidas" value="${sessionScope.credencialesInvalidas}" scope="page"/>
<c:remove var="credencialesInvalidas" scope="session"/>

<body class="login-page">

<main class="login">

    <div class="login__tarjeta">

        <a href="${pageContext.request.contextPath}/" class="login__logo-enlace">
            <img src="imagenes/logo.png"
                 alt="InfoFormación" class="login__logo">
        </a>

        <h1 class="login__titulo">Iniciar sesión</h1>

        <c:if test="${not empty error}">
            <p class="formulario__aviso formulario__aviso--error" role="alert">${error}</p>
        </c:if>

        <form class="formulario" method="post"
              action="app/doLogin">

            <div class="formulario__campo">
                <label for="email" class="formulario__etiqueta formulario__etiqueta--requerido">
                    Correo electrónico
                </label>
                <input type="email" id="email" name="email" class="formulario__input"
                       value="mgarcia@dummy.es" required autofocus>
            </div>

            <div class="formulario__campo">
                <label for="password" class="formulario__etiqueta formulario__etiqueta--requerido">
                    Contraseña
                </label>
                <input type="password" id="password" name="password" class="formulario__input"
                       required minlength="5" value="pass1">
            </div>

            <button type="submit" class="boton boton--primario boton--bloque">Entrar</button>
        </form>

        <p class="login__registro">
            ¿No tienes cuenta?
            <a href="app/registro" class="login__enlace-registro">
                Regístrate aquí
            </a>
        </p>

    </div>
</main>


<%@ include file="layout/footer.jspf" %>

</body>

</html>