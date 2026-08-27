package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.service.InscripcionService;

public class ListarEstudiantesPorCursoCommand implements Command {

    private InscripcionService inscripcionService;

    public ListarEstudiantesPorCursoCommand(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long cursoId = Long.parseLong(req.getParameter("cursoId"));
        req.setAttribute("estudiantes", inscripcionService.obtenerEstudiantesPorCurso(cursoId));
        return CommandResult.forward("/WEB-INF/views/estudiantes_matriculados.jsp");


    }
}
