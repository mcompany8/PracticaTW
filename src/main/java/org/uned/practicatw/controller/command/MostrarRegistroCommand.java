package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.service.TematicaService;

public class MostrarRegistroCommand implements Command {

    private final TematicaService tematicaService;

    public MostrarRegistroCommand(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("tematicas", tematicaService.obtenerTodos());
        return CommandResult.forward("/WEB-INF/views/registro.jsp");
    }
}