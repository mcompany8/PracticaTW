package org.uned.practicatw.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Profesor;

import java.util.List;

public class ProfesorDAOImpl extends GenericDAOImpl<Profesor> implements ProfesorDAO {
    public ProfesorDAOImpl(EntityManagerFactory emf) {
        super(Profesor.class, emf);
    }

    @Override
    public List<Profesor> buscarProfesores() {
        try(EntityManager em = getEntityManager()) {
            return em.createNamedQuery("Profesor.obtenerProfesores", Profesor.class).getResultList();
        }
    }
}
