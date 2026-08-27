<%--
  Created by IntelliJ IDEA.
  User: mcomp
  Date: 08/08/2026
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Temáticas</h1>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Título</th>
        <th>Descripción</th>
    </tr>
    <c:forEach var="tematica" items="${requestScope.listaTematicas}">
        <tr>
            <td>${tematica.id}</td>
            <td>${tematica.titulo}</td>
            <td>${tematica.descripcion}</td>
        </tr>
    </c:forEach>


</table>
<br>
<a href="WEB-INF/views/index.jsp">Volver</a>
</body>
</html>
