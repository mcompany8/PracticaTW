package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.InscripcionService;

import java.util.Optional;

public class DesinscripcionCommand implements Command {

    private final InscripcionService inscripcionService;

    public DesinscripcionCommand(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Estudiante)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long inscripcionId = Long.parseLong(req.getParameter("inscripcionId"));
        Optional<Inscripcion> inscripcionOpt = inscripcionService.obtenerPorId(inscripcionId);

        if (inscripcionOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Inscripcion inscripcion = inscripcionOpt.get();

        // IDOR: la inscripción tiene que pertenecer al alumno logueado, no basta con que exista
        if (!inscripcion.getEstudiante().getId().equals(usuario.getId())) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        inscripcionService.eliminar(inscripcionId);

        return CommandResult.redirect("/app/misCursos");
    }
}