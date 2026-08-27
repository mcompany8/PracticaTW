<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="title" value="Login"/>

<%@ include file="common/head.jsp"%>
<%@ include file="common/header.jsp"%>

<main>
    <section>
        <a href="perfil">
            <i class="fa-solid fa-pencil"></i>
            Editar datos personales
        </a>

        <input type="checkbox" id="toggleEdicion" style="display:none;">
        <label for="toggleEdicion" style="cursor:pointer; color:blue; text-decoration:underline;">
            Editar datos personales
        </label>

        <div class="panel-edicion">
            <form action="EditarDatosServlet" method="post">
                <label>Nombre:</label>
                <input type="text" name="nombre" value="${usuario.nombre}"><br>

                <label>Email:</label>
                <input type="email" name="email" value="${usuario.email}"><br>

                <label>Teléfono:</label>
                <input type="text" name="telefono" value="${usuario.apellidos}"><br>

                <button type="submit">Guardar</button>
            </form>
        </div>

        <style>
            .panel-edicion {
                display: none;
                margin-top: 10px;
                border: 1px solid #ccc;
                padding: 15px;
            }
            #toggleEdicion:checked ~ .panel-edicion {
                display: block;
            }
        </style>

    </section>

</main>

<%@ include file="common/footer.jsp"%>