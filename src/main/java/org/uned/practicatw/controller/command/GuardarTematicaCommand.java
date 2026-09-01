package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Tematica;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.TematicaService;
import org.uned.practicatw.utils.FilesUtil;

/**
 * Guarda el formulario de creación de una temática (ruta {@code guardarTematica}, POST).
 * La imagen es opcional. Solo accesible por un {@link Administrador}.
 */
public class GuardarTematicaCommand implements Command {

    private final TematicaService tematicaService;

    public GuardarTematicaCommand(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        String imagen = null;
        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            imagen = FilesUtil.copy(imagenPart, AppConfig.IMAGENES_DIR.resolve("tematicas"));
        }

        Tematica tematica = Tematica.builder()
                .titulo(req.getParameter("titulo"))
                .descripcion(req.getParameter("descripcion"))
                .imagen(imagen)
                .build();

        tematicaService.crear(tematica);

        return CommandResult.redirect("/app/listarTematicas");
    }
}