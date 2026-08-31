<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="cursos-grid">
    <c:forEach items="${cursos}" var="curso">
        <article class="curso-tarjeta">
            <div class="curso-tarjeta__imagen-contenedor">
                <img src="imagenes/cursos/${curso.imagen}"
                     alt="${curso.titulo}" class="curso-tarjeta__imagen">
                <span class="curso-card__level curso-card__level--${curso.nivel}">
                    <c:choose>
                        <c:when test="${curso.nivel == 'BASICO'}">Básico</c:when>
                        <c:when test="${curso.nivel == 'INTERMEDIO'}">Intermedio</c:when>
                        <c:when test="${curso.nivel == 'AVANZADO'}">Avanzado</c:when>
                    </c:choose>
                </span>
            </div>
            <div class="curso-tarjeta__cuerpo">
                <h3 class="curso-tarjeta__titulo">${curso.titulo}</h3>
                <p class="curso-tarjeta__descripcion">${curso.descripcion}</p>
                <ul class="curso-tarjeta__tematicas">
                    <c:forEach items="${curso.tematicas}" var="tematica">
                        <li class="curso-tarjeta__tematica">
                            <img src="imagenes/tematicas/${tematica.imagen}"
                                 alt="" class="curso-tarjeta__tematica-icono">
                            <span class="curso-tarjeta__tematica-texto">${tematica.titulo}</span>
                        </li>
                    </c:forEach>
                </ul>



                <a href="app/curso?id=${curso.id}"
                   class="boton boton--primario boton--bloque curso-tarjeta__boton">
                    Ver curso
                </a>
            </div>
        </article>
    </c:forEach>
</div>