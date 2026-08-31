<%@ tag description="Modal de confirmación CSS-only (sin JavaScript)" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="id" required="true" description="Id único del modal, usado como ancla #id" %>
<%@ attribute name="titulo" required="false" %>
<%@ attribute name="mensaje" required="true" %>
<%@ attribute name="textoConfirmar" required="false" %>
<%@ attribute name="urlConfirmar" required="false" description="Para acciones GET: URL del botón Confirmar" %>
<%@ attribute name="accionFormulario" required="false" description="Para acciones POST: action del formulario" %>
<%@ attribute name="camposOcultos" fragment="true" required="false" description="Inputs hidden adicionales del formulario" %>

<div class="modal-confirmacion" id="${id}">
    <a href="#" class="modal-confirmacion__overlay" aria-label="Cerrar"></a>
    <div class="modal-confirmacion__caja" role="dialog" aria-modal="true">
        <h2 class="modal-confirmacion__titulo">${empty titulo ? 'Confirmar acción' : titulo}</h2>
        <p class="modal-confirmacion__texto">${mensaje}</p>
        <div class="modal-confirmacion__acciones">
            <a href="#" class="boton boton--secundario">Cancelar</a>
            <c:choose>
                <c:when test="${not empty accionFormulario}">
                    <form method="post" action="${accionFormulario}" class="modal-confirmacion__form">
                        <c:if test="${not empty camposOcultos}">
                            <jsp:invoke fragment="camposOcultos"/>
                        </c:if>
                        <button type="submit" class="boton boton--primario">
                                ${empty textoConfirmar ? 'Confirmar' : textoConfirmar}
                        </button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="${urlConfirmar}" class="boton boton--primario">
                            ${empty textoConfirmar ? 'Confirmar' : textoConfirmar}
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>