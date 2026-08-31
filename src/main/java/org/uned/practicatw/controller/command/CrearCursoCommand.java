package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Nivel;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.ProfesorService;

public class CrearCursoCommand implements Command {

    private CursoService cursoService;
    private ProfesorService profesorService;

    public CrearCursoCommand(CursoService cursoService, ProfesorService profesorService) {
        this.cursoService = cursoService;
        this.profesorService = profesorService;
    }

    /**
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String titulo = req.getParameter("titulo");
        String descripcion = req.getParameter("descripcion");
        Integer duracion = Integer.parseInt(req.getParameter("duracion"));
        Long id =  Long.parseLong(req.getParameter("responsable_id"));
        Nivel nivel = Nivel.valueOf(req.getParameter("nivel"));
        Profesor profesor = profesorService.obtenerPorId(id).get();

        Curso curso = Curso.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .duracionHoras(duracion)
                .responsable(profesor)
                .nivel(nivel)
                .build();

        cursoService.crear(curso);

        return CommandResult.redirect("/app/inicio");
    }
}
