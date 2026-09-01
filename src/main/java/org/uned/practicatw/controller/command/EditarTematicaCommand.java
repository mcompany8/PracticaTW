package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Tematica;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.TematicaService;

import java.util.Optional;

public class EditarTematicaCommand implements Command {

    private final TematicaService tematicaService;

    public EditarTematicaCommand(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Tematica> tematicaOpt = tematicaService.obtenerPorId(id);
        if (tematicaOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        req.setAttribute("tematica", tematicaOpt.get());
        return CommandResult.forward("/WEB-INF/views/editarTematica.jsp");
    }
}