package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.utils.JPAUtil;

import java.util.List;
import java.util.Optional;

public abstract class GenericDAOImpl<T,ID> implements GenericDAO<T,ID> {

    private static final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
    private final Class<T> entityClass;

    protected GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
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
    public Optional<T> buscarPorId(ID id) {
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
    public void eliminar(ID id) {
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
