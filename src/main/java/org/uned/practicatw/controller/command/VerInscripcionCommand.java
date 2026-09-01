package org.uned.practicatw.controller.command;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;

import java.util.List;

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
        Curso curso = cursoService.obtenerPorId(cursoId).get();
        Estudiante estudiante = (Estudiante) req.getSession().getAttribute("usuario");
        Inscripcion inscripcion = inscripcionService.obtenerPorCursoAndEstudiante(cursoId, estudiante.getId());
        List<Contenido> contenidos = contenidoService.obtenerPorCurso(cursoId);
        req.setAttribute("inscripcion", inscripcion);
        req.setAttribute("curso", curso);
        req.setAttribute("contenidos", contenidos);
        return CommandResult.forward(("/WEB-INF/views/miInscripcion.jsp"));

    }
}
