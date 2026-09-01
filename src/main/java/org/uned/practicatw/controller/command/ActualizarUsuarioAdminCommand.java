package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.UsuarioService;

import java.util.Optional;

public class ActualizarUsuarioAdminCommand implements Command {

    private final UsuarioService usuarioService;

    public ActualizarUsuarioAdminCommand(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long usuarioId = Long.parseLong(req.getParameter("usuarioId"));
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(usuarioId);
        if (usuarioOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setNombre(req.getParameter("nombre"));
        usuario.setApellidos(req.getParameter("apellidos"));
        usuario.setDireccion(req.getParameter("direccion"));
        usuario.setPoblacion(req.getParameter("poblacion"));
        usuario.setProvincia(req.getParameter("provincia"));
        usuario.setCodigopostal(req.getParameter("codigopostal"));

        usuarioService.actualizar(usuario);

        // Cambio de rol: solo entre Profesor <-> Administrador
        if (!"Estudiante".equals(usuario.getTipoUsuario())) {
            boolean marcadoAdmin = req.getParameter("esAdministrador") != null;

            // Un admin no puede quitarse a sí mismo el rol de administrador
            // desde aquí, para no quedarse fuera de esta misma pantalla.
            if (usuarioId.equals(admin.getId()) && !marcadoAdmin) {
                req.getSession().setAttribute("errorUsuario",
                        "No puedes quitarte a ti mismo el rol de administrador.");
                return CommandResult.redirect("/app/listarUsuarios");
            }

            String tipoNuevo = marcadoAdmin ? "ADMINISTRADOR" : "PROFESOR";
            if (!usuario.getTipoUsuario().toUpperCase().equals(tipoNuevo)) {
                usuarioService.cambiarTipo(usuarioId, tipoNuevo);
            }
        }

        return CommandResult.redirect("/app/listarUsuarios");
    }
}