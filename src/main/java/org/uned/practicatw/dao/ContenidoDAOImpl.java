package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Contenido;

import java.util.List;

public class ContenidoDAOImpl extends GenericDAOImpl<Contenido> implements ContenidoDAO {

    public ContenidoDAOImpl(EntityManagerFactory emf) {
        super(Contenido.class, emf);
    }

    @Override
    public List<Contenido> buscarPorPropietarioOrPublico(Long propietario) {
        try (EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Contenido.buscarPorCurso", Contenido.class)
                    .setParameter("propietarioId", propietario)
                    .getResultList();
        }
    }
}
