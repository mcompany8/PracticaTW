package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.service.TematicaService;

/**
 * Muestra el formulario de registro público (ruta {@code registro}, GET),
 * con el catálogo de temáticas para el selector de áreas de interés. El
 * guardado lo procesa {@code CrearUsuarioCommand}.
 */
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