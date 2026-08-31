package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Contenido;
import org.uned.practicatw.model.CursoContenido;

import java.util.List;

public class CursoContenidoDAOImpl extends GenericDAOImpl<CursoContenido> implements CursoContenidoDAO {

    public CursoContenidoDAOImpl(EntityManagerFactory emf) {
        super(CursoContenido.class, emf);
    }

    @Override
    public List<CursoContenido> buscarPorNotCurso(Long cursoId) {

        try(var em = getEntityManager()) {
            return em.createNamedQuery("CursoContenido.buscarPorNotCurso", CursoContenido.class)
                    .setParameter("cursoId", cursoId)
                    .getResultList();
        }
    }

    @Override
    public List<CursoContenido> buscarPorCursoAndContenido(Long cursoId, Long contenidoId) {

        try(var em = getEntityManager()) {
            return em.createNamedQuery("CursoContenido.buscarPorCursoYContenido", CursoContenido.class)
                    .setParameter("cursoId", cursoId)
                    .setParameter("contenidoId", contenidoId)
                    .getResultList();
        }
    }
}
