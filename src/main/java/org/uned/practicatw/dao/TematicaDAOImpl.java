package org.uned.practicatw.dao;

import org.uned.practicatw.model.Tematica;

public class TematicaDAOImpl extends GenericDAOImpl<Tematica,Integer> implements TematicaDAO {
    public TematicaDAOImpl() {
        super(Tematica.class);
    }
}
