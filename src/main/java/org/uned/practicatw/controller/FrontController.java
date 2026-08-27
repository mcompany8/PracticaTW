package org.uned.practicatw.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.command.Command;

import java.io.IOException;

@WebServlet("/app/*")
@MultipartConfig(
        location = "/temp",
        maxFileSize = 10 * 1024 * 1024,
        maxRequestSize = 15 * 1024 * 1024,
        fileSizeThreshold = 0
        
)
public class FrontController extends HttpServlet {

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
