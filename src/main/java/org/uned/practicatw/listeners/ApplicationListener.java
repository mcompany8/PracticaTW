package org.uned.practicatw.listeners;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.uned.practicatw.utils.JPAUtil;

import java.util.logging.Logger;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ApplicationListener.class.getName());
    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JPAUtil.getEntityManagerFactory();
        logger.info("Creada la PU");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (emf != null) {
            emf.close();
            logger.info("Cerrada la PU");
        }
    }
}
