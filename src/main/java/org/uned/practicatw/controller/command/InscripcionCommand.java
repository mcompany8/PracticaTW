// InscripcionCommand.java
package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Matricula al estudiante en sesión en un curso (ruta {@code inscripcion}, POST).
 * <p>
 * Comprueba que el usuario en sesión es un {@link Estudiante}, que el curso
 * existe, y que el alumno no está ya matriculado en él antes de intentar
 * crear la fila — evita depender de que la unique constraint
 * {@code (estudiante_id, curso_id)} de {@link Inscripcion} sea quien detecte
 * la duplicidad, dejando pasar en su lugar una excepción de persistencia sin
 * controlar hasta el usuario.
 */
public class InscripcionCommand implements Command {

    private InscripcionService inscripcionService;
    private CursoService cursoService;

    public InscripcionCommand(InscripcionService inscripcionService, CursoService cursoService) {
        this.inscripcionService = inscripcionService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Estudiante estudiante)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long cursoId = Long.parseLong(req.getParameter("cursoId"));
        Optional<Curso> cursoOpt = cursoService.obtenerPorId(cursoId);
        if (cursoOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }
        Curso curso = cursoOpt.get();

        // Evita duplicar la inscripción si el alumno ya está matriculado
        // (en vez de dejar que lo detecte la unique constraint de Inscripcion)
        Inscripcion existente = inscripcionService.obtenerPorCursoAndEstudiante(cursoId, estudiante.getId());
        if (existente != null) {
            req.setAttribute("curso", curso);
            return CommandResult.forward("/WEB-INF/views/matriculaExito.jsp");
        }

        Inscripcion inscripcion = Inscripcion.builder()
                .curso(curso)
                .estudiante(estudiante)
                .fechaInscripcion(LocalDate.now())
                .build();
        inscripcionService.crear(inscripcion);

        req.setAttribute("curso", curso);
        return CommandResult.forward("/WEB-INF/views/matriculaExito.jsp");
    }
}