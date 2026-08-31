<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="titulo" value="Bienvenido a Infoformation"/>

<%@ include file="common/head.jsp" %>
<%@ include file="common/header.jsp" %>

<c:if test="${!empty sessionScope.usuario}">
    <jsp:include page="/WEB-INF/views/common/menu.jsp"/>
</c:if>

<main>

    <section class="hero">
        <div class="hero__content">
            <h1 class="hero__title">Aprende a tu ritmo con <span class="hero__title-accent">InfoFormación</span></h1>
            <p class="hero__subtitle">
                Cursos online creados por profesores expertos. Inscríbete, sigue tu progreso
                y consigue tus objetivos de aprendizaje.
            </p>
            <div class="hero__actions">
                <a href="${pageContext.request.contextPath}/app/cursos" class="hero__cta hero__cta--primary">
                    Explorar cursos
                </a>
                <a href="${pageContext.request.contextPath}/app/registro" class="hero__cta hero__cta--secondary">
                    Crear cuenta
                </a>
            </div>
        </div>
    </section>

    <section class="cursos-destacados">
        <header class="cursos-destacados__header">
            <h2 class="cursos-destacados__title">Cursos destacados</h2>
            <a href ="#" class="cursos-destacados__todos">Ver todos los cursos →</a>
        </header>
        <div class="cursos-destacados__grid">
            <c:forEach var="curso" items="${requestScope.cursosDestacados}">
                <article class="curso-card">
                    <div class="curso-card__image-wrapper">
                        <img
                                src="imagenes/${curso.imagen}"
                                alt ="${curso.titulo}"
                                class="cursos-card__image"
                        >
                        <span class="curso-card__level curso-card__level--${curso.nivel}">
                            <c:choose>
                                <c:when test="${curso.nivel == 'BASICO'}">Básico</c:when>
                                <c:when test="${curso.nivel == 'INTERMEDIO'}">Intermedio</c:when>
                                <c:when test="${curso.nivel == 'AVANZADO'}">Avanzado</c:when>
                            </c:choose>
                        </span>
                    </div>
                    <div class="curso-card__body">
                        <h3 class="curso-card__title">${curso.titulo}</h3>
                        <p class="curso-card__description">${curso.descripcion}</p>
                    </div>
                    <div class="curso-card__footer">
                        <a href="app/curso/${curso.id}}" class="curso-card__link">
                            Ver curso
                        </a>
                    </div>
                </article>
            </c:forEach>
        </div>



    </section>


</main>

<%@ include file="common/footer.jsp" %>