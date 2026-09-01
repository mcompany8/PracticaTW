// MostrarCrearTematicaCommand.java
package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Usuario;

/**
 * Muestra el formulario de creación de una temática (ruta {@code crearTematica}, GET).
 * El guardado lo procesa {@code GuardarTematicaCommand}. Solo accesible por
 * un {@link Administrador}.
 */
public class MostrarCrearTematicaCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }
        return CommandResult.forward("/WEB-INF/views/crearTematica.jsp");
    }
}