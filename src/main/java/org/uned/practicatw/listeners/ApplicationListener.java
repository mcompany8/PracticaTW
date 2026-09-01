package org.uned.practicatw.listeners;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.uned.practicatw.config.AppConfig;
import org.uned.practicatw.controller.CommandFactory;
import org.uned.practicatw.service.ServiceFactory;
import org.uned.practicatw.utils.JPAUtil;

import java.io.IOException;
import java.nio.file.Files;


/**
 * Primer listener en ejecutarse al arrancar la aplicación (declarado antes
 * que {@code DatabaseInitListener} y {@code SeedListener} en {@code web.xml},
 * no vía {@code @WebListener}, precisamente para garantizar ese orden):
 * crea la unidad de persistencia, inicializa {@link ServiceFactory} y
 * {@link CommandFactory}, y crea los directorios de subida de ficheros.
 * <p>
 * Los otros dos listeners dependen de que este haya corrido antes —
 * {@code ServiceFactory.get*Service()} devolvería {@code null} si se
 * invocara antes de {@code ServiceFactory.init(emf)}.
 */
@Slf4j
public class ApplicationListener implements ServletContextListener {

    private static EntityManagerFactory emf;

    @SneakyThrows
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        inicioPU();
        inicioFactory();
        crearDirectorios();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null) {
            emf.close();
            log.info("Cerrada la PU");
        }
    }

    private void inicioPU() {
        emf = JPAUtil.getEntityManagerFactory();
        log.info("Creada la PU");
    }

    private void inicioFactory() {

        ServiceFactory.init(emf);
        log.info("ServiceFactory inicializado");

        CommandFactory.init();
        log.info("CommandFactory inicializado");

    }

    private void crearDirectorios() throws IOException {
        Files.createDirectories(AppConfig.CONTENIDO_DIR);
        Files.createDirectories(AppConfig.IMAGENES_DIR);
        Files.createDirectories(AppConfig.IMAGENES_DIR.resolve("cursos"));
        Files.createDirectories(AppConfig.IMAGENES_DIR.resolve("tematicas"));
    }
}