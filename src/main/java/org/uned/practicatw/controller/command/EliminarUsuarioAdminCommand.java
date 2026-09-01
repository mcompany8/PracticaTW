package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.UsuarioService;

import java.util.List;
import java.util.Optional;

/**
 * Elimina un usuario desde el panel de administración (ruta
 * {@code eliminarUsuarioAdmin}, POST). Dos salvaguardas antes de borrar:
 * un administrador no puede eliminar su propia cuenta, y un {@link Profesor}
 * con cursos todavía asignados no se puede eliminar hasta reasignarlos o
 * borrarlos (evita dejar cursos huérfanos, ya que {@code Curso.responsable}
 * no tiene {@code @OnDelete}).
 * <p>
 * Para un {@link org.uned.practicatw.model.Estudiante}, el borrado sí cae en
 * cascada sobre sus inscripciones (y, con ellas, valoraciones y entregas),
 * gracias al {@code @OnDelete(CASCADE)} de {@code Inscripcion.estudiante}.
 */
public class EliminarUsuarioAdminCommand implements Command {

    private final UsuarioService usuarioService;
    private final CursoService cursoService;

    public EliminarUsuarioAdminCommand(UsuarioService usuarioService, CursoService cursoService) {
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long usuarioId = Long.parseLong(req.getParameter("usuarioId"));

        if (usuarioId.equals(admin.getId())) {
            req.getSession().setAttribute("errorUsuario", "No puedes eliminar tu propia cuenta.");
            return CommandResult.redirect("/app/listarUsuarios");
        }

        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(usuarioId);
        if (usuarioOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario instanceof Profesor) {
            List<Curso> cursos = cursoService.obtenerCursosPorProfesor(usuarioId);
            if (!cursos.isEmpty()) {
                req.getSession().setAttribute("errorUsuario",
                        "No se puede eliminar: este profesor todavía tiene " + cursos.size()
                                + " curso(s) asignado(s). Reasígnalos o elimínalos primero.");
                return CommandResult.redirect("/app/listarUsuarios");
            }
        }

        usuarioService.eliminar(usuarioId);

        return CommandResult.redirect("/app/listarUsuarios");
    }
}