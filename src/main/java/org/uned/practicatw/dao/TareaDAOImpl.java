package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Tarea;

public class TareaDAOImpl extends GenericDAOImpl<Tarea> implements TareaDAO {
    public TareaDAOImpl(EntityManagerFactory emf) {
        super(Tarea.class, emf);
    }
}
