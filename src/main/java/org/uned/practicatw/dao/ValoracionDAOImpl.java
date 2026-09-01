package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.uned.practicatw.model.Valoracion;

import java.util.List;

public class ValoracionDAOImpl extends GenericDAOImpl<Valoracion> implements ValoracionDAO {
    public ValoracionDAOImpl(EntityManagerFactory emf) {
        super(Valoracion.class, emf);
    }

    @Override
    public Valoracion buscarPorInscripcion(Long inscripcionId) {
        try(EntityManager em = getEntityManager()) {
            TypedQuery<Valoracion> query = em.createNamedQuery(
                    "Valoracion.buscarPorInscripcion"
                    , Valoracion.class);
            query.setParameter("inscripcionId", inscripcionId);
            return query.getSingleResultOrNull();
        }
    }

    @Override
    public List<Valoracion> buscarPorCurso(Long cursoId) {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Valoracion.obtenerPorCurso", Valoracion.class)
                    .setParameter("cursoId", cursoId)
                    .getResultList();
        }
    }


}
