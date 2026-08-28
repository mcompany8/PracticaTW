<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>
<main>

    <h2>Nuevo Curso</h2>

    <form method="POST" action="/login">
        <input type="hidden" name="responsable" value="${sessionScope.usuario}">

        <label for="titulo">Título</label>
        <input type="text" name="titulo" id="titulo">

        <label for="descripcion">Título</label>
        <input type="text" name="descripcion" id="descripcion">

        <label for="nivel">Nivel</label>
        <select name="nivel" id="nivel">
            <option value="BASICO">Básico</option>
            <option value="INTERMEDIO">Intermedio</option>
            <option value="AVANZADO">Avanzado</option>
        </select>

        <label for="duracion">Duración</label>
        <input type="number" name="duracion" id="duracion">

        

    </form>

</main>


<%@ include file="common/footer.jsp" %>