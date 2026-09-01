package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.TematicaService;

public class MostrarCrearCursoCommand implements Command {

    private final TematicaService tematicaService;

    public MostrarCrearCursoCommand(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        req.setAttribute("tematicas", tematicaService.obtenerTodos());
        return CommandResult.forward("/WEB-INF/views/crearCurso.jsp");
    }
}