// ActualizarTematicaCommand.java (POST)
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

import java.util.Optional;

public class ActualizarTematicaCommand implements Command {

    private final TematicaService tematicaService;

    public ActualizarTematicaCommand(TematicaService tematicaService) {
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long tematicaId = Long.parseLong(req.getParameter("tematicaId"));
        Optional<Tematica> tematicaOpt = tematicaService.obtenerPorId(tematicaId);
        if (tematicaOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Tematica tematica = tematicaOpt.get();
        tematica.setTitulo(req.getParameter("titulo"));
        tematica.setDescripcion(req.getParameter("descripcion"));

        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            tematica.setImagen(FilesUtil.copy(imagenPart, AppConfig.IMAGENES_DIR.resolve("tematicas")));
        }

        tematicaService.actualizar(tematica);

        return CommandResult.redirect("/app/listarTematicas");
    }
}