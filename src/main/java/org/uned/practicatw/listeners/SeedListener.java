package org.uned.practicatw.listeners;

import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.model.ConfiguracionSistema;
import org.uned.practicatw.model.Curso;
import org.uned.practicatw.service.ConfiguracionService;
import org.uned.practicatw.service.ContenidoService;
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


/**
 * Tercer y último listener en ejecutarse: copia los ficheros semilla
 * embebidos en el classpath (materiales, imágenes de curso/temática) a los
 * directorios reales bajo {@code AppConfig.*_DIR}, rellena las descripciones
 * largas de los cursos leyendo ficheros de texto ({@code descripciones/curso-<id>.txt}),
 * y crea la fila de {@link ConfiguracionSistema} por defecto si no existe
 * todavía.
 * <p>
 * A diferencia de {@code DatabaseInitListener.insertUsuarios()},
 * {@link #seedConfiguracion()} sí comprueba primero si la fila ya existe, así
 * que es seguro ejecutarlo en cada arranque.
 */
@Slf4j
public class SeedListener implements ServletContextListener {

    private ContenidoService contenidoService;
    private ConfiguracionService configuracionService;

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

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }

    /**
     * Copia todos los ficheros regulares de un recurso del classpath a un
     * directorio destino en disco. Si el recurso no existe, no hace nada
     * (permite que el proyecto compile y arranque aunque falte alguna
     * carpeta de semillas opcional).
     */
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


    /**
     * Rellena {@code Curso.descripcionLarga} para los cursos que todavía no
     * la tienen, leyendo el fichero {@code descripciones/curso-<id>.txt}
     * correspondiente del classpath. Si no existe el fichero para un curso,
     * simplemente lo deja sin descripción larga (con un aviso en el log) en
     * vez de fallar.
     */
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

    /** Crea la fila única de {@link ConfiguracionSistema} (id = 1) si todavía no existe. */
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