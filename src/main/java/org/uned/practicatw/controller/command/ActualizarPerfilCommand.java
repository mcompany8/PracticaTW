package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.EstudianteTematica;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.EstudianteTematicaService;
import org.uned.practicatw.service.TematicaService;
import org.uned.practicatw.service.UsuarioService;

/**
 * Guarda los cambios del formulario de "Mi cuenta" (ruta {@code actualizarPerfil}, POST):
 * datos personales para cualquier usuario, y temáticas de interés si es un
 * {@link Estudiante}.
 * <p>
 * Nunca lee un id de usuario del formulario — opera siempre sobre
 * {@code session.getAttribute("usuario")}, así que por construcción no hay
 * forma de que un usuario edite la cuenta de otro.
 */
public class ActualizarPerfilCommand implements Command {

    private final UsuarioService usuarioService;
    private final TematicaService tematicaService;
    private final EstudianteTematicaService estudianteTematicaService;

    public ActualizarPerfilCommand(UsuarioService usuarioService,
                                   TematicaService tematicaService,
                                   EstudianteTematicaService estudianteTematicaService) {
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
        this.estudianteTematicaService = estudianteTematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        // Se opera siempre sobre el usuario de la sesión: nunca se lee un id
        // del formulario, así que no hay forma de editar la cuenta de otro.
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario == null) {
            return CommandResult.redirect("/app/login");
        }

        usuario.setNombre(req.getParameter("nombre"));
        usuario.setApellidos(req.getParameter("apellidos"));
        usuario.setDireccion(req.getParameter("direccion"));
        usuario.setPoblacion(req.getParameter("poblacion"));
        usuario.setProvincia(req.getParameter("provincia"));
        usuario.setCodigopostal(req.getParameter("codigopostal"));

        usuarioService.actualizar(usuario);

        if (usuario instanceof Estudiante estudiante) {
            for (EstudianteTematica et : estudianteTematicaService.obtenerPorEstudiante(estudiante.getId())) {
                estudianteTematicaService.eliminar(et.getId());
            }

            String[] tematicasIds = req.getParameterValues("tematicasIds");
            if (tematicasIds != null) {
                for (String idTexto : tematicasIds) {
                    Long tematicaId = Long.parseLong(idTexto);
                    tematicaService.obtenerPorId(tematicaId).ifPresent(tematica -> {
                        EstudianteTematica et = EstudianteTematica.builder()
                                .estudiante(estudiante)
                                .tematica(tematica)
                                .build();
                        estudianteTematicaService.crear(et);
                    });
                }
            }
        }

        req.getSession().setAttribute("perfilActualizado", "Tus datos se han actualizado correctamente.");
        return CommandResult.redirect("/app/perfil");
    }
}