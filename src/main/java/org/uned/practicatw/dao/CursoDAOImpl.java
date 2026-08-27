package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Curso;

import java.util.List;

public class CursoDAOImpl extends GenericDAOImpl<Curso> implements CursoDAO {
    public CursoDAOImpl(EntityManagerFactory emf) {
        super(Curso.class, emf);
    }

    public List<Curso> buscarPorProfesor(Long idProfesor) {

        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Curso.buscarPorProfesor", Curso.class)
                    .setParameter("responsableId", idProfesor)
                    .getResultList();
        }

    }

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
}
