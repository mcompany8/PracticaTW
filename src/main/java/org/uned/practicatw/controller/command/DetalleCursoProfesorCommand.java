package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;

import java.util.List;

public class DetalleCursoProfesorCommand implements Command {

    private CursoService cursoService;
    private InscripcionService inscripcionService;

    public DetalleCursoProfesorCommand(CursoService cursoService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long idCurso = Long.parseLong(req.getParameter("id"));
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario == null) {
            return CommandResult.redirect(req.getContextPath() + "/login");
        }
        Curso curso = cursoService.obtenerCursoPorIdYProfesor(idCurso, usuario.getId());
        if (curso == null) {
            return CommandResult.forward(req.getContextPath() + "/login");

        }
        req.setAttribute("curso", curso);

        String doParam = req.getParameter("do");
        if (doParam != null) {
            switch (doParam) {
                case "verMatriculados" -> {
                    List<Estudiante> estudiantes = inscripcionService.obtenerEstudiantesPorCurso(idCurso);
                    req.setAttribute("estudiantes", estudiantes);
                }
                default -> {}
            }
        }




        return CommandResult.forward("/WEB-INF/views/detalleCurso.jsp");
    }
}
