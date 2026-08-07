package org.uned.practicatw.dao;

import org.uned.practicatw.model.EntregaTarea;

public class EntregaTareaDAOImpl extends GenericDAOImpl<EntregaTarea,Long> implements EntregaTareaDAO{
    public EntregaTareaDAOImpl() {
        super(EntregaTarea.class);
    }
}