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

/**
 * Muestra el detalle público de un curso (ruta {@code curso}, GET), tal como
 * se ve desde el catálogo. Si el usuario en sesión es un {@link Estudiante},
 * carga también su {@link Inscripcion} en ese curso (si existe), para que la
 * vista pueda ofrecer "Inscribirme" o "Ya estás inscrito" según corresponda.
 *
 * @implNote {@code cursoService.obtenerPorId(cursoId).get()} no comprueba
 * que el {@code Optional} tenga valor — un {@code id} de curso inexistente
 * lanza {@code NoSuchElementException} sin control en vez de un 404 limpio.
 */
public class VerCursoCommand implements Command {

    private CursoService cursoService;
    private InscripcionService inscripcionService;


    public VerCursoCommand(CursoService cursoService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long cursoId = Long.parseLong(req.getParameter("id"));
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario instanceof Estudiante) {
            Long estudianteId = usuario.getId();
            Inscripcion inscripcion = inscripcionService.obtenerPorCursoAndEstudiante(cursoId, estudianteId);
            req.setAttribute("inscripcion", inscripcion);
        }
        Curso curso = cursoService.obtenerPorId(cursoId).get();
        req.setAttribute("curso", curso);
        return  CommandResult.forward("/WEB-INF/views/curso.jsp");
    }
}