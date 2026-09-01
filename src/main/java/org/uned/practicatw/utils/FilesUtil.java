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

/**
 * Utilidad para guardar ficheros subidos por el usuario (materiales de
 * curso, imágenes de curso/temática/hero) en el sistema de ficheros, bajo
 * las rutas configuradas en {@link org.uned.practicatw.config.AppConfig}.
 */
public class FilesUtil {

    /** Tipos MIME aceptados, mapeados a la extensión con la que se guarda el fichero. */
    private static final Map<String, String> TIPOS_PERMITIDOS = Map.of(
            "application/pdf", "pdf",
            "application/msword", "doc",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx",
            "image/jpeg", "jpeg",
            "image/png", "png",
            "image/webp", "webp"
    );

    private FilesUtil() {}

    /**
     * Guarda el contenido de un {@link Part} (campo {@code <input type="file">}
     * de un formulario) en el directorio indicado, usando el nombre original
     * del fichero más la extensión correspondiente a su tipo MIME.
     * <p>
     * El nombre no se hace único (no lleva UUID ni prefijo): si dos ficheros
     * distintos se suben con el mismo nombre original, el segundo sobrescribe
     * al primero silenciosamente ({@code REPLACE_EXISTING}).
     *
     * @param part el campo de fichero del formulario
     * @param out  directorio destino
     * @return el nombre de fichero final con el que se ha guardado (nombre
     *         original + extensión), para persistirlo en la entidad correspondiente
     * @throws IOException                si falla la copia a disco
     * @throws FicheroInvalidoException    si el fichero está vacío
     * @throws FicheroNoPermitidoException si el tipo MIME no está en {@link #TIPOS_PERMITIDOS}
     */
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

    /**
     * Copia un fichero ya existente en disco (usado para los ficheros semilla
     * embebidos en el classpath, ver {@code SeedListener}) a un directorio destino.
     *
     * @param in  fichero origen
     * @param out directorio destino
     * @throws IOException si falla la copia
     */
    public static void copy(Path in, Path out) throws IOException {
        Files.copy(in, out.resolve(in.getFileName()), StandardCopyOption.REPLACE_EXISTING);
    }
}