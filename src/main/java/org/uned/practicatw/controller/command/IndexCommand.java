package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.CursoService;

import java.util.List;

public class IndexCommand implements Command {

    private CursoService cursoService;

    public IndexCommand(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        List<Curso> cursosDestacados = cursoService.obtenerCursosRandom(6);
        req.setAttribute("cursosDestacados", cursosDestacados);
        return CommandResult.forward("/WEB-INF/views/index.jsp");
    }
}
