<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="titulo" value="Bienvenido a Infoformation"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>

<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>

<main>
    <a href="usuarios">Lista de usuarios</a>
</main>

<%@ include file="common/footer.jsp" %>