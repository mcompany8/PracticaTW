package org.uned.practicatw.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.uned.practicatw.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(name = "imagenServlet", description = "", value = "/imagenes/*")
public class ImagenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null ||  pathInfo.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String fileName = Path.of(pathInfo).getFileName().toString();
        Path ruta = AppConfig.IMAGENES_DIR.resolve(fileName);

        if (Files.notExists(ruta) || !Files.isReadable(ruta)) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = Files.probeContentType(ruta);
        resp.setContentType(contentType != null ?  contentType : "application/octet-stream");
        resp.setHeader("Cache-Control", "max-age=3600");

        Files.copy(ruta, resp.getOutputStream());
    }
}