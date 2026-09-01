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
 * Guarda los cambios del formulario de edición de un curso (título,
 * descripción, nivel, duración, imagen y temáticas), en la pestaña
 * "Información" de {@code DetalleCursoProfesorCommand} (ruta {@code actualizarCurso}, POST).
 * <p>
 * Comprueba que el curso pertenece al profesor logueado antes de tocarlo
 * (protección IDOR). La imagen solo se sustituye si llega un fichero nuevo;
 * las temáticas se reemplazan por completo con lo marcado en el formulario.
 */
public class ActualizarCursoCommand implements Command {

    private final CursoService cursoService;
    private final TematicaService tematicaService;

    public ActualizarCursoCommand(CursoService cursoService, TematicaService tematicaService) {
        this.cursoService = cursoService;
        this.tematicaService = tematicaService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long cursoId = Long.parseLong(req.getParameter("cursoId"));

        // IDOR: el curso tiene que pertenecer al profesor logueado
        Curso curso = cursoService.obtenerCursoPorIdYProfesor(cursoId, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        curso.setTitulo(req.getParameter("titulo"));
        curso.setDescripcion(req.getParameter("descripcion"));
        curso.setDescripcionLarga(req.getParameter("descripcionLarga"));
        curso.setNivel(Nivel.valueOf(req.getParameter("nivel")));

        String duracionParam = req.getParameter("duracionHoras");
        curso.setDuracionHoras(duracionParam == null || duracionParam.isBlank()
                ? null : Integer.parseInt(duracionParam));

        // Imagen: solo se toca si han subido un fichero nuevo; si el campo
        // viene vacío, se mantiene la que ya tenía el curso.
        Part imagenPart = req.getPart("imagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            String nombreGuardado = FilesUtil.copy(imagenPart, AppConfig.IMAGENES_DIR.resolve("cursos"));
            curso.setImagen(nombreGuardado);
        }

        // Temáticas: se reemplaza la colección entera por lo marcado en el
        // formulario. A diferencia de EstudianteTematica, aquí no hace falta
        // borrar-y-recrear a mano: Curso.tematicas sigue siendo un
        // @ManyToMany implícito, así que el propio em.merge() del Service
        // reconcilia la tabla intermedia según el contenido de la lista.
        List<Tematica> nuevasTematicas = new ArrayList<>();
        String[] tematicasIds = req.getParameterValues("tematicasIds");
        if (tematicasIds != null) {
            for (String idTexto : tematicasIds) {
                Long tematicaId = Long.parseLong(idTexto);
                tematicaService.obtenerPorId(tematicaId).ifPresent(nuevasTematicas::add);
            }
        }
        curso.setTematicas(nuevasTematicas);

        cursoService.actualizar(curso);

        return CommandResult.redirect("/app/detalleCurso?id=" + cursoId);
    }
}