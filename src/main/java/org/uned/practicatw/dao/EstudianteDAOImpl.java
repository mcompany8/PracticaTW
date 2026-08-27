package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Estudiante;

public class EstudianteDAOImpl extends GenericDAOImpl<Estudiante> implements EstudianteDAO {
    public EstudianteDAOImpl(EntityManagerFactory emf) {
        super(Estudiante.class, emf);
    }
}
