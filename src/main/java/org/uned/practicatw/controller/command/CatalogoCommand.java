package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.CursoService;

import java.util.List;

public class CatalogoCommand implements Command {

    private CursoService cursoService;

    public CatalogoCommand(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        List<Curso> cursos = cursoService.obtenerTodos();
        req.setAttribute("cursos", cursos);
        return CommandResult.forward("/WEB-INF/views/catalogo.jsp");
    }
}
