package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.service.ContenidoService;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class SubirContenidoCommand implements Command {

    private static final Map<String, String> TIPOS_PERMITIDOS = Map.of(
            "application/pdf", "pdf",
            "application/msword", "doc",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx"
    );

    private final ContenidoService contenidoService;

    public SubirContenidoCommand(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }


    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Part filePart = req.getPart("archivo");

        if (filePart == null || filePart.getSize() == 0) {
            req.getSession().setAttribute("error", "No se ha incluido archivo o está vacío.");
            return CommandResult.redirect("/app/contenido");
        }

        String contentType = filePart.getContentType();
        String extension = TIPOS_PERMITIDOS.get(contentType);

        if (extension == null) {
            req.getSession().setAttribute("error", "Tipo de archivo no permitido: " + contentType);
            return CommandResult.redirect("/app/contenido");
        }

        String nombreOriginal = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String nombreArchivo = UUID.randomUUID().toString() +  "." + extension;

        Path dir = Paths.get("/tmp/uploads");
        Files.createDirectories(dir);

        try (InputStream is = filePart.getInputStream()) {
            Files.copy(is, dir.resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
        }

        Contenido contenido = Contenido.builder()
                .ficheroId(nombreArchivo)
                .titulo(nombreOriginal)
                .propietario((Profesor) req.getSession().getAttribute("usuario"))
                .publico(false)
                .fechaSubida(LocalDateTime.now())
                .tipoContenido(Contenido.TipoContenido.ARCHIVO)
                .build();

        contenidoService.crear(contenido);
        return CommandResult.redirect("/app/inicio");
    }
}
