package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.*;
import org.uned.practicatw.service.UsuarioService;
import org.uned.practicatw.service.exception.EmailYaRegistradoException;
import org.uned.practicatw.utils.PasswordUtil;

public class GuardarUsuarioAdminCommand implements Command {

    private final UsuarioService usuarioService;

    public GuardarUsuarioAdminCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Usuario nuevo = switch (req.getParameter("rol")) {
            case "PROFESOR" -> new Profesor();
            case "ADMINISTRADOR" -> new Administrador();
            default -> new Estudiante();
        };

        nuevo.setNombre(req.getParameter("nombre"));
        nuevo.setApellidos(req.getParameter("apellidos"));
        nuevo.setEmail(req.getParameter("email"));
        nuevo.setPassword(PasswordUtil.hashPassword(req.getParameter("password")));
        nuevo.setDireccion(req.getParameter("direccion"));
        nuevo.setPoblacion(req.getParameter("poblacion"));
        nuevo.setProvincia(req.getParameter("provincia"));
        nuevo.setCodigopostal(req.getParameter("codigopostal"));

        try {
            usuarioService.crear(nuevo);
        } catch (EmailYaRegistradoException e) {
            req.getSession().setAttribute("errorUsuario", e.getMessage());
            return CommandResult.redirect("/app/crearUsuarioAdmin");
        }

        return CommandResult.redirect("/app/listarUsuarios");
    }
}