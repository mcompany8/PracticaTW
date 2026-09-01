package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.CursoService;

/**
 * Elimina un curso por completo (ruta {@code eliminarCurso}, POST). Gracias a
 * los {@code @OnDelete(CASCADE)} declarados en {@link org.uned.practicatw.model.Contenido},
 * {@link org.uned.practicatw.model.Inscripcion} y {@code Curso.tematicas},
 * más la cascada de JPA de {@code Curso.tareas}, un único {@code eliminar(cursoId)}
 * arrastra en cadena materiales, inscripciones (y con ellas valoraciones y
 * entregas de tareas) y las filas de la tabla intermedia de temáticas — ver
 * la documentación de {@link Curso} para el detalle completo del grafo de borrado.
 * <p>
 * Comprueba que el curso pertenece al profesor logueado antes de eliminarlo
 * (protección IDOR).
 */
public class EliminarCursoCommand implements Command {

    private final CursoService cursoService;

    public EliminarCursoCommand(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario == null) {
            return CommandResult.redirect("/app/login");
        }

        Long cursoId = Long.parseLong(req.getParameter("cursoId"));

        Curso curso = cursoService.obtenerCursoPorIdYProfesor(cursoId, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        cursoService.eliminar(cursoId);

        return CommandResult.redirect("/app/cursosProf");
    }
}