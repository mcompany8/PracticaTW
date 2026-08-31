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

public class InscripcionCommand implements Command {

    private InscripcionService inscripcionService;
    private CursoService cursoService;

    public InscripcionCommand(InscripcionService inscripcionService, CursoService cursoService) {
        this.inscripcionService = inscripcionService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long cursoId = Long.parseLong(req.getParameter("cursoId"));
        Curso curso = cursoService.obtenerPorId(cursoId).get();
        Estudiante estudiante = (Estudiante) req.getSession().getAttribute("usuario");
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
