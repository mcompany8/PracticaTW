package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;

/** Cierra la sesión del usuario (ruta {@code logout}), invalidándola por completo. */
public class CerrarSesionCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().invalidate();
        return CommandResult.redirect("/app/inicio");
    }
}