package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.UsuarioService;

import java.util.Optional;

/**
 * Muestra el formulario de edición de un usuario desde el panel de
 * administración (ruta {@code editarUsuarioAdmin}, GET). El guardado lo
 * procesa {@code ActualizarUsuarioAdminCommand}. Solo accesible por un
 * {@link Administrador}.
 */
public class EditarUsuarioAdminCommand implements Command {

    private final UsuarioService usuarioService;

    public EditarUsuarioAdminCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long id = Long.parseLong(req.getParameter("id"));
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(id);
        if (usuarioOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        req.setAttribute("usuarioEditado", usuarioOpt.get());
        return CommandResult.forward("/WEB-INF/views/editarUsuarioAdmin.jsp");
    }
}