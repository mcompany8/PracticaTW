package org.uned.practicatw.service;

import org.uned.practicatw.dao.EntregaTareaDAO;
import org.uned.practicatw.model.EntregaTarea;

public class EntregaTareaServiceImpl extends GenericServiceImpl<EntregaTarea, EntregaTareaDAO> implements EntregaTareaService {
    public EntregaTareaServiceImpl(EntregaTareaDAO dao) {
        super(dao);
    }
}
