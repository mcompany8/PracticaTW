package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.TematicaService;

import java.util.List;

public class CatalogoCommand implements Command {

    private final CursoService cursoService;
    private final TematicaService tematicaService;

    public CatalogoCommand(CursoService cursoService, TematicaService tematicaService) {

        this.cursoService = cursoService;
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String tematicaParam = req.getParameter("tematica");
        List<Curso> cursos;
        Long tematicaSeleccionada = null;

        if (tematicaParam != null && !tematicaParam.isBlank()) {
            tematicaSeleccionada = Long.parseLong(tematicaParam);
            cursos = cursoService.obtenerCursosPorTematica(tematicaSeleccionada);
        } else {
            cursos = cursoService.obtenerTodosConTematicas();
        }

        req.setAttribute("cursos", cursos);
        req.setAttribute("tematicas", tematicaService.obtenerTodos());
        req.setAttribute("tematicaSeleccionada", tematicaSeleccionada);

        return CommandResult.forward("/WEB-INF/views/catalogo.jsp");
    }
}
