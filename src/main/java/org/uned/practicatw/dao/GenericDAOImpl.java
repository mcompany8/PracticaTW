package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.utils.JPAUtil;

import java.util.List;
import java.util.Optional;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private final EntityManagerFactory emf;
    private final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass, EntityManagerFactory emf) {

        this.entityClass = entityClass;
        this.emf = emf;
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    @Override
    public T guardar(T entidad) {
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             em.persist(entidad);
             em.getTransaction().commit();
             return entidad;
         } catch (RuntimeException e) {
             em.getTransaction().rollback();
             throw e;
         } finally {
             em.close();
         }
    }

    @Override
    public Optional<T> buscarPorId(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return Optional.ofNullable(em.find(entityClass, id));
        }
    }

    @Override
    public List<T> buscarTodos() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                    "SELECT e FROM " + entityClass.getSimpleName() + " e",
                    entityClass)
                    .getResultList();
        }
    }

    @Override
    public void actualizar(T entidad) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        }  catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.find(entityClass, id));
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
