package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.service.CursoService;

import java.util.List;

public class ListarCursosProfesorCommand implements Command {

    private final CursoService cursoService;

    public ListarCursosProfesorCommand(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Profesor profesor = (Profesor) request.getSession().getAttribute("usuario");
        List<Curso> cursos = cursoService.obtenerCursosPorProfesor(profesor.getId());
        request.setAttribute("cursos", cursos);

        return CommandResult.forward("/WEB-INF/views/cursosProf.jsp");
    }
}
