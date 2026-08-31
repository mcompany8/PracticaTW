package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.CursoService;

public class VerCursoCommand implements Command {

    private CursoService cursoService;

    public VerCursoCommand(CursoService cursoService) {
        this.cursoService = cursoService;
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
        Curso curso = cursoService.obtenerPorId(cursoId).get();
        req.setAttribute("curso", curso);
        return  CommandResult.forward("/WEB-INF/views/curso.jsp");
    }
}
