package org.uned.practicatw.service;

import org.uned.practicatw.dao.TareaDAO;
import org.uned.practicatw.model.Tarea;

public class TareaServiceImpl extends GenericServiceImpl<Tarea, TareaDAO> implements TareaService {

    public TareaServiceImpl(TareaDAO dao) {
        super(dao);
    }
}
