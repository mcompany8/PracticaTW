<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="titulo" value="Lista de usuarios"></c:set>
<%@include file="common/head.jsp"%>
<%@include file="common/header.jsp"%>
<section>
    <table>
        <thead>
        <tr>
            <th>Email</th>
            <th>Nombre</th>
            <th>Apellidoss</th>
        </tr>

        <c:forEach var="u" items="${requestScope.usuarios}">
            <tr>
                <td>${u.email}</td>
                <td>${u.nombre}</td>
                <td>${u.apellidos}</td>
                <td>${u.tipoUsuario}</td>
            </tr>
        </c:forEach>
        </thead>
    </table>
    <a href="inicio">Volver</a>

</section>


</body>
</html>
