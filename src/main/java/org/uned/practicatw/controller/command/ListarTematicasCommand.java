package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.TematicaService;

/** Lista todas las temáticas (ruta {@code listarTematicas}). Solo accesible por un {@link Administrador}. */
public class ListarTematicasCommand implements Command {

    private final TematicaService tematicaService;

    public ListarTematicasCommand(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        req.setAttribute("tematicas", tematicaService.obtenerTodos());
        return CommandResult.forward("/WEB-INF/views/listaTematicas.jsp");
    }
}