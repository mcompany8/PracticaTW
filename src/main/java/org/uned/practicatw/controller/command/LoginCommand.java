package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.AuthService;
import org.uned.practicatw.service.AuthServiceImpl;

import java.util.Optional;

public class LoginCommand implements Command {

    private final AuthService authService;

    public LoginCommand(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Optional<Usuario> usuario = authService.autenticar(req.getParameter("email"), req.getParameter("password"));
        String view;

        if (usuario.isPresent()) {
            req.getSession().setAttribute("usuario", usuario.get());
            view = "/app/inicio";
        } else {
            req.getSession().setAttribute("credencialesInvalidas", "Credenciales inválidas");
            view = "/app/login";
        }
        return CommandResult.redirect(view);
    }
}
