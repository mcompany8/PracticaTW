package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.*;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;
import org.uned.practicatw.service.TematicaService;
import org.uned.practicatw.service.ValoracionService;

import java.util.List;

/**
 * Panel de gestión de un curso desde el punto de vista del profesor (ruta
 * {@code detalleCurso}), organizado en pestañas mediante el parámetro
 * {@code vista} ({@code info} por defecto, {@code materiales},
 * {@code matriculados}, {@code estadisticas}): carga solo los datos que
 * necesita la pestaña activa, no todos a la vez.
 * <p>
 * Comprueba que el curso pertenece al profesor logueado antes de mostrar
 * nada (protección IDOR) — el resto de {@code Command} que actúan sobre
 * materiales/matriculados de este mismo curso replican esa misma comprobación.
 */
public class DetalleCursoProfesorCommand implements Command {

    private final CursoService cursoService;
    private final InscripcionService inscripcionService;
    private final ContenidoService contenidoService;
    private final TematicaService tematicaService;
    private final ValoracionService valoracionService;

    public DetalleCursoProfesorCommand(CursoService cursoService,
                                       InscripcionService inscripcionService,
                                       ContenidoService contenidoService,
                                       TematicaService tematicaService,
                                       ValoracionService valoracionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
        this.contenidoService = contenidoService;
        this.tematicaService = tematicaService;
        this.valoracionService = valoracionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario == null) {
            return CommandResult.redirect("/app/login");
        }

        Long idCurso = Long.parseLong(req.getParameter("id"));

        // IDOR: el curso tiene que pertenecer al profesor logueado
        Curso curso = cursoService.obtenerCursoPorIdYProfesor(idCurso, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }
        req.setAttribute("curso", curso);

        String pestana = req.getParameter("vista");
        if (pestana == null) {
            pestana = "info";
        }

        switch (pestana) {

            case "materiales" -> {
                List<Contenido> materiales = contenidoService.obtenerPorCurso(idCurso);
                req.setAttribute("materiales", materiales);
            }

            case "matriculados" -> {
                List<Inscripcion> inscripciones = inscripcionService.obtenerPorCurso(idCurso);
                req.setAttribute("inscripciones", inscripciones);
            }

            case "estadisticas" -> {
                List<Inscripcion> inscripciones = inscripcionService.obtenerPorCurso(idCurso);
                List<Valoracion> valoraciones = valoracionService.obtenerPorCurso(idCurso);

                double media = valoraciones.stream()
                        .mapToInt(Valoracion::getValoracion)
                        .average()
                        .orElse(0);

                req.setAttribute("totalInscritos", inscripciones.size());
                req.setAttribute("valoraciones", valoraciones);
                req.setAttribute("valoracionMedia", valoraciones.isEmpty() ? null : media);
            }

            // "info" (por defecto): solo hace falta el catálogo de temáticas
            // para los checkboxes del formulario de edición
            default -> {
                req.setAttribute("tematicas", tematicaService.obtenerTodos());

                String tematicasSeleccionadasCsv = curso.getTematicas().stream()
                        .map(t -> String.valueOf(t.getId()))
                        .collect(java.util.stream.Collectors.joining(",", ",", ","));
                req.setAttribute("tematicasSeleccionadasCsv", tematicasSeleccionadasCsv);
            }
        }

        return CommandResult.forward("/WEB-INF/views/detalleCurso.jsp");
    }
}