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

public class ActualizarOrdenMaterialCommand implements Command {

    private final ContenidoService contenidoService;
    private final CursoService cursoService;

    public ActualizarOrdenMaterialCommand(ContenidoService contenidoService, CursoService cursoService) {
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
        Integer ordenNuevo = Integer.parseInt(req.getParameter("orden"));

        String vista = "&vista=materiales";

        if (ordenNuevo < 1) {
            req.getSession().setAttribute("errorMaterial", "El orden tiene que ser 1 o mayor.");
            return CommandResult.redirect("/app/detalleCurso?id=" + cursoId + vista);
        }

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

        if (!material.getCurso().getId().equals(cursoId)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        contenidoService.actualizarOrden(cursoId, materialId, material.getOrden(), ordenNuevo);

        return CommandResult.redirect("/app/detalleCurso?id=" + cursoId + vista);
    }
}