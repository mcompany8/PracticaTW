package org.uned.practicatw.dao;

import org.uned.practicatw.model.Tarea;

public class TareaDAOImpl extends GenericDAOImpl<Tarea,Long> implements TareaDAO {
    public TareaDAOImpl() {
        super(Tarea.class);
    }
}
