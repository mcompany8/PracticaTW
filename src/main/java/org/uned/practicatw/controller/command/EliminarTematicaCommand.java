package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.TematicaService;

import java.util.List;

/**
 * Elimina una temática (ruta {@code eliminarTematica}, POST). Bloquea la
 * operación con un aviso si algún curso la tiene todavía asignada — ver la
 * nota de {@link org.uned.practicatw.model.Tematica} sobre por qué (la tabla
 * intermedia {@code cursos_tematicas} no tiene {@code @OnDelete} en su
 * columna {@code tematica_id}, así que el borrado reventaría por FK si no se
 * comprobara antes). Solo accesible por un {@link Administrador}.
 */
public class EliminarTematicaCommand implements Command {

    private final TematicaService tematicaService;
    private final CursoService cursoService;

    public EliminarTematicaCommand(TematicaService tematicaService, CursoService cursoService) {
        this.tematicaService = tematicaService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long tematicaId = Long.parseLong(req.getParameter("tematicaId"));

        // Curso.tematicas no tiene @OnDelete en el lado "tematica_id" (solo en
        // "curso_id", para el borrado de curso). Borrar una temática todavía
        // asignada a algún curso reventaría por esa FK, así que se bloquea
        // con un aviso en vez de dejar que pete.
        List<Curso> cursosConEstaTematica = cursoService.obtenerCursosPorTematica(tematicaId);
        if (!cursosConEstaTematica.isEmpty()) {
            req.getSession().setAttribute("errorTematica",
                    "No se puede eliminar: " + cursosConEstaTematica.size()
                            + " curso(s) todavía la tienen asignada.");
            return CommandResult.redirect("/app/listarTematicas");
        }

        tematicaService.eliminar(tematicaId);

        return CommandResult.redirect("/app/listarTematicas");
    }
}