package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.model.Profesor;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;

import java.util.Optional;

/**
 * Muestra el formulario de edición de un material (ruta {@code editarMaterial}, GET).
 * El guardado real lo procesa {@code GuardarMaterialCommand}, en una ruta
 * distinta — {@code FrontController} no distingue por verbo HTTP, así que
 * mostrar y guardar necesitan rutas separadas.
 */
public class EditarMaterialCommand implements Command {

    private final ContenidoService contenidoService;
    private final CursoService cursoService;

    public EditarMaterialCommand(ContenidoService contenidoService, CursoService cursoService) {
        this.contenidoService = contenidoService;
        this.cursoService = cursoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
        if (!(usuario instanceof Profesor)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Long materialId = Long.parseLong(req.getParameter("id"));
        Long cursoId = Long.parseLong(req.getParameter("cursoId"));

        Curso curso = cursoService.obtenerCursoPorIdYProfesor(cursoId, usuario.getId());
        if (curso == null) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Optional<Contenido> materialOpt = contenidoService.obtenerPorId(materialId);
        if (materialOpt.isEmpty()) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        Contenido material = materialOpt.get();

        if (!material.getCurso().getId().equals(cursoId)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        req.setAttribute("material", material);
        return CommandResult.forward("/WEB-INF/views/editarMaterial.jsp");
    }
}