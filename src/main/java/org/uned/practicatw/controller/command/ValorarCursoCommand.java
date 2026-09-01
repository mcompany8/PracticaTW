package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Estudiante;
import org.uned.practicatw.model.Inscripcion;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.model.Valoracion;
import org.uned.practicatw.service.InscripcionService;
import org.uned.practicatw.service.ValoracionService;

import java.util.Optional;

public class ValorarCursoCommand implements Command {

    private final ValoracionService valoracionService;
    private final InscripcionService inscripcionService;

    public ValorarCursoCommand(ValoracionService valoracionService, InscripcionService inscripcionService) {
        this.valoracionService = valoracionService;
        this.inscripcionService = inscripcionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Estudiante)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long inscripcionId = Long.parseLong(req.getParameter("inscripcionId"));
        Optional<Inscripcion> inscripcionOpt = inscripcionService.obtenerPorId(inscripcionId);

        if (inscripcionOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Inscripcion inscripcion = inscripcionOpt.get();

        // IDOR: la inscripción tiene que pertenecer al alumno logueado
        if (!inscripcion.getEstudiante().getId().equals(usuario.getId())) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long cursoId = inscripcion.getCurso().getId();

        // Si ya existe una valoración para esta inscripción, no se crea otra
        // (evita reventar la unique constraint si reenvían el formulario)
        if (valoracionService.obtenerPorInscripcion(inscripcionId) != null) {
            return CommandResult.redirect("/app/miInscripcion?id=" + cursoId);
        }

        int puntuacion;
        try {
            puntuacion = Integer.parseInt(req.getParameter("valoracion"));
        } catch (NumberFormatException e) {
            puntuacion = 0;
        }

        if (puntuacion < 1 || puntuacion > 5) {
            req.getSession().setAttribute("errorValoracion", "La puntuación debe estar entre 1 y 5.");
            return CommandResult.redirect("/app/miInscripcion?id=" + cursoId);
        }

        Valoracion valoracion = Valoracion.builder()
                .inscripcion(inscripcion)
                .valoracion(puntuacion)
                .comentario(req.getParameter("comentario"))
                .build();



        valoracionService.crear(valoracion);

        return CommandResult.redirect("/app/miInscripcion?id=" + cursoId);
    }
}