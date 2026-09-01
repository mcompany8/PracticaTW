package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.Administrador;
import org.uned.practicatw.model.ConfiguracionSistema;
import org.uned.practicatw.model.Usuario;
import org.uned.practicatw.service.ConfiguracionService;
import org.uned.practicatw.utils.FilesUtil;

/**
 * Guarda los cambios del formulario de configuración global (ruta
 * {@code guardarConfiguracion}, POST). El número de cursos recomendados se
 * acota siempre a [1, 12], independientemente de lo que llegue en el
 * formulario. La imagen del hero solo se sustituye si llega un fichero nuevo.
 * Solo accesible por un {@link Administrador}.
 */
public class GuardarConfiguracionCommand implements Command {

    private final ConfiguracionService configuracionService;

    public GuardarConfiguracionCommand(ConfiguracionService configuracionService) {
        this.configuracionService = configuracionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Usuario admin = (Usuario) req.getSession().getAttribute("usuario");
        if (!(admin instanceof Administrador)) {
            return CommandResult.forward("/WEB-INF/views/error/404.jsp");
        }

        ConfiguracionSistema config = configuracionService.obtenerPorId(1L).orElseThrow();

        config.setHeroTitulo(req.getParameter("heroTitulo"));
        config.setHeroSubtitulo(req.getParameter("heroSubtitulo"));

        Integer numCursos = Integer.parseInt(req.getParameter("numCursosRecomendados"));
        config.setNumCursosRecomendados(Math.max(1, Math.min(numCursos, 12)));

        Part imagenPart = req.getPart("heroImagen");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            // Se guarda directamente bajo IMAGENES_DIR (no en /cursos ni /tematicas),
            // por eso en el JSP el src es "imagenes/${config.heroImagen}" sin subcarpeta.
            String nombreGuardado = FilesUtil.copy(imagenPart, AppConfig.IMAGENES_DIR);
            config.setHeroImagen(nombreGuardado);
        }

        configuracionService.actualizar(config);

        return CommandResult.redirect("/app/admin");
    }
}