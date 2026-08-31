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

    <form class="formulario" method="POST" action="app/crearCurso">
        <input type="hidden" name="responsable_id" value="${sessionScope.usuario.id}">

        <div class="formulario__item">
            <label for="titulo">Título</label>
            <input type="text" name="titulo" id="titulo">
        </div>

        <div class="formulario__item">
            <label for="descripcion">Descripción</label>
            <input type="text" name="descripcion" id="descripcion">
        </div>

        <div class="formulario__item">
            <label for="nivel">Nivel</label>
            <select name="nivel" id="nivel">
                <option value="BASICO">Básico</option>
                <option value="INTERMEDIO">Intermedio</option>
                <option value="AVANZADO">Avanzado</option>
            </select>
        </div>

        <div class="formulario__item">
            <label for="duracion">Duración</label>
            <input type="number" name="duracion" id="duracion">
        </div>

        <div class="formulario__item">
            <label for="imagen">Imagen del curso</label>
            <input type="file" name="imagen" id="imagen" accept="image/*">
        </div>

        <input type="submit" value="Crear curso">
    </form>

</main>


<%@ include file="common/footer.jsp" %>