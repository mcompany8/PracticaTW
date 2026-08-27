package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Profesor;

public class ProfesorDAOImpl extends GenericDAOImpl<Profesor> implements ProfesorDAO {
    public ProfesorDAOImpl(EntityManagerFactory emf) {
        super(Profesor.class, emf);
    }
}
