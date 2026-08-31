<%@ page pageEncoding="UTF-8" %>
<footer class="site-footer">
    <div class="site-footer__content">
        <div class="site-footer__brand">
            <h3 class="site-footer__logo">InfoFormación</h3>
            <p class="site-footer__tagline">Aprende a tu ritmo, con quien sabe enseñar.</p>
        </div>

        <nav class="site-footer__links">
            <div class="site-footer__column">
                <h4 class="site-footer__heading">Plataforma</h4>
                <a href="${pageContext.request.contextPath}/app/cursos" class="site-footer__link">Cursos</a>
                <a href="${pageContext.request.contextPath}/app/registro" class="site-footer__link">Crear cuenta</a>
            </div>

            <div class="site-footer__column">
                <h4 class="site-footer__heading">Ayuda</h4>
                <a href="${pageContext.request.contextPath}/app/contacto" class="site-footer__link">Contacto</a>
                <a href="${pageContext.request.contextPath}/app/faq" class="site-footer__link">Preguntas frecuentes</a>
            </div>
        </nav>
    </div>

    <div class="site-footer__bottom">
        <p class="site-footer__copyright">&copy; 2026 PracticaTW &mdash; UNED. Todos los derechos reservados.</p>
    </div>
</footer>
