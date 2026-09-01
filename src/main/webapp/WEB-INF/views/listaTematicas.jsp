<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Temáticas"/>
<c:set var="extraCss" value="${['paginas/detalleCurso.css']}"/>

<%@ include file="layout/head.jspf" %>
<%@ include file="layout/header.jsp" %>

<c:set var="errorTematica" value="${sessionScope.errorTematica}" scope="page"/>
<c:remove var="errorTematica" scope="session"/>

<body>
<main>
    <div class="contenedor detalle-curso">
        <div class="detalle-curso__cabecera">
            <h1 class="detalle-curso__titulo">Temáticas</h1>
            <a href="app/crearTematica" class="boton boton--primario">+ Crear temática</a>
        </div>

        <c:if test="${not empty errorTematica}">
            <p class="formulario__aviso formulario__aviso--error" role="alert">${errorTematica}</p>
        </c:if>

        <c:choose>
            <c:when test="${empty tematicas}">
                <p class="inscripcion-detalle__vacio">Todavía no hay temáticas creadas.</p>
            </c:when>
            <c:otherwise>
                <table class="detalle-curso__tabla">
                    <thead>
                    <tr>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="tematica" items="${tematicas}">
                        <tr>
                            <td>${tematica.titulo}</td>
                            <td>${tematica.descripcion}</td>
                            <td class="lista-usuarios__acciones">
                                <a href="app/editarTematica?id=${tematica.id}" class="boton boton--secundario">
                                    Editar
                                </a>
                                <a href="${pageContext.request.contextPath}/app/listarTematicas#eliminar-tematica-${tematica.id}"
                                   class="boton boton--peligro">
                                    Eliminar
                                </a>
                                <ui:confirmacion id="eliminar-tematica-${tematica.id}"
                                                 urlActual="${urlActual}"
                                                 titulo="Eliminar temática"
                                                 mensaje="¿Seguro que quieres eliminar «${tematica.titulo}»?"
                                                 accionFormulario="app/eliminarTematica"
                                                 textoConfirmar="Sí, eliminar">
                                    <jsp:attribute name="camposOcultos">
                                        <input type="hidden" name="tematicaId" value="${tematica.id}">
                                    </jsp:attribute>
                                </ui:confirmacion>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<%@ include file="layout/footer.jspf" %>
</body>
</html>