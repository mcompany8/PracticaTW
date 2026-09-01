package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;

import java.util.List;
import java.util.Optional;

/**
 * Muestra el detalle de la inscripción de un estudiante en un curso (ruta
 * {@code miInscripcion}, GET): mismo banner que el catálogo, materiales del
 * curso y la valoración del alumno si ya la ha hecho.
 * <p>
 * Comprueba, en este orden, que el usuario en sesión es un {@link Estudiante},
 * que el curso existe, y que existe una {@link Inscripcion} de ese estudiante
 * en ese curso — solo entonces carga y muestra los materiales. Sin esta
 * última comprobación, cualquier estudiante logueado podría ver el contenido
 * de un curso en el que no está matriculado navegando directamente a esta
 * ruta con su {@code id}.
 */
public class VerInscripcionCommand implements Command {
    private final CursoService cursoService;
    private final InscripcionService inscripcionService;
    private final ContenidoService contenidoService;

    public VerInscripcionCommand(CursoService cursoService, InscripcionService inscripcionService, ContenidoService contenidoService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
        this.contenidoService = contenidoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long cursoId = Long.parseLong(req.getParameter("id"));

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Estudiante estudiante)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Optional<Curso> cursoOpt = cursoService.obtenerPorId(cursoId);
        if (cursoOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        // El estudiante tiene que estar matriculado en este curso para ver sus materiales
        Inscripcion inscripcion = inscripcionService.obtenerPorCursoAndEstudiante(cursoId, estudiante.getId());
        if (inscripcion == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        List<Contenido> contenidos = contenidoService.obtenerPorCurso(cursoId);
        req.setAttribute("inscripcion", inscripcion);
        req.setAttribute("curso", cursoOpt.get());
        req.setAttribute("contenidos", contenidos);
        return CommandResult.forward("/WEB-INF/views/miInscripcion.jsp");
    }
}