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
    }
}
