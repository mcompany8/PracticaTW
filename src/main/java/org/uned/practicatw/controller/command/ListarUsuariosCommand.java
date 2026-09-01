package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.UsuarioService;

/**
 * Lista todos los usuarios de la plataforma (ruta {@code listarUsuarios}), para el panel de administración.
 *
 * @implNote no comprueba que el usuario en sesión sea un {@link org.uned.practicatw.model.Administrador}
 * — a diferencia del resto de {@code Command} de gestión de usuarios/temáticas/configuración, que sí lo
 * hacen. Cualquier usuario logueado (o incluso sin sesión) puede acceder a esta ruta directamente.
 */
public class ListarUsuariosCommand implements Command {

    private final UsuarioService usuarioService;

    public ListarUsuariosCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Usuario admin = (Usuario) request.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        request.setAttribute("usuarios", usuarioService.obtenerTodos());
        return CommandResult.forward("/WEB-INF/views/listaUsuarios.jsp");
    }
}