package org.uned.practicatw.listeners;

import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.model.ConfiguracionSistema;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.ConfiguracionService;
import org.uned.practicatw.service.ContenidoService;
import org.uned.practicatw.service.CursoService;
import org.uned.practicatw.service.ServiceFactory;
import org.uned.practicatw.utils.FilesUtil;
import org.uned.practicatw.utils.JPAUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;


@Slf4j
public class SeedListener implements ServletContextListener {

    private ContenidoService contenidoService;
    private ConfiguracionService configuracionService;

    /**
     *
     * @param sce the ServletContextEvent containing the ServletContext that is being initialized
     */
    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        this.contenidoService = ServiceFactory.getContenidoService();
        this.configuracionService = ServiceFactory.getConfiguracionService();

        copiarArchivos("contenido", AppConfig.CONTENIDO_DIR);
        copiarArchivos("imagenes", AppConfig.IMAGENES_DIR);
        copiarArchivos("imagenes/cursos", AppConfig.IMAGENES_DIR.resolve("cursos"));
        copiarArchivos("imagenes/tematicas", AppConfig.IMAGENES_DIR.resolve("tematicas"));
        actualizarDescripcionesDB();
        seedConfiguracion();
    }

    /**
     * @param sce the ServletContextEvent containing the ServletContext that is being destroyed
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    private void copiarArchivos (String resource, Path destino) {
        try {
            URL url = getClass().getClassLoader().getResource(resource);
            if (url == null) return;

            Path origenDir = Path.of(url.toURI());

            try (Stream<Path> stream = Files.list(origenDir)) {
                var archivos = stream.filter(Files::isRegularFile).toList();
                for (Path p : archivos){
                    FilesUtil.copy(p, destino);
                    log.info("Copiado seed file: " +   p.getFileName());
                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    private void actualizarDescripcionesDB() throws URISyntaxException {
        var em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();

            List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class)
                    .getResultList();

            for (Curso c : cursos) {
                if (c.getDescripcionLarga() != null) continue;
                String recurso = "descripciones/curso-" + c.getId() + ".txt";

                try (InputStream is = getClass().getClassLoader().getResourceAsStream(recurso)) {
                    System.out.println(recurso);
                    if (is==null) {
                        log.warn("No se encontró descripción larga para el curso " + c.getId());
                        continue;
                    }
                    c.setDescripcionLarga(new String(is.readAllBytes(), StandardCharsets.UTF_8));
                }
            }

            tx.commit();
        } catch (RuntimeException | IOException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw new RuntimeException("Error al sembrar descripciones de cursos.");
        }
    }

    private void seedConfiguracion() {
        if (configuracionService.obtenerPorId(1L).isEmpty()) {
            ConfiguracionSistema config = ConfiguracionSistema.builder()
                    .id(1L)
                    .heroTitulo("Aprende a tu ritmo con InfoFormación")
                    .heroSubtitulo("Cursos online creados por profesores expertos. Inscríbete, sigue tu progreso y consigue tus objetivos de aprendizaje.")
                    .numCursosRecomendados(6)
                    .build();
            configuracionService.crear(config);
            log.info("Creada configuración del sistema por defecto");
        }
    }

}
