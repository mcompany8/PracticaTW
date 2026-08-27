package org.uned.practicatw.dao;

import jakarta.persistence.EntityManagerFactory;
import org.uned.practicatw.model.Tematica;

public class TematicaDAOImpl extends GenericDAOImpl<Tematica> implements TematicaDAO {
    public TematicaDAOImpl(EntityManagerFactory emf) {
        super(Tematica.class, emf);
    }
}
