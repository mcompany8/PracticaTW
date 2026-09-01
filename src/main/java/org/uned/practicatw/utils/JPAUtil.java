package org.uned.practicatw.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Map;

/**
 * Punto único de acceso a la unidad de persistencia {@code practicatwPU}. El
 * {@link EntityManagerFactory} se crea una sola vez, en la inicialización
 * estática de la clase, y se comparte durante toda la vida de la aplicación
 * (ver {@code ApplicationListener}, que lo obtiene aquí y se lo pasa a
 * {@code ServiceFactory.init(emf)}).
 */
public class JPAUtil {

    private static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("practicatwPU");

    private JPAUtil() {}

    /**
     * @return el {@link EntityManagerFactory} compartido de la aplicación
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }

    /** Cierra el {@link EntityManagerFactory}. Se invoca desde {@code ApplicationListener.contextDestroyed}. */
    public static void shutdown() {
        EMF.close();
    }

    /**
     * @return un {@link EntityManager} nuevo, listo para usar
     */
    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
    }
}