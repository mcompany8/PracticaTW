<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>





<section class="curso-detalle__banner">
    <c:choose>
        <c:when test="${not empty curso.imagen}">
            <img class="curso-detalle__banner-img"
                 src="imagenes/${curso.imagen}"
                 alt="Banner del curso ${curso.titulo}">
        </c:when>
        <c:otherwise>
            <img class="curso-detalle__banner-img curso-detalle__banner-img--placeholder"
                 src="${pageContext.request.contextPath}/img/banner-por-defecto.jpg"
                 alt="Banner del curso ${curso.titulo}">
        </c:otherwise>
    </c:choose>

    <div class="curso-detalle__banner-overlay">
        <h1 class="curso-detalle__titulo">${curso.titulo}</h1>
<%--        <p class="curso-detalle__meta">--%>
<%--            <c:if test="${not empty curso.profesor}">--%>
<%--                Impartido por ${curso.profesor.nombre} ${curso.profesor.apellidos}--%>
<%--            </c:if>--%>
<%--            <c:if test="${not empty curso.fechaInicio}">--%>
<%--                &nbsp;&middot;&nbsp;--%>
<%--                Inicio: <fmt:formatDate value="${curso.fechaInicio}" pattern="dd/MM/yyyy"/>--%>
<%--            </c:if>--%>
<%--        </p>--%>
    </div>
</section>

