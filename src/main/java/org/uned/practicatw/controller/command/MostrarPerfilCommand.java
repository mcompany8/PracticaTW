package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.EstudianteTematica;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.EstudianteTematicaService;
import org.uned.practicatw.service.TematicaService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Muestra el formulario de "Mi cuenta" (ruta {@code perfil}, GET), con las
 * temáticas de interés precargadas si el usuario es un {@link Estudiante}.
 * El guardado lo procesa {@code ActualizarPerfilCommand}.
 */
public class MostrarPerfilCommand implements Command {

    private final TematicaService tematicaService;
    private final EstudianteTematicaService estudianteTematicaService;

    public MostrarPerfilCommand(TematicaService tematicaService,
                                EstudianteTematicaService estudianteTematicaService) {
        this.tematicaService = tematicaService;
        this.estudianteTematicaService = estudianteTematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (usuario == null) {
            return CommandResult.redirect("/app/login");
        }

        if (usuario instanceof Estudiante estudiante) {
            req.setAttribute("tematicas", tematicaService.obtenerTodos());

            List<EstudianteTematica> actuales =
                    estudianteTematicaService.obtenerPorEstudiante(estudiante.getId());

            // CSV con comas de guarda en los extremos, para poder comparar por id
            // completo en el JSP con fn:contains sin falsos positivos de subcadena
            String csv = actuales.stream()
                    .map(et -> String.valueOf(et.getTematica().getId()))
                    .collect(Collectors.joining(",", ",", ","));
            req.setAttribute("tematicasSeleccionadasCsv", csv);
        }

        return CommandResult.forward("/WEB-INF/views/micuenta.jsp");
    }
}