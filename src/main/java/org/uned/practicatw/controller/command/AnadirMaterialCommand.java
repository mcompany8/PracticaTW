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

import java.time.LocalDateTime;

public class AnadirMaterialCommand implements Command {

    private final ContenidoService contenidoService;
    private final CursoService cursoService;

    public AnadirMaterialCommand(ContenidoService contenidoService, CursoService cursoService) {
        this.contenidoService = contenidoService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long cursoId = Long.parseLong(req.getParameter("cursoId"));
        String vista = "&vista=materiales";

        // IDOR: el curso tiene que pertenecer al profesor logueado
        Curso curso = cursoService.obtenerCursoPorIdYProfesor(cursoId, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        String titulo = req.getParameter("titulo");
        Integer orden = Integer.parseInt(req.getParameter("orden"));
        if (orden < 1) {
            orden = 1;
        }

        Part archivoPart = req.getPart("archivo");
        String url = req.getParameter("url");

        String uri;
        if (archivoPart != null && archivoPart.getSize() > 0) {
            uri = FilesUtil.copy(archivoPart, AppConfig.CONTENIDO_DIR);
        } else if (url != null && !url.isBlank()) {
            uri = url;
        } else {
            req.getSession().setAttribute("errorMaterial", "Tienes que adjuntar un archivo o un enlace.");
            return CommandResult.redirect("/app/detalleCurso?id=" + cursoId + vista);
        }

        // Hace hueco antes de insertar, para no chocar con la unique constraint (curso_id, orden)
        contenidoService.hacerHueco(cursoId, orden);

        Contenido material = Contenido.builder()
                .titulo(titulo)
                .curso(curso)
                .fechaSubida(LocalDateTime.now())
                .orden(orden)
                .uri(uri)
                .build();

        contenidoService.crear(material);

        return CommandResult.redirect("/app/detalleCurso?id=" + cursoId + vista);
    }
}