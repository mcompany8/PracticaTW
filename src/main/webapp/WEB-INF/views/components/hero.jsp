<%--<%@page pageEncoding="UTF-8"%>--%>

<%--<section class="hero">--%>
<%--  <div class="hero__content">--%>
<%--    <h1 class="hero__title">Aprende a tu ritmo con <span class="hero__title-accent">InfoFormación</span></h1>--%>
<%--    <p class="hero__subtitle">--%>
<%--      Cursos online creados por profesores expertos. Inscríbete, sigue tu progreso--%>
<%--      y consigue tus objetivos de aprendizaje.--%>
<%--    </p>--%>
<%--    <div class="hero__actions">--%>
<%--      <a href="app/login" class="hero__cta hero__cta--primary">--%>
<%--        Iniciar sesión--%>
<%--      </a>--%>
<%--      <a href="app/registro" class="hero__cta hero__cta--secondary">--%>
<%--        Crear cuenta--%>
<%--      </a>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</section>--%>

<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<section class="hero">
  <div class="hero__content">
    <c:choose>
      <c:when test="${empty sessionScope.usuario}">
        <h1 class="hero__title">Aprende a tu ritmo con <span class="hero__title-accent">InfoFormación</span></h1>
        <p class="hero__subtitle">
          Cursos online creados por profesores expertos. Inscríbete, sigue tu progreso
          y consigue tus objetivos de aprendizaje.
        </p>
        <div class="hero__actions">
          <a href="app/login" class="hero__cta hero__cta--primary">
            Iniciar sesión
          </a>
          <a href="app/registro" class="hero__cta hero__cta--secondary">
            Crear cuenta
          </a>
        </div>
      </c:when>
      <c:otherwise>
        <h1 class="hero__title">Bienvenido de nuevo, <span class="hero__title-accent">${sessionScope.usuario.nombre}</span></h1>
        <p class="hero__subtitle">
          Retoma tus cursos donde los dejaste o descubre algo nuevo en el catálogo.
        </p>
        <div class="hero__actions">
          <a href="app/misCursos" class="hero__cta hero__cta--primary">
            Ir a mis cursos
          </a>
          <a href="app/catalogo" class="hero__cta hero__cta--secondary">
            Ver catálogo
          </a>
        </div>
      </c:otherwise>
    </c:choose>
  </div>
</section>