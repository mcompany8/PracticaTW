package org.uned.practicatw.listeners;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;
import org.uned.practicatw.controller.CommandFactory;
import org.uned.practicatw.service.ServiceFactory;
import org.uned.practicatw.utils.JPAUtil;


@Slf4j
public class ApplicationListener implements ServletContextListener {

    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        inicioPU();
        inicioFactory();
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
}
