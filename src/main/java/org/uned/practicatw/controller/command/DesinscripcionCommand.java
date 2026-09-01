package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.InscripcionService;

import java.util.Optional;

/**
 * Da de baja a un estudiante de un curso (ruta {@code desinscripcion}, POST),
 * eliminando su {@link Inscripcion}. Gracias al {@code @OnDelete(CASCADE)}
 * declarado en {@link Inscripcion}, la eliminación arrastra en cascada, a
 * nivel de base de datos, tanto la valoración del curso (si la había) como
 * las entregas de tareas asociadas — no hace falta borrarlas aquí a mano.
 * <p>
 * Comprueba que la inscripción pertenece al estudiante logueado antes de
 * borrarla (protección IDOR).
 */
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