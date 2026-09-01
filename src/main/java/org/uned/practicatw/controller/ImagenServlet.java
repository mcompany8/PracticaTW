package org.uned.practicatw.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.uned.practicatw.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Sirve las imágenes de cursos y temáticas (rutas {@code /imagenes/cursos/<fichero>},
 * {@code /imagenes/tematicas/<fichero>}), leyéndolas de
 * {@link AppConfig#IMAGENES_DIR} en disco.
 * <p>
 * A diferencia de {@link ContenidoServlet}, admite subcarpetas en la URL
 * (necesario para distinguir {@code cursos/} de {@code tematicas/}), usando
 * {@code pathInfo.substring(1)} completo en vez de solo el nombre de fichero
 * — por eso, a diferencia de aquel, la comprobación de contención se hace
 * aquí contra la ruta ya resuelta y normalizada, no contra el nombre de
 * fichero suelto.
 */
@WebServlet(name = "imagenServlet", description = "", value = "/imagenes/*")
public class ImagenServlet extends HttpServlet {

    /**
     * Sirve la imagen pedida con su {@code Content-Type} detectado.
     *
     * @param req  la petición HTTP; {@code getPathInfo()} debe contener la
     *             subcarpeta y el nombre del fichero (p. ej. {@code /cursos/portada.jpg})
     * @param resp la respuesta HTTP
     * @throws IOException si falla la lectura o el envío del fichero
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null ||  pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Path ruta = AppConfig.IMAGENES_DIR.resolve(pathInfo.substring(1)).normalize();

        if (!ruta.startsWith(AppConfig.IMAGENES_DIR)
                || Files.notExists(ruta)
                || !Files.isReadable(ruta)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = Files.probeContentType(ruta);
        resp.setContentType(contentType != null ?  contentType : "application/octet-stream");
        resp.setHeader("Cache-Control", "max-age=3600");

        Files.copy(ruta, resp.getOutputStream());
    }
}