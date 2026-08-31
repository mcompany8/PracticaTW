package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.service.ContenidoService;
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

    public SubirContenidoCommand(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }


    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Part filePart = req.getPart("archivo");
        FilesUtil.copy(filePart, AppConfig.CONTENIDO_DIR);

        Contenido contenido = Contenido.builder()
                .titulo(filePart.getSubmittedFileName())
                .propietario((Profesor) req.getSession().getAttribute("usuario"))
                .publico(false)
                .fechaSubida(LocalDateTime.now())
                .tipoContenido(Contenido.TipoContenido.ARCHIVO)
                .build();

        contenidoService.crear(contenido);
        return CommandResult.redirect("/app/inicio");
    }
}
