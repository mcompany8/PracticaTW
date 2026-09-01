package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Nivel;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Tematica;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.TematicaService;
import org.uned.practicatw.utils.FilesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda el formulario de creación de un curso (ruta {@code guardarCurso}, POST).
 * El {@link Profesor} responsable se toma siempre del usuario en sesión, no
 * de ningún parámetro del formulario (a diferencia de la
 * {@code CrearCursoCommand} obsoleta que sustituye), así que no hay forma de
 * crear un curso a nombre de otro profesor. Redirige al detalle del curso
 * recién creado.
 */
public class GuardarCursoCommand implements Command {

    private final CursoService cursoService;
    private final TematicaService tematicaService;

    public GuardarCursoCommand(CursoService cursoService, TematicaService tematicaService) {
        this.cursoService = cursoService;
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        String titulo = req.getParameter("titulo");
        String descripcion = req.getParameter("descripcion");
        String descripcionLarga = req.getParameter("descripcionLarga");
        Nivel nivel = Nivel.valueOf(req.getParameter("nivel"));

        String duracionParam = req.getParameter("duracionHoras");
        Integer duracionHoras = duracionParam == null || duracionParam.isBlank()
                ? null : Integer.parseInt(duracionParam);

        String imagen = null;
        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            imagen = FilesUtil.copy(imagenPart, AppConfig.IMAGENES_DIR.resolve("cursos"));
        }

        List<Tematica> tematicas = new ArrayList<>();
        String[] tematicasIds = req.getParameterValues("tematicasIds");
        if (tematicasIds != null) {
            for (String idTexto : tematicasIds) {
                Long tematicaId = Long.parseLong(idTexto);
                tematicaService.obtenerPorId(tematicaId).ifPresent(tematicas::add);
            }
        }

        Curso curso = Curso.builder()
                .titulo(titulo)
                .descripcion(descripcion)
                .descripcionLarga(descripcionLarga)
                .duracionHoras(duracionHoras)
                .nivel(nivel)
                .imagen(imagen)
                .responsable(profesor)
                .tematicas(tematicas)
                .build();

        Curso creado = cursoService.crear(curso);

        return CommandResult.redirect("/app/detalleCurso?id=" + creado.getId());
    }
}