<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<jsp:include page="WEB-INF/views/common/header.jsp" />

<div class="card shadow-sm">
    <div class="card-header">
        <h5 class="mb-0"><i class="bi bi-list-ul"></i> Productos</h5>
    </div>
    <div class="card-body">
        <table class="table table-hover table-striped align-middle">

            <thead>
            <tr>
                <th>ID</th>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="t" items="${requestScope.listaTematicas}">
                <tr>
                    <td>${t.id}</td>
                    <td>${t.titulo}</td>
                    <td>€${t.descripcion}</td>
                    <td><a href="editar?id=${t.id}" class="btn btn-sm btn-outline-primary">Editar</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="WEB-INF/views/common/footer.jsp" />