package org.uned.practicatw.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.command.Command;

import java.io.IOException;

/**
 * Punto de entrada único de la aplicación (patrón Front Controller),
 * mapeado a {@code /app/*}. Traduce la parte de la URL después de
 * {@code /app/} en un nombre de ruta (p. ej. {@code /app/misCursos} → {@code "misCursos"}),
 * busca el {@link Command} correspondiente en {@link CommandFactory}, lo
 * ejecuta, y resuelve el {@link CommandResult} devuelto haciendo
 * {@code forward} o {@code redirect} según su {@link CommandResult.NavigationType}.
 * <p>
 * No distingue por verbo HTTP: una misma ruta recibe tanto peticiones
 * {@code GET} como {@code POST} en el mismo {@code Command} — por eso, en
 * toda la aplicación, "mostrar un formulario" y "procesar su envío" son
 * siempre dos rutas (y dos clases {@code Command}) distintas, nunca una sola
 * que mire {@code request.getMethod()}.
 * <p>
 * Lleva {@code @MultipartConfig} a nivel de servlet (no por ruta individual),
 * así que cualquier {@code Command} puede leer {@code request.getPart(...)}
 * sin configuración adicional, incluidas las rutas que no reciben ficheros.
 */
@WebServlet("/app/*")
@MultipartConfig(
//        location = "C:/temp/archivitos",
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 15 * 1024 * 1024,
        fileSizeThreshold = 0

)
public class FrontController extends HttpServlet {

    /**
     * Resuelve y ejecuta el {@link Command} correspondiente a la ruta pedida.
     *
     * @param req  la petición HTTP
     * @param resp la respuesta HTTP
     * @throws ServletException nunca se lanza directamente aquí (cualquier
     *                          excepción del {@code Command} se envuelve en
     *                          {@link RuntimeException}), se mantiene por la
     *                          firma heredada de {@code HttpServlet.service}
     * @throws IOException      si falla el {@code forward}/{@code redirect}
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String pathInfo = req.getPathInfo();
        String action = (pathInfo == null || pathInfo.equals("/"))
                ? "home"
                : pathInfo.substring(1);

        Command command = CommandFactory.getCommand(action);
        if (command == null) {
            resp.sendError(404);
            return;
        }

        try {
            CommandResult cr = command.execute(req, resp);

            if (cr.getNavigationType() == CommandResult.NavigationType.REDIRECT) {
                resp.sendRedirect(req.getContextPath() + cr.getView());
            } else {
                req.getRequestDispatcher(cr.getView()).forward(req, resp);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}