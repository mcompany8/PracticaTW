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

public class VerCursoCommand implements Command {

    private CursoService cursoService;
    private InscripcionService inscripcionService;


    public VerCursoCommand(CursoService cursoService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    /**
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
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
