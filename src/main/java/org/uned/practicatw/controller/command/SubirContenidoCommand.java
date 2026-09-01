package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.utils.FilesUtil;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class SubirContenidoCommand implements Command {

    private final ContenidoService contenidoService;
    private final CursoService cursoService;

    public SubirContenidoCommand(ContenidoService contenidoService, CursoService cursoService) {
        this.contenidoService = contenidoService;
        this.cursoService = cursoService;
    }

    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Part filePart = req.getPart("archivo");
        Curso curso = (Curso) req.getAttribute("curso");
        Integer orden = Integer.parseInt(req.getParameter("orden"));
        FilesUtil.copy(filePart, AppConfig.CONTENIDO_DIR);

        Contenido contenido = Contenido.builder()
                .titulo(filePart.getSubmittedFileName())
                .curso(curso)
                .fechaSubida(LocalDateTime.now())
                .orden(orden)
                .build();

        contenidoService.crear(contenido);
        return CommandResult.redirect("/app/inicio");
    }
}
