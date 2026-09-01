package org.uned.practicatw.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.uned.practicatw.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Sirve los materiales de curso subidos por los profesores (ruta
 * {@code /contenidos/<nombre-de-fichero>}), leyéndolos directamente de
 * {@link AppConfig#CONTENIDO_DIR} en disco.
 * <p>
 * Solo admite un nombre de fichero plano en la URL (sin subcarpetas):
 * {@code Path.of(pathInfo).getFileName()} descarta cualquier segmento de
 * ruta previo, y la comprobación posterior
 * {@code ruta.startsWith(AppConfig.CONTENIDO_DIR)} verifica además, tras el
 * {@code normalize()}, que la ruta resuelta sigue dentro del directorio
 * esperado — doble defensa contra un {@code ../../} en la URL.
 */
@WebServlet(name = "contenidoServlet", description = "", value = "/contenidos/*")
public class ContenidoServlet extends HttpServlet {

    /**
     * Sirve el fichero pedido con su {@code Content-Type} detectado y
     * cabecera {@code Content-Disposition: inline} (para que un PDF se abra
     * en el visor del navegador en vez de forzar la descarga).
     *
     * @param req  la petición HTTP; {@code getPathInfo()} debe contener el
     *             nombre del fichero
     * @param resp la respuesta HTTP
     * @throws IOException si falla la lectura o el envío del fichero
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Solo el nombre de fichero: evita que ../../ escape de CONTENIDO_DIR
        String fileName = Path.of(pathInfo).getFileName().toString();
        Path ruta = AppConfig.CONTENIDO_DIR.resolve(fileName).normalize();

        if (!ruta.startsWith(AppConfig.CONTENIDO_DIR)
                || Files.notExists(ruta)
                || !Files.isReadable(ruta)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = Files.probeContentType(ruta);
        resp.setContentType(contentType != null ? contentType : "application/octet-stream");
        resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
        resp.setHeader("Cache-Control", "max-age=3600");

        Files.copy(ruta, resp.getOutputStream());
    }
}