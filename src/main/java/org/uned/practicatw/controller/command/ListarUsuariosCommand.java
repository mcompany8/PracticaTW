package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.service.UsuarioService;

public class ListarUsuariosCommand implements Command {

    private final UsuarioService usuarioService;

    public ListarUsuariosCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("usuarios", usuarioService.listar());
        return CommandResult.forward("WEB-INF/views/listaUsuarios.jsp");
    }
}
