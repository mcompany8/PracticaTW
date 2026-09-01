package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.utils.FilesUtil;

import java.util.Optional;

public class GuardarMaterialCommand implements Command {

    private final ContenidoService contenidoService;
    private final CursoService cursoService;

    public GuardarMaterialCommand(ContenidoService contenidoService, CursoService cursoService) {
        this.contenidoService = contenidoService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long materialId = Long.parseLong(req.getParameter("materialId"));
        Long cursoId = Long.parseLong(req.getParameter("cursoId"));
        String vista = "&vista=materiales";

        Curso curso = cursoService.obtenerCursoPorIdYProfesor(cursoId, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Optional<Contenido> materialOpt = contenidoService.obtenerPorId(materialId);
        if (materialOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Contenido material = materialOpt.get();

        if (!material.getCurso().getId().equals(cursoId)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        material.setTitulo(req.getParameter("titulo"));

        Integer ordenNuevo = Integer.parseInt(req.getParameter("orden"));
        if (ordenNuevo < 1) {
            ordenNuevo = 1;
        }
        if (!ordenNuevo.equals(material.getOrden())) {
            // Mismo mecanismo de sentinela que en el reordenamiento de la lista
            contenidoService.actualizarOrden(cursoId, materialId, material.getOrden(), ordenNuevo);
        }

        Part archivoPart = req.getPart("archivo");
        String url = req.getParameter("url");

        if (archivoPart != null && archivoPart.getSize() > 0) {
            material.setUri(FilesUtil.copy(archivoPart, AppConfig.CONTENIDO_DIR));
        } else if (url != null && !url.isBlank()) {
            material.setUri(url);
        }
        // si los dos vienen vacíos, se mantiene el uri que ya tenía

        contenidoService.actualizar(material);

        return CommandResult.redirect("/app/detalleCurso?id=" + cursoId + vista);
    }
}