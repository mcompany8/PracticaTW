package org.uned.practicatw.utils;


import jakarta.servlet.http.Part;
import org.uned.practicatw.service.exception.FicheroInvalidoException;
import org.uned.practicatw.service.exception.FicheroNoPermitidoException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

public class FilesUtil {

    private static final Map<String, String> TIPOS_PERMITIDOS = Map.of(
            "application/pdf", "pdf",
            "application/msword", "doc",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx",
            "image/jpeg", "jpeg",
            "image/png", "png",
            "image/webp", "webp"
    );

    private FilesUtil() {}

    public static String copy(Part part, Path out) throws IOException {

        String fileName = part.getSubmittedFileName();
        String contentType = part.getContentType();
        String extension = TIPOS_PERMITIDOS.get(contentType);

        if (part.getSize() == 0) {
            throw new FicheroInvalidoException(fileName);
        }

        if (extension == null) {
            throw new FicheroNoPermitidoException(fileName);
        }

        fileName += "." + extension;
        Files.copy(part.getInputStream(), out.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    public static void copy(Path in, Path out) throws IOException {
        Files.copy(in, out.resolve(in.getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }
}