<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="titulo" value="Login"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>

<main>

    <h2>Mis cursos</h2>

    <section class="lista-cursos">

        <ul class="lista-cursos__items">
            <c:forEach var="curso" items="${requestScope.cursos}">
                <li class="lista-cursos__item">
                    <a href="app/detalleCurso?id=${curso.id}">
                        <img src="imagenes/${curso.imagen}"
                             alt="">
                        <h3>${curso.titulo}</h3>
                    </a>

                </li>
            </c:forEach>
        </ul>
    </section>

    <a href="app/nuevoCurso" class="link-icon">
        <img src="assets/icons/plus-solid-full.svg" alt="">
        <h3>Añadir nuevo curso</h3>
    </a>


</main>



<%@ include file="common/footer.jsp" %>