package org.uned.practicatw.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ServiceFactory;
import org.uned.practicatw.service.UsuarioService;
import org.uned.practicatw.service.exception.EmailYaRegistradoException;
import org.uned.practicatw.utils.PasswordUtil;

import java.io.IOException;

public class CrearUsuarioCommand implements Command {

    private final UsuarioService usuarioService;

    public CrearUsuarioCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario u;
        String view;

        switch (req.getParameter("tipo_usuario")) {
            case "ESTUDIANTE" -> u = new Estudiante();
            case "PROFESOR" -> u = new Profesor();
            case "ADMINISTRADOR" -> u = new Administrador();
            default -> throw new ServletException("No se encontro el tipo de usuario");
        }

        u.setEmail(req.getParameter("email"));
        u.setPassword(PasswordUtil.hashPassword(req.getParameter("password")));
        u.setNombre(req.getParameter("nombre"));
        u.setApellidos(req.getParameter("apellidos"));
        u.setDireccion(req.getParameter("direccion"));
        u.setProvincia(req.getParameter("provincia"));
        u.setCodigopostal(req.getParameter("codigopostal"));

        try {
            usuarioService.crear(u);
            view = "/WEB-INF/views/index.jsp";
        } catch (EmailYaRegistradoException e) {
            req.getSession().setAttribute("errorRegistro", e.getMessage());
            view = "/WEB-INF/views/index.jsp";
        }

        return CommandResult.redirect(view);
    }
}
