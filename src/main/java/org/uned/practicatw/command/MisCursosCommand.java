package org.uned.practicatw.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.controller.command.Command;
import org.uned.practicatw.model.*;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;

import java.util.List;

public class MisCursosCommand implements Command {

    private final CursoService cursoService;
    private final InscripcionService inscripcionService;

    public MisCursosCommand(CursoService cursoService, InscripcionService inscripcionService) {
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario == null) {
            return CommandResult.redirect("/app/login");
        }

        if (usuario instanceof Estudiante estudiante) {
            return misCursosEstudiante(req, estudiante);
        }
        // Profesor y Administrador (Administrador extends Profesor)
        return misCursosProfesor(req, (Profesor) usuario);
    }

    private CommandResult misCursosEstudiante(HttpServletRequest req, Estudiante estudiante) {
        List<Inscripcion> inscripciones = inscripcionService.obtenerPorEstudiante(estudiante.getId());
        req.setAttribute("inscripciones", inscripciones);
        return CommandResult.forward("/WEB-INF/views/misCursosEstudiante.jsp");
    }

    private CommandResult misCursosProfesor(HttpServletRequest req, Profesor profesor) {
        List<Curso> cursos = cursoService.obtenerCursosPorProfesor(profesor.getId());
        req.setAttribute("cursos", cursos);
        return CommandResult.forward("/WEB-INF/views/cursosProf.jsp");
    }

}
