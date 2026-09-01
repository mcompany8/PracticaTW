package org.uned.practicatw.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.uned.practicatw.controller.CommandResult;
import org.uned.practicatw.model.ConfiguracionSistema;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.ConfiguracionService;
import org.uned.practicatw.service.CursoService;

import java.util.List;

/**
 * Muestra la portada (ruta {@code inicio}): hero configurable y una
 * selección aleatoria de cursos destacados, cuya cantidad depende de
 * {@link ConfiguracionSistema#getNumCursosRecomendados()}.
 * <p>
 * Si por algún motivo no existe la fila de configuración (no debería ocurrir
 * en circunstancias normales, ver {@code SeedListener}), usa unos valores por
 * defecto en memoria en vez de fallar.
 */
public class IndexCommand implements Command {

    private final CursoService cursoService;
    private final ConfiguracionService configuracionService;

    public IndexCommand(CursoService cursoService, ConfiguracionService configuracionService) {
        this.cursoService = cursoService;
        this.configuracionService = configuracionService;
    }

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        ConfiguracionSistema config = configuracionService.obtenerPorId(1L)
                .orElseGet(() -> ConfiguracionSistema.builder()
                        .heroTitulo("Aprende a tu ritmo con InfoFormación")
                        .heroSubtitulo("")
                        .numCursosRecomendados(6)
                        .build());

        List<Curso> cursosDestacados = cursoService.obtenerCursosRandom(config.getNumCursosRecomendados());

        req.setAttribute("cursos", cursosDestacados);
        req.setAttribute("config", config);
        return CommandResult.forward("/WEB-INF/views/index.jsp");
    }
}