package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;

/**
 * {@code Command} genérico para rutas que solo necesitan mostrar una vista
 * estática, sin ninguna lógica ni datos que cargar (p. ej. {@code login},
 * {@code notFound}). Evita crear una clase dedicada para cada una de esas rutas.
 */
public class ViewCommand implements Command {

    private final String view;

    public ViewCommand(String view) {
        this.view = view;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return CommandResult.forward(view);
    }
}