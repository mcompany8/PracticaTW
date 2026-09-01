package org.uned.practicatw.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.uned.practicatw.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "contenidoServlet", description = "", value = "/contenidos/*")
public class ContenidoServlet extends HttpServlet {

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