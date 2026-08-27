<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="titulo" value="InfoFormacion - Registro"/>
<c:set var="errorMsg" value="${sessionScope.errorRegistro}" scope="page"/>
<c:remove var="errorRegistro" scope="session"/>

<%@include file="common/head.jsp" %>
<%@include file="common/header.jsp" %>



<main>
    <h1>Inicio de sesión</h1>
    <form action="usuario" method="post">
        <input type="hidden" name="action" value="crear" />
        <input type="hidden" name="tipo_usuario" value="ESTUDIANTE" />
        <fieldset>
            <legend>Datos de usuario</legend>


            <c:if test="${!empty errorMsg}">
                <h1>${errorMsg}</h1>
            </c:if>
            <div class="campo">
                <label for="email">E-mail</label>
                <input type="email" name="email" id="email" value="test@email.com" class = "${not empty errorMsg? 'borde-rojo' : ''}">
            </div>
            <div class="campo">
                <label for="password">Password</label>
                <input type="password" name="password" id="password" value="abc">
            </div>
        </fieldset>
        <fieldset>
            <legend>Datos personales</legend>
            <div class="campo">
                <label for="nombre">Nombre</label>
                <input type="text" name="nombre" id="nombre" value="Miguel">
            </div>
            <div class="campo">
                <label for="apellidos">Apellidos</label>
                <input type="text" name="apellidos" id="apellidos" value="Company Palomo">
            </div>
            <div class="campo">
                <label for="direccion">Direccion</label>
                <input type="text" name="direccion" id="direccion" value="Calle Larala">
            </div>
            <div class="campo">
                <label for="poblacion">Población</label>
                <input type="text" name="poblacion" id="poblacion" value="Madrid">
            </div>
            <div class="campo">
                <label for="codigo_postal">Código Postal</label>
                <input type="text" name="codigo_postal" id="codigo_postal" value="28001">
            </div>
            <div class="campo">
                <label for="provincia">Provincia</label>
                <input type="text" name="provincia" id="provincia" value="Madrid">
            </div>
        </fieldset>
        <input type="submit" value="Registrarse" >
        <button type="button" command="close">Enviar</button>


    </form>

</main>

<c:remove var="errorRegistro" scope="session"/>
<%@include file="common/footer.jsp" %>