<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>
<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>
<main>

    <h2> Asignar contenido </h2>

    <form action="" method="post">

        <label for="contenidos">Selecciona los contenidos:</label>
        <select id="contenidos" name="idContenidos" muliple required>
            <c:forEach items="${requestScope.contenidos}" var="c">
                <option value="${c.id}">${c.titulo}</option>
            </c:forEach>
        </select>

        <input type="submit" value="Asignar">
    </form>

</main>


<%@ include file="common/footer.jsp" %>