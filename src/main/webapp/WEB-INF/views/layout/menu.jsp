<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>


<c:if test="${not empty sessionScope.usuario}">
    <nav class="menu">
        <div class="menu__contenido">

            <c:choose>
                <c:when test="${sessionScope.usuario.tipoUsuario == 'Estudiante'}">
                    <a href="app/misCursos"
                       class="menu__enlace <c:if test='${accion == "misCursos"}'>menu__enlace--activo</c:if>">
                        Mis cursos
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="app/cursosProf"
                       class="menu__enlace <c:if test='${accion == "misCursos"}'>menu__enlace--activo</c:if>">
                        Mis cursos
                    </a>
                </c:otherwise>
            </c:choose>

            <c:if test="${sessionScope.usuario.tipoUsuario == 'Estudiante'}">
                <a href="app/tarea?accion=pendientes"
                   class="menu__enlace">Tareas pendientes</a>
            </c:if>

            <c:if test="${sessionScope.usuario.tipoUsuario == 'Profesor' || sessionScope.usuarioLogueado.rol == 'Administrador'}">
                <a href="app/curso?accion=crear"
                   class="menu__enlace">Crear curso</a>
            </c:if>

            <c:if test="${sessionScope.usuarioLogueado.rol == 'Administrador'}">
                <span class="menu__separador"></span>
                <div class="menu__grupo menu__grupo--derecha">
                    <a href="app/usuario?accion=listar"
                       class="menu__enlace">Usuarios</a>
                    <a href="app/admin"
                       class="menu__enlace">Administración</a>
                </div>
            </c:if>

        </div>
    </nav>
</c:if>