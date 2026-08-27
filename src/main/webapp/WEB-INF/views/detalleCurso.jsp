<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>
<main>

    <header class="banner-curso">
        <img src="assets/img/cursos/${requestScope.curso.imagen}" alt="">
        <h2>${requestScope.curso.titulo}</h2>
    </header>

    <ul class="lista-acciones">
        <li class="lista-acciones_accion">
            <a href="app/detalleCurso?id=${requestScope.curso.id}&do=verMatriculados" class="link">
                <svg class="link__icon">
                    <use href="assets/icons/icons.svg#graduate"></use>
                </svg>
                <p>Alumnos matriculados</p>
            </a>
        </li>
        <li>
            <a href="#" class="link">
                <svg class="link__icon">
                    <use href="assets/icons/icons.svg#pencil"></use>
                </svg>
                <p>Modificar datos del curso</p>
            </a>
        </li>
        <li>
            <a href="#" class="link">
                <svg class="link__icon">
                    <use href="assets/icons/icons.svg#folder-plus"></use>
                </svg>
                <p>Modificar contenido del curso</p>
            </a>
        </li>
    </ul>

    <c:if test="${!empty requestScope.estudiantes}">
        <table>
            <thead>
            <tr>
                <td>Nombre</td>
                <td>Apellidos</td>
            </tr>
            </thead>

            <c:forEach var="item" items="${requestScope.estudiantes}">
                <tr>
                    <td>${item.nombre}</td>
                    <td>${item.apellidos}</td>
                </tr>


            </c:forEach>
        </table>
    </c:if>


</main>


<%@ include file="common/footer.jsp" %>