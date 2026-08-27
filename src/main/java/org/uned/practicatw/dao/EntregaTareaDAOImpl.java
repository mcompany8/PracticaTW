package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.EntregaTarea;

public class EntregaTareaDAOImpl extends GenericDAOImpl<EntregaTarea> implements EntregaTareaDAO {
    public EntregaTareaDAOImpl(EntityManagerFactory emf) {

        super(EntregaTarea.class, emf);
    }
}