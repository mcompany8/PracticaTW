package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ConfiguracionService;

/**
 * Muestra el formulario de configuración global de la plataforma (ruta
 * {@code editarConfiguracion}): texto e imagen del hero de la portada, número
 * de cursos recomendados. Solo accesible por un {@link Administrador}.
 */
public class EditarConfiguracionCommand implements Command {

    private final ConfiguracionService configuracionService;

    public EditarConfiguracionCommand(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        req.setAttribute("config", configuracionService.obtenerPorId(1L).orElseThrow());
        return CommandResult.forward("/WEB-INF/views/configuracion.jsp");
    }
}