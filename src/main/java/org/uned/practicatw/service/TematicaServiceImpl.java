package org.uned.practicatw.service;

import org.uned.practicatw.dao.TematicaDAO;
import org.uned.practicatw.model.Tematica;

public class TematicaServiceImpl extends GenericServiceImpl<Tematica, TematicaDAO> implements TematicaService {
    public TematicaServiceImpl(TematicaDAO dao) {
        super(dao);
    }
}
