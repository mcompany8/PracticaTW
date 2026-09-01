// AdminCommand.java
package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.*;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.InscripcionService;
import org.uned.practicatw.service.UsuarioService;
import org.uned.practicatw.service.ValoracionService;

import java.util.List;

/**
 * Panel de administración (ruta {@code admin}): estadísticas globales de la
 * plataforma (usuarios por rol, cursos, inscripciones, valoración media) y
 * accesos a la gestión de usuarios, temáticas y configuración.
 * <p>
 * Los totales se calculan sobre las listas completas devueltas por
 * {@code obtenerTodos()} de cada servicio, en vez de con consultas
 * {@code COUNT} dedicadas — suficiente para el volumen de datos de esta
 * práctica, aunque no sería la aproximación adecuada a mayor escala.
 */
public class AdminCommand implements Command {

    private final UsuarioService usuarioService;
    private final CursoService cursoService;
    private final InscripcionService inscripcionService;
    private final ValoracionService valoracionService;

    public AdminCommand(UsuarioService usuarioService, CursoService cursoService,
                        InscripcionService inscripcionService, ValoracionService valoracionService) {
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
        this.inscripcionService = inscripcionService;
        this.valoracionService = valoracionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        List<Usuario> usuarios = usuarioService.obtenerTodos();
        long totalEstudiantes = usuarios.stream().filter(u -> u instanceof Estudiante).count();
        // getClass() == Profesor.class (no instanceof) para no contar aquí a los Administrador,
        // que también son Profesor por herencia
        long totalProfesores = usuarios.stream().filter(u -> u.getClass() == Profesor.class).count();
        long totalAdministradores = usuarios.stream().filter(u -> u instanceof Administrador).count();

        List<Curso> cursos = cursoService.obtenerTodos();
        List<Inscripcion> inscripciones = inscripcionService.obtenerTodos();
        List<Valoracion> valoraciones = valoracionService.obtenerTodos();

        double valoracionMedia = valoraciones.stream()
                .mapToInt(Valoracion::getValoracion)
                .average()
                .orElse(0);

        req.setAttribute("totalUsuarios", usuarios.size());
        req.setAttribute("totalEstudiantes", totalEstudiantes);
        req.setAttribute("totalProfesores", totalProfesores);
        req.setAttribute("totalAdministradores", totalAdministradores);
        req.setAttribute("totalCursos", cursos.size());
        req.setAttribute("totalInscripciones", inscripciones.size());
        req.setAttribute("totalValoraciones", valoraciones.size());
        req.setAttribute("valoracionMedia", valoraciones.isEmpty() ? null : valoracionMedia);

        return CommandResult.forward("/WEB-INF/views/admin.jsp");
    }
}