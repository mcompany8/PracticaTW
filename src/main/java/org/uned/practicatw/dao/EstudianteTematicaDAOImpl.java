package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.uned.practicatw.model.EstudianteTematica;

import java.util.List;

public class EstudianteTematicaDAOImpl extends GenericDAOImpl<EstudianteTematica> implements EstudianteTematicaDAO {
    public EstudianteTematicaDAOImpl(EntityManagerFactory emf) {
        super(EstudianteTematica.class, emf);
    }

    @Override
    public List<EstudianteTematica> buscarPorEstudiante(Long estudianteId) {
        try(EntityManager em = getEntityManager()) {
            TypedQuery<EstudianteTematica> query = em.createNamedQuery(
                    "EstudianteTematica.buscarPorEstudiante"
                    , EstudianteTematica.class);
            query.setParameter("estudianteId", estudianteId);
            return query.getResultList();
        }
    }
}