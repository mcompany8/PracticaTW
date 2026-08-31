package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.service.ProfesorService;

public class PrepararFormCursoCommand implements Command{

    private ProfesorService profesorService;

    public PrepararFormCursoCommand(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    /**
     * @param req
     * @param resp
     * @return
     * @throws Exception
     */
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("profesores", profesorService.obtenerProfesores());
        return CommandResult.forward("/WEB-INF/views/nuevoCurso.jsp");
    }
}
