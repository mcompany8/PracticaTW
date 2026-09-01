package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;

import java.util.Optional;

public class EliminarMaterialCommand implements Command {

    private final ContenidoService contenidoService;
    private final CursoService cursoService;

    public EliminarMaterialCommand(ContenidoService contenidoService, CursoService cursoService) {
        this.contenidoService = contenidoService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long materialId = Long.parseLong(req.getParameter("materialId"));
        Long cursoId = Long.parseLong(req.getParameter("cursoId"));

        // El curso tiene que pertenecer al profesor logueado
        Curso curso = cursoService.obtenerCursoPorIdYProfesor(cursoId, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Optional<Contenido> materialOpt = contenidoService.obtenerPorId(materialId);
        if (materialOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Contenido material = materialOpt.get();

        // El material tiene que pertenecer a este curso — comparar solo el id
        // no inicializa el proxy lazy de Contenido.curso
        if (!material.getCurso().getId().equals(cursoId)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        contenidoService.eliminar(materialId);
        contenidoService.cerrarHueco(cursoId, material.getOrden());

        return CommandResult.redirect("/app/detalleCurso?id=" + cursoId + "&vista=materiales");
    }
}