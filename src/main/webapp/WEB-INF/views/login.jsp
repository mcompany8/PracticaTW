<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>
<c:set var="credencialesInvalidas" value="${sessionScope.credencialesInvalidas}" scope="page"/>
<c:remove var="credencialesInvalidas" scope="session"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>

<section>
    <figure class="fig-logo">
        <img src="assets/img/logo.png" alt="Logo">
    </figure>
    <c:choose>
        <c:when test="${empty sessionScope.usuario}">
            <form action="app/doLogin" method="post">
                <fieldset>
                    <legend>Login</legend>
                    <div class="campo">
                        <label for="email">E-mail</label>
                        <input type="email" name="email" id="email" value="mgarcia@dummy.es"
                               class="${not empty credencialesInvalidas? 'borde-rojo' : ''}">
                    </div>
                    <div class="campo">
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password" value="pass1">
                    </div>
                    <input type="submit" value="Iniciar sesión">
                </fieldset>
            </form>
        </c:when>
        <c:otherwise>
            <jsp:useBean id="usuario" scope="session" type="org.uned.practicatw.model.Usuario"/>
            <h1>Bienvenido <jsp:getProperty name="usuario" property="nombre"/> </h1>
        </c:otherwise>
    </c:choose>

</section>

<%@ include file="common/footer.jsp" %>