<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>


<nav class="menu">
    <ul class="menu__list">

        <c:choose>
            <c:when test="${sessionScope.usuario.tipoUsuario eq 'Estudiante'}">
                <li class="menu__item">
                    <a href="app/cursosProf" class="menu__link">Mis cursos</a>
                </li>
            </c:when>
            <c:when test="${sessionScope.usuario.tipoUsuario eq 'Profesor'
                        || sessionScope.usuario.tipoUsuario eq 'Administrador'}">
                <li class="menu__item">
                    <a href="app/cursosProf" class="menu__link">Cursos</a>
                </li>
                <li class="menu__item">
                    <a href="app/contenido" class="menu__link">Contenidos</a>
                </li>
                <li class="menu__item">
                    <a href="#" class="menu__link">Estadísticas</a>
                </li>
                <li class="menu__item">
                    <a href="app/logout" class="menu__link">Cerrar sesión</a>
                </li>
            </c:when>
        </c:choose>


        <c:if test="${sessionScope.usuario.tipoUsuario eq 'Administrador'}">
            <li class="menu__item">
                <a href="#" class="menu__link">Gestión de usuarios</a>
            </li>
        </c:if>
    </ul>
</nav>
