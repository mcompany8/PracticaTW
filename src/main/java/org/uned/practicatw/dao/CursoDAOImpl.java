package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Curso;

import java.util.List;

public class CursoDAOImpl extends GenericDAOImpl<Curso> implements CursoDAO {
    public CursoDAOImpl(EntityManagerFactory emf) {
        super(Curso.class, emf);
    }

    @Override
    public List<Curso> buscarPorProfesor(Long idProfesor) {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Curso.buscarPorProfesor", Curso.class)
                    .setParameter("responsableId", idProfesor)
                    .getResultList();
        }

    }

    @Override
    public Curso buscarPorIdAndProfesor(Long id, Long idProfesor) {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Curso.buscarPorIdYProfesor", Curso.class)
                    .setParameter("responsableId", idProfesor)
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
        }
    }

    @Override
    public List<Curso> buscarRandom(Integer cantidad) {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Curso.buscarCursosRandom", Curso.class)
                    .setMaxResults(cantidad)
                    .getResultList();
        }

    }

    @Override
    public List<Curso> buscarTodosConTematicas() {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Curso.buscarTodosConTematicas", Curso.class)
                    .getResultList();
        }
    }

    @Override
    public List<Curso> buscarPorTematica(Long tematicaId) {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Curso.buscarPorTematica", Curso.class)
                    .setParameter("tematicaId", tematicaId)
                    .getResultList();
        }
    }
}
